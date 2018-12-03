package cn.thinkjoy.qky.classgroup.common;

/**
 * 成员是否要审核，1：是；0：否；开启时，所加入的成员需审核后才能加入群组。
 */
public enum GroupNeeCheckEnum {
    /**
     * 是
     */
    YES(1, "是"),
    /**
     * 否
     */
    NO(1, "否");

    /**
     * 由一个值和名称实例化对象
     *
     * @param value
     * @param name
     */
    private GroupNeeCheckEnum(int value, String name) {
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
        GroupNeeCheckEnum[] types = GroupNeeCheckEnum.values();
        for (int i = 0; i < types.length; i++) {
            if (types[i].getValue() == value)
                return types[i].getName();
        }
        return "";
    }
}
