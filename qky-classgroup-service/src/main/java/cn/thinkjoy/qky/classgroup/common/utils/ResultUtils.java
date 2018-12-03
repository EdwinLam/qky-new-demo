package cn.thinkjoy.qky.classgroup.common.utils;

import cn.thinkjoy.common.exception.BizException;
import cn.thinkjoy.qky.classgroup.domain.QkyCreateBaseDomain;
import cn.thinkjoy.qky.classgroup.domain.bas.BasUser;
import com.google.gson.Gson;

import javax.servlet.ServletResponse;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by tengen on 2016/7/19.
 */
public class ResultUtils {

    /**
     *  使用response输出JSON
     * @param response
     * @param resultMap
     */
    public static void out(ServletResponse response, Map<String, Object> resultMap){

        PrintWriter out = null;
        try {
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json");
            out = response.getWriter();
            out.println(new Gson().toJson(resultMap));
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            if(out!=null){
                out.flush();
                out.close();
            }
        }
    }

    public static void outBizException(ServletResponse response,BizException bizException){
        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("code", bizException.getErrorCode());
        resultMap.put("msg", bizException.getMsg());
        resultMap.put("timestamp", System.currentTimeMillis());
        out(response,resultMap);
    }

    public static void outSuc(ServletResponse response,Map<String, Object> data){
        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("code", "000000");
        resultMap.put("bizData", data);
        resultMap.put("timestamp", System.currentTimeMillis());
        out(response,resultMap);
    }

    public static Map<String, Object> resultMap(boolean flag, Integer code, String msg){
        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("success", flag);
        resultMap.put("message", msg);
        resultMap.put("code", code);
        resultMap.put("timestamp", System.currentTimeMillis());
        return resultMap;
    }

    public static Map<String, Object> resultMap(String code, String msg, Object data){

        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("message", msg);
        resultMap.put("code", code);
        resultMap.put("timestamp", System.currentTimeMillis());
        resultMap.put("result", data);
        return resultMap;
    }


    public static void initCreateDomain(QkyCreateBaseDomain baseDomain, BasUser basUser) {
        baseDomain.setLastModDate(new Date().getTime());
        baseDomain.setCreateDate(new Date().getTime());
        baseDomain.setCreator(basUser.getId());
        baseDomain.setLastModifier(basUser.getId());
    }

    public static void initUpdateDomain(QkyCreateBaseDomain baseDomain, BasUser basUser) {
        baseDomain.setLastModifier(basUser.getId());
        baseDomain.setLastModDate(new Date().getTime());
    }

}
