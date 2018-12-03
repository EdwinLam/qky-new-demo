package cn.thinkjoy.qky.classgroup.common;

/**
 * 查询匹配类型枚举
 *
 * @author tengen on 2016/1/15.
 */
public enum MatchTypeEnum {
    /**
     * 精确匹配 content
     */
    EXACT,
    /**
     * 左匹配 模糊查询 %content
     */
    LEFT_FUZZY,
    /**
     * 右匹配 模糊查询 content%
     */
    RIGHT_FUZZY,
    /**
     * 模糊查询 %content%
     */
    ALL_FUZZY;
}
