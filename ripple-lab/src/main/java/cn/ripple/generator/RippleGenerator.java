package cn.ripple.generator;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import cn.ripple.entity.Table;
import cn.ripple.generator.bean.EntityOfEntity;
import lombok.extern.slf4j.Slf4j;
import org.beetl.core.Configuration;
import org.beetl.core.GroupTemplate;
import org.beetl.core.Template;
import org.beetl.core.resource.FileResourceLoader;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
public class RippleGenerator {

    /**
     * 实体类名
     * 建议仅需修改
     */
    private static final String className = "User";

    /**
     * 类说明描述
     * 建议仅需修改
     */
    private static final String description = "用户";

    /**
     * 作者名
     * 建议仅需修改
     */
    private static final String author = "Edwin";

    /**
     * 主键类型
     */
    private static final String primaryKeyType = "String";

    /**
     * 实体类对应包
     */
    private static final String entityPackage = "cn.ripple.entity";

    /**
     * dao对应包
     */
    private static final String daoPackage = "cn.ripple.dao";

    /**
     * service对应包
     */
    private static final String servicePackage = "cn.ripple.service";

    /**
     * serviceImpl对应包
     */
    private static final String serviceImplPackage = "cn.ripple.service.impl";

    /**
     * controller对应包
     */
    private static final String controllerPackage = "cn.ripple.controller";


    /**
     * 模板地址
     */
    private static final String templatePath =getProjectPath()+File.separator+"ripple-lab"+File.separator+"src"+File.separator+"main"+File.separator+"java"
            +File.separator+"cn"+File.separator+"ripple"+File.separator+"generator"+File.separator+"template";

    /**
     * 实体地址
     */
    private static final String entityPath =getProjectPath()+File.separator+"ripple-entity"+File.separator+"src"+File.separator+"main"+File.separator+"java"
            +File.separator+"cn"+File.separator+"ripple"+File.separator+"entity";

    /**
     * service地址
     */
    private static final String servicePath =getProjectPath()+File.separator+"ripple-service"+File.separator+"src"+File.separator+"main"+File.separator+"java"
            +File.separator+"cn"+File.separator+"ripple"+File.separator+"service";

    /**
     * service impl地址
     */
    private static final String serviceImplPath =getProjectPath()+File.separator+"ripple-service"+File.separator+"src"+File.separator+"main"+File.separator+"java"
            +File.separator+"cn"+File.separator+"ripple"+File.separator+"service"+File.separator+"impl";

    /**
     * dao地址
     */
    private static final String daoPath =getProjectPath()+File.separator+"ripple-service"+File.separator+"src"+File.separator+"main"+File.separator+"java"
            +File.separator+"cn"+File.separator+"ripple"+File.separator+"dao";

    /**
     * controller地址
     */
    private static final String controllerPath =getProjectPath()+File.separator+"ripple-web"+File.separator+"src"+File.separator+"main"+File.separator+"java"
            +File.separator+"cn"+File.separator+"ripple"+File.separator+"controller";


    /**
     * 运行该主函数即可生成代码
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {

//        //生成代码
        generateCode("user");

        //根据类名删除生成的代码
        //deleteCode(className);
    }

    private static String getProjectPath(){
        return System.getProperty("user.dir").replace(File.separatorChar+"ripple-lab","");
    }

    /**
     * 根据名称获取类层次
     * @param tableName
     * @return
     */
    private static String getTableClassNames(String tableName){
        String tableClassName = tableName;
        if(StrUtil.isNotBlank(tableName)){
            String[] moduleName = tableName.split("_");
            tableClassName = moduleName[0]; //
        }
        return tableClassName;
    }

    /**
     * 根据名称获取目录层次
     * @param tableName
     * @return
     */
    private static String getTablePathNames(String tableName){
        String tableClassName = tableName;
        if(StrUtil.isNotBlank(tableName)){
            String[] moduleName = tableName.split("_");
            tableClassName = moduleName[0]; //
        }
        return tableClassName;
    }

