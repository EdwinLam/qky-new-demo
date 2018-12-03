package cn.thinkjoy.qky.classgroup.common;

/**
 * 阅读状态（0：未读；1：已读)
 */
public enum ReadStatusEnum {

    /**
     * 无限制
     */
    UNREAD(0, "未读"),
    READ(1, "已读");

    /**
     * 由一个值和名称实例化对象
     *
     * @param value
     * @param name
     */
    ReadStatusEnum(int value, String name) {
        this.value = value;
        this.name = name;
    }

    private final int value;
    private final String name;

    /**
     * 取得枚举的值
     *
     * @return
     */
    public int getValue() {
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
