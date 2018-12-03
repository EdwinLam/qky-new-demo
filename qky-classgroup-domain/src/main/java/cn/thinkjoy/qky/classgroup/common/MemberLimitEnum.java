package cn.thinkjoy.qky.classgroup.common;

/**
 * 成员人数限制,-1表示无限制
 */
public enum MemberLimitEnum {

    /**
     * 无限制
     */
    NO_LIMIT(-1, "无限制");

    /**
     * 由一个值和名称实例化对象
     *
     * @param value
     * @param name
     */
    private MemberLimitEnum(int value, String name) {
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

    /**
     * 根据枚举值获取对应的名称
     *
     * @param value
     * @return
     */
    public static String getNameByValue(int value) {
        MemberLimitEnum[] types = MemberLimitEnum.values();
        for (int i = 0; i < types.length; i++) {
            if (types[i].getValue() == value)
                return types[i].getName();
        }
        return "";
    }
}
