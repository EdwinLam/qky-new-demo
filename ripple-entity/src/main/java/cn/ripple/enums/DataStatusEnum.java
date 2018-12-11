package cn.ripple.enums;

/**
 * @Auther: Edwin
 * @Date: 2018/12/11 13:45
 * @Description:
 */
public enum DataStatusEnum {
    /**
     * 删除
     */
    DELETED(0,"删除"),
    /**
     * 启动
     */
    ENABLED(1,"启用"),
    /**
     * 不启用
     */
    DISABLED(2,"不启用");

    /**
     * 由一个值和名称实例化对象
     * @param value
     * @param name
     */
    DataStatusEnum(int value, String name) {
        this.value = value;
        this.name = name;
    }

    private final int value;
    private final String name;

    /**
     * 取得枚举的值
     * @return
     */
    public int getValue() {
        return value;
    }

    /**
     * 取得枚举的名称
     * @return
     */
    public String getName() {
        return name;
    }


}
