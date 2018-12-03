/*
{*****************************************************************************
{  全课云-班级小助手 v1.0
{  版权信息 (c) 2005-2018 广东全通教育股份有限公司. 保留所有权利.
{  创建人：  linyuewei
{  审查人：
{  模块：groupCode值记录表
{  功能描述:
{
{  ---------------------------------------------------------------------------
{  维护历史:
{  日期        维护人        维护类型
{  ---------------------------------------------------------------------------
{  2018-11-28  linyuewei        新建
{
{  ---------------------------------------------------------------------------
{  注：本模块代码由codgen代码生成工具辅助生成 http://www.oschina.net/p/codgen
{*****************************************************************************
*/

package cn.thinkjoy.qky.classgroup.param.code;

import cn.thinkjoy.qky.classgroup.param.BaseParam;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * 《groupCode值记录》 查询参数实体
 *
 * @author linyuewei
 */
public class CodeIndexParam extends BaseParam<Long> {
    private static final long serialVersionUID = 1L;

    /**
     * 字段常量——位数
     */
    public static final String F_Digit = "digit";
    /**
     * 字段常量——id
     */
    public static final String F_Code = "code";
    /**
     * 字段常量——状态 0-启用，1-停用
     */
    public static final String F_Status = "status";

    private Integer digit; //位数
    private String code; //id
    private Integer status; //状态 0-启用，1-停用

    /**
     * 默认空构造函数
     */
    public CodeIndexParam() {
        super();
    }

    /**
     * @return digit 位数
     */
    public Integer getDigit() {
        return this.digit;
    }

    /**
     * @param digit 位数
     */
    public void setDigit(Integer digit) {
        this.digit = digit;
    }

    /**
     * @return code id
     */
    public String getCode() {
        return this.code;
    }

    /**
     * @param code id
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * @return status 状态 0-启用，1-停用
     */
    public Integer getStatus() {
        return this.status;
    }

    /**
     * @param status 状态 0-启用，1-停用
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("digit", getDigit())
                .append("code", getCode())
                .append("status", getStatus())
                .toString();
    }

}
