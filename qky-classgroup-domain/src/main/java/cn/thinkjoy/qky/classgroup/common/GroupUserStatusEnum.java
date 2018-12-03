package cn.thinkjoy.qky.classgroup.common;

/**
 * 成员状态，审核不通过时，移除记录，只保留两种状态；0：待审核，1：正式成员；
 */
public enum GroupUserStatusEnum {

    /**
     * 待审核
     */
    TO_CHECK(0, "待审核"),
    FULL_MEMBER(1, "正式会员");

    /**
     * 由一个值和名称实例化对象
     *
     * @param value
     * @param name
     */
    private GroupUserStatusEnum(int value, String name) {
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
        GroupUserStatusEnum[] types = GroupUserStatusEnum.values();
        for (int i = 0; i < types.length; i++) {
            if (types[i].getValue() == value)
                return types[i].getName();
        }
        return "";
    }
}
