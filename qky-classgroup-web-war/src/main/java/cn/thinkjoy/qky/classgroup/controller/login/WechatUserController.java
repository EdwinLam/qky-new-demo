package cn.thinkjoy.qky.classgroup.controller.login;

import cn.thinkjoy.qky.classgroup.common.utils.login.HttpRequest;
import cn.thinkjoy.qky.classgroup.common.utils.login.JacksonUtil;
import cn.thinkjoy.qky.classgroup.common.utils.login.wechatUtils.AES;
import cn.thinkjoy.qky.classgroup.controller.BaseController;
import cn.thinkjoy.qky.classgroup.domain.bas.BasUserEx;
import cn.thinkjoy.qky.classgroup.domain.login.JsonDto;

import org.apache.commons.codec.binary.Base64;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.util.Map;


@Controller
@RequestMapping("/server")
public class WechatUserController extends BaseController{
    public static String appid = "wx81ad6819b892ec7f";
    public static String secret = "791088daf3bd77b02e324ae85857089b";
    public static String grant_type = "authorization_code";
    public static String apiUrl = "https://api.weixin.qq.com/sns/jscode2session";

    @RequestMapping("/test")
    @ResponseBody
    public  String test(){
        return getCurrUser().toString();
    }

    /**
     * 使用code换取用户openid
     * @return
     */
    @RequestMapping("/login")
    @ResponseBody
    public JsonDto login(String code,BasUserEx user,HttpSession session){
        JsonDto jsonDto = new JsonDto();
        jsonDto.setCode(0);
        jsonDto.setMsg("成功");
        //接口地址
        //https://api.weixin.qq.com/sns/jscode2session?appid=APPID&secret=SECRET&js_code=JSCODE&grant_type=authorization_code
        String params = "appid=" + appid + "&secret=" + secret + "&js_code=" + code + "&grant_type=" + grant_type;
        String res = HttpRequest.sendGet(apiUrl, params);
        System.out.println(res);
        Map map = JacksonUtil.JsonToMap(res);
        if(map==null){
            return new JsonDto(-1,"操作失败");
        }
        if (map.get("errmsg")!=null){
            return new JsonDto(-1,map.get("errmsg").toString());
        }
        String session_key = map.get("session_key").toString();
        String openid = map.get("openid").toString();
        Object exp = map.get("expires_in");
        int expires_in = exp==null?0:Integer.parseInt(exp.toString());


        System.out.println(session_key+"----"+openid);

        //登录成功，保存openid,session_key
        user.setSession_key(session_key);
        user.setOpenid(openid);
        session.setAttribute("userInfo",user);
        session.setMaxInactiveInterval(expires_in);

        return jsonDto;
    }

    /**
     * 解密数据
     * @param session
     * @param sessionId
     * @param encryptedData
     * @param iv
     * @return
     */
    @RequestMapping("/encodeData")
    @ResponseBody
    public JsonDto encodeData(HttpSession session , @RequestParam(value = "thiedSessionKey",required = false) String sessionId, @RequestParam(value = "encryptedData",required = false) String encryptedData, String iv){

        String session_key = getCurrUser().getSession_key();
        Base64 base64 = new Base64();
        String userInfo = null;
        try {
            userInfo = new String(AES.decrypt(base64.decode(encryptedData), base64.decode(session_key), base64.decode(iv)),"UTF-8");
        } catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        if(null==userInfo){
            return null;
        }
        Map<String,String> map = JacksonUtil.JsonToMap(userInfo);

        JsonDto jsonDto = new JsonDto();
        jsonDto.setData(map);
        return jsonDto;
    }
}
