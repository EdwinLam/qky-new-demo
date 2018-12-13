package cn.ripple.controller.user;

import cn.ripple.controller.BaseController;
import cn.ripple.entity.face.FaceInfo;
import cn.ripple.entity.user.UserInfo;
import cn.ripple.face.bean.SingleFaceInfo;
import cn.ripple.service.face.FaceEngineService;
import cn.ripple.service.user.UserInfoService;
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


/**
 * @author Edwin
 */
@Slf4j
@RestController
@Api(description = "用户管理接口")
@RequestMapping("/ripple/userInfo")
public class UserInfoController extends BaseController<UserInfo, String>{

    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private FaceEngineService faceEngineService;

    @Override
    public UserInfoService getService() {
        return userInfoService;
    }

    @RequestMapping(value = "/faceFeature",method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "faceFeature")
    public List<FaceInfo> faceFeature(@RequestParam("file") MultipartFile file) throws IOException {
        InputStream inputStream = file.getInputStream();
        BufferedImage image = ImageIO.read(inputStream);
        return faceEngineService.detectFaces(image);
    }
}