    /**
     * 生成代码
     * @param tableName
     * @throws IOException
     */
    public static void generateCode(String tableName) throws IOException{
        String plusClass = "."+getTableClassNames(tableName);
        String plusPath = File.separator+getTablePathNames(tableName);
        String className = first2UpperCase(underlineToCamel(new StringBuffer(tableName)).toString());
        //模板路径
        FileResourceLoader resourceLoader = new FileResourceLoader(templatePath,"utf-8");
        Configuration cfg = Configuration.defaultConfiguration();
        GroupTemplate gt = new GroupTemplate(resourceLoader, cfg);

        Template entityTemplate = gt.getTemplate("entity.btl");
        Template daoTemplate = gt.getTemplate("dao.btl");
        Template serviceTemplate = gt.getTemplate("service.btl");
        Template serviceImplTemplate = gt.getTemplate("serviceImpl.btl");
        Template controllerTemplate = gt.getTemplate("controller.btl");

        EntityOfEntity entity = new EntityOfEntity();
        entity.setEntityPackage(entityPackage+plusClass);
        entity.setDaoPackage(daoPackage+plusClass);
        entity.setServicePackage(servicePackage+plusClass);
        entity.setServiceImplPackage(serviceImplPackage+plusClass);
        entity.setControllerPackage(controllerPackage+plusClass);
        entity.setAuthor(author);
        entity.setClassName(className);
        entity.setTableName("t_"+camel2Underline(className));
        entity.setClassNameLowerCase(first2LowerCase(className));
        entity.setDescription(description);
        entity.setPrimaryKeyType(primaryKeyType);

        OutputStream out = null;

        //生成实体类代码
        entityTemplate.binding("entity",entity);
        String entityResult = entityTemplate.render();
        log.info(entityResult);
        //创建文件
        String entityFileUrl = entityPath+plusPath+File.separator+className+".java";
        File entityFile = new File(entityFileUrl);

        if(!entityFile.exists()){
            new File(entityPath+plusPath).mkdirs();
            //实体类若存在则不重新生成
            entityFile.createNewFile();
            out = new FileOutputStream(entityFile);
            entityTemplate.renderTo(out);
        }

        //生成dao代码
        daoTemplate.binding("entity",entity);
        String daoResult = daoTemplate.render();
        log.info(daoResult);
        //创建文件
        String daoFileUrl =daoPath+plusPath+ File.separator+className+"Dao.java";
        new File(daoPath+plusPath).mkdirs();
        File daoFile = new File(daoFileUrl);
        daoFile.createNewFile();
        out = new FileOutputStream(daoFile);
        daoTemplate.renderTo(out);

        //生成service代码
        serviceTemplate.binding("entity",entity);
        String serviceResult = serviceTemplate.render();
        log.info(serviceResult);
        //创建文件
        String serviceFileUrl =servicePath+plusPath+File.separator+className+"Service.java";
        new File(servicePath+plusPath).mkdirs();
        File serviceFile = new File(serviceFileUrl);
        serviceFile.createNewFile();
        out = new FileOutputStream(serviceFile);
        serviceTemplate.renderTo(out);

        //生成serviceImpl代码
        serviceImplTemplate.binding("entity",entity);
        String serviceImplResult = serviceImplTemplate.render();
        log.info(serviceImplResult);
        //创建文件
        String serviceImplFileUrl =serviceImplPath+plusPath+File.separator+className+"ServiceImpl.java";
        new File(serviceImplPath+plusPath).mkdirs();

        File serviceImplFile = new File(serviceImplFileUrl);
        serviceImplFile.createNewFile();
        out = new FileOutputStream(serviceImplFile);
        serviceImplTemplate.renderTo(out);

        //生成controller代码
        controllerTemplate.binding("entity",entity);
        String controllerResult = controllerTemplate.render();
        log.info(controllerResult);
        //创建文件
        String controllerFileUrl = controllerPath+plusPath+File.separator+className+"Controller.java";
        new File(controllerPath+plusPath).mkdirs();
        File controllerFile = new File(controllerFileUrl);
        controllerFile.createNewFile();
        out = new FileOutputStream(controllerFile);
        controllerTemplate.renderTo(out);

        out.close();
        log.info("=====生成代码成功=====");
    }

