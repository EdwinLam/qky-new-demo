package cn.thinkjoy.qky.classgroup.common;

/**
 * 数据状态（启用\停用）枚举
 */
public enum DataStatusEnum {

    /**
     * 启用
     */
    ENABLED(0, "启用"),
    /**
     * 停用
     */
    DISABLED(1, "停用");

    /**
     * 由一个值和名称实例化对象
     *
     * @param value
     * @param name
     */
    private DataStatusEnum(int value, String name) {
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
        DataStatusEnum[] types = DataStatusEnum.values();
        for (int i = 0; i < types.length; i++) {
            if (types[i].getValue() == value)
                return types[i].getName();
        }
        return "";
    }
}
