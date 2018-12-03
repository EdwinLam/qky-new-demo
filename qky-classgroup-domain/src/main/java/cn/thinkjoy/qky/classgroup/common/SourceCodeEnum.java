package cn.thinkjoy.qky.classgroup.common;

/**
 * 来源标识编码,通常是sourceId所对应的表名，如biz_notice表示通知；biz_enroll表示报名；
 */
public enum SourceCodeEnum {

    /**
     * 无限制
     */
    NOTICE("biz_notice", "通知"),
    ENROLL("biz_enroll", "报名");

    /**
     * 由一个值和名称实例化对象
     *
     * @param value
     * @param name
     */
    SourceCodeEnum(String value, String name) {
        this.value = value;
        this.name = name;
    }

    private final String value;
    private final String name;

    /**
     * 取得枚举的值
     *
     * @return
     */
    public String getValue() {
        return value;
    }

    /**
     * 取得枚举的名称
     *
     * @return
     */
    public String getName() {
        return name;
    }
}
