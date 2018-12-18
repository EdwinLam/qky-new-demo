package cn.ripple.controller.face;

import cn.ripple.controller.BaseController;
import cn.ripple.dao.face.ImageInfo;
import cn.ripple.entity.face.FaceInfo;
import cn.ripple.entity.user.UserInfo;
import cn.ripple.exception.RippleException;
import cn.ripple.service.face.FaceEngineService;
import cn.ripple.service.face.FaceInfoService;
import cn.ripple.service.user.UserInfoService;
import cn.ripple.utils.ImageUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.concurrent.ExecutionException;


/**
 * @author Edwin
 */
@Slf4j
@RestController
@Api(description = "用户管理接口")
@RequestMapping("/ripple/faceInfo")
public class FaceInfoController extends BaseController<FaceInfo, String>{

    @Autowired
    private FaceInfoService faceInfoService;
    @Autowired
    private FaceEngineService faceEngineService;
    @Autowired
    private UserInfoService userInfoService;

    @Override
    public FaceInfoService getService() {
        return faceInfoService;
    }

    @RequestMapping(value = "/detectFaces",method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "detectFaces")
    public List<FaceInfo> faceFeature(@RequestParam("file") MultipartFile file) throws IOException {
        InputStream inputStream = file.getInputStream();
        ImageInfo imageInfo = ImageUtil.getRGBData(inputStream);
        List<FaceInfo> faceInfos = faceEngineService.detectFaces(imageInfo);
        for(FaceInfo faceInfo:faceInfos){
            faceInfo.setFaceFeature(null);
        }
        return  faceInfos;
    }

    @RequestMapping(value = "/addFaceToGroup",method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "增加人脸到脸库")
    public boolean addFaceToGroup(@RequestParam("file") MultipartFile file,String userId,Integer groupId) throws IOException, ExecutionException {
        InputStream inputStream = file.getInputStream();
        ImageInfo imageInfo = ImageUtil.getRGBData(inputStream);
        List<FaceInfo> faceInfos = faceEngineService.detectFaces(imageInfo);
        if(faceInfos.size()>1)
            throw new RippleException("只能添加一张人脸");
        if(faceInfos.size()>0){
            FaceInfo faceInfo = faceInfos.get(0);
            UserInfo userInfo = userInfoService.get(userId);
            userInfo.setFaceToken(faceInfo.getToken());
            faceInfoService.save(faceInfo);
            userInfoService.update(userInfo);
            faceEngineService.addFaceToCache(groupId,faceInfo);
        }
        return  true;
    }

}