    /**
     * 删除指定类代码
     * @param className
     */
    private static void deleteCode(String className) {
        String entityFileUrl = System.getProperty("user.dir")+"/src/main/java/cn/ripple/domain/"+className+".java";
        File entityFile = new File(entityFileUrl);
        if(entityFile.exists()){
            entityFile.delete();
        }
        String daoFileUrl = System.getProperty("user.dir")+"/src/main/java/cn/ripple/dao/"+className+"Dao.java";
        File daoFile = new File(daoFileUrl);
        if(daoFile.exists()){
            daoFile.delete();
        }

        String serviceFileUrl = System.getProperty("user.dir")+"/src/main/java/cn/ripple/service/"+className+"Service.java";
        File serviceFile = new File(serviceFileUrl);
        if(serviceFile.exists()){
            serviceFile.delete();
        }

        String serviceImplFileUrl = System.getProperty("user.dir")+"/src/main/java/cn/ripple/serviceimpl/"+className+"ServiceImpl.java";
        File serviceImplFile = new File(serviceImplFileUrl);
        if(serviceImplFile.exists()){
            serviceImplFile.delete();
        }

        String controllerFileUrl = System.getProperty("user.dir")+"/src/main/java/cn/ripple/controller/"+className+"Controller.java";
        File controllerFile = new File(controllerFileUrl);
        if(controllerFile.exists()){
            controllerFile.delete();
        }

        log.info("=====删除代码成功=====");
    }

    /**
     * 下划线转驼峰
     * @param str
     * @return
     */
    public static StringBuffer underlineToCamel(StringBuffer str) {
        //利用正则删除下划线，把下划线后一位改成大写
        Pattern pattern = Pattern.compile("_(\\w)");
        Matcher matcher = pattern.matcher(str);
        StringBuffer sb = new StringBuffer(str);
        if(matcher.find()) {
            sb = new StringBuffer();
            //将当前匹配子串替换为指定字符串，并且将替换后的子串以及其之前到上次匹配子串之后的字符串段添加到一个StringBuffer对象里。
            //正则之前的字符和被替换的字符
            matcher.appendReplacement(sb, matcher.group(1).toUpperCase());
            //把之后的也添加到StringBuffer对象里
            matcher.appendTail(sb);
        }else {
            return sb;
        }
        return underlineToCamel(sb);
    }

    /**
     * 驼峰法转下划线
     */
    public static String camel2Underline(String str) {
        if (StrUtil.isBlank(str)) {
            return "";
        }
        if(str.length()==1){
            return str.toLowerCase();
        }
        StringBuffer sb = new StringBuffer();
        for(int i=1;i<str.length();i++){
            if(Character.isUpperCase(str.charAt(i))){
                sb.append("_"+Character.toLowerCase(str.charAt(i)));
            }else{
                sb.append(str.charAt(i));
            }
        }
        return (str.charAt(0)+sb.toString()).toLowerCase();
    }

    /**
     * 首字母小写
     */
    public static String first2LowerCase(String str) {
        if (StrUtil.isBlank(str)) {
            return "";
        }
        if(str.length()==1){
            return str.toLowerCase();
        }
        StringBuffer sb = new StringBuffer();
        sb.append(Character.toLowerCase(str.charAt(0)));
        sb.append(str.substring(1));
        return sb.toString();
    }

    /**
     * 首字母大写哦
     */
    public static String first2UpperCase(String str) {
        if (StrUtil.isBlank(str)) {
            return "";
        }
        if(str.length()==1){
            return str.toLowerCase();
        }
        StringBuffer sb = new StringBuffer();
        sb.append(Character.toUpperCase(str.charAt(0)));
        sb.append(str.substring(1));
        return sb.toString();
    }
}
