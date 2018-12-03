/*
{*****************************************************************************
{  全课云-班级小助手 v1.0
{  版权信息 (c) 2005-2018 广东全通教育股份有限公司. 保留所有权利.
{  创建人：  linyuewei
{  审查人：
{  模块：附件表，有于记录整个应用中各个功能模块产生的附件
{  功能描述:
{
{  ---------------------------------------------------------------------------
{  维护历史:
{  日期        维护人        维护类型
{  ---------------------------------------------------------------------------
{  2018-11-27  linyuewei        新建
{
{  ---------------------------------------------------------------------------
{  注：本模块代码由codgen代码生成工具辅助生成 http://www.oschina.net/p/codgen
{*****************************************************************************
*/

package cn.thinkjoy.qky.classgroup.param.bas;

import cn.thinkjoy.qky.classgroup.param.CreateBaseParam;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * 《附件表，有于记录整个应用中各个功能模块产生的附件》 查询参数实体
 *
 * @author linyuewei
 */
public class BasAttsParam extends CreateBaseParam<Long> {
    private static final long serialVersionUID = 1L;

    /**
     * 字段常量——所属群组id
     */
    public static final String F_GroupId = "groupId";
    /**
     * 字段常量——所属业务表id
     */
    public static final String F_SourceId = "sourceId";
    /**
     * 字段常量——来源标识编码,通常是sourceId所对应的表名，如biz_notice表示通知；biz_enroll表示报名；
     */
    public static final String F_SourceCode = "sourceCode";
    /**
     * 字段常量——文件名称,用于显示
     */
    public static final String F_FileName = "fileName";
    /**
     * 字段常量——文件大小，单位是k
     */
    public static final String F_FileSize = "fileSize";
    /**
     * 字段常量——文件类型
     */
    public static final String F_FileType = "fileType";
    /**
     * 字段常量——文件访问路径
     */
    public static final String F_FileUrl = "fileUrl";
    /**
     * 字段常量——文件id,上传第三方文件系统时，文件标识的id
     */
    public static final String F_FileId = "fileId";

    private Long groupId; //所属群组id
    private Long sourceId; //所属业务表id
    private String sourceCode; //来源标识编码,通常是sourceId所对应的表名，如biz_notice表示通知；biz_enroll表示报名；
    private String fileName; //文件名称,用于显示
    private Long fileSize; //文件大小，单位是k
    private String fileType; //文件类型
    private String fileUrl; //文件访问路径
    private String fileId; //文件id,上传第三方文件系统时，文件标识的id

    /**
     * 默认空构造函数
     */
    public BasAttsParam() {
        super();
    }

    /**
     * @return groupId 所属群组id
     */
    public Long getGroupId() {
        return this.groupId;
    }

    /**
     * @param groupId 所属群组id
     */
    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    /**
     * @return sourceId 所属业务表id
     */
    public Long getSourceId() {
        return this.sourceId;
    }

    /**
     * @param sourceId 所属业务表id
     */
    public void setSourceId(Long sourceId) {
        this.sourceId = sourceId;
    }

    /**
     * @return sourceCode 来源标识编码,通常是sourceId所对应的表名，如biz_notice表示通知；biz_enroll表示报名；
     */
    public String getSourceCode() {
        return this.sourceCode;
    }

    /**
     * @param sourceCode 来源标识编码,通常是sourceId所对应的表名，如biz_notice表示通知；biz_enroll表示报名；
     */
    public void setSourceCode(String sourceCode) {
        this.sourceCode = sourceCode;
    }

    /**
     * @return fileName 文件名称,用于显示
     */
    public String getFileName() {
        return this.fileName;
    }

    /**
     * @param fileName 文件名称,用于显示
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    /**
     * @return fileSize 文件大小，单位是k
     */
    public Long getFileSize() {
        return this.fileSize;
    }

    /**
     * @param fileSize 文件大小，单位是k
     */
    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }

    /**
     * @return fileType 文件类型
     */
    public String getFileType() {
        return this.fileType;
    }

    /**
     * @param fileType 文件类型
     */
    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    /**
     * @return fileUrl 文件访问路径
     */
    public String getFileUrl() {
        return this.fileUrl;
    }

    /**
     * @param fileUrl 文件访问路径
     */
    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    /**
     * @return fileId 文件id,上传第三方文件系统时，文件标识的id
     */
    public String getFileId() {
        return this.fileId;
    }

    /**
     * @param fileId 文件id,上传第三方文件系统时，文件标识的id
     */
    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("groupId", getGroupId())
                .append("sourceId", getSourceId())
                .append("sourceCode", getSourceCode())
                .append("fileName", getFileName())
                .append("fileSize", getFileSize())
                .append("fileType", getFileType())
                .append("fileUrl", getFileUrl())
                .append("fileId", getFileId())
                .append("creator", getCreator())
                .append("createDate", getCreateDate())
                .append("lastModifier", getLastModifier())
                .append("lastModDate", getLastModDate())
                .append("status", getStatus())
                .toString();
    }

}
