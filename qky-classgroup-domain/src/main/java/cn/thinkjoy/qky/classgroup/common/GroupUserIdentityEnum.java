package cn.thinkjoy.qky.classgroup.common;

/**
 * 用户在群组中的身份，1：群主（创建人) ；2：管理员；3：普通用户。群组创建人默认是群主，管理员由群主设置。
 */
public enum GroupUserIdentityEnum {
    GROUP_OWNER(1, "群主（创建人)"),
    ADMIN(2, "管理员"),
    NORMAL_USER(3, "普通用户");

    /**
     * 由一个值和名称实例化对象
     *
     * @param value
     * @param name
     */
    private GroupUserIdentityEnum(int value, String name) {
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
        GroupUserIdentityEnum[] types = GroupUserIdentityEnum.values();
        for (int i = 0; i < types.length; i++) {
            if (types[i].getValue() == value)
                return types[i].getName();
        }
        return "";
    }
}
