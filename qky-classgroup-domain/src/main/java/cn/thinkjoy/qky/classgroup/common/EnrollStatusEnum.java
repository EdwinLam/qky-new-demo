package cn.thinkjoy.qky.classgroup.common;

/**
 * @Auther: Edwin
 * @Date: 2018/11/29 14:22
 * @Description:
 */
public enum EnrollStatusEnum {
    /**
     * 无限制
     */
    NO_ENROLL(0, "未报名"),
    FINISH_ENROLL(1, "已报名"),
    END_ENROLL(2, "报名已截止"),
    FUll_ENROLL(3, "报名名额已满");

    /**
     * 由一个值和名称实例化对象
     *
     * @param value
     * @param name
     */
    EnrollStatusEnum(int value, String name) {
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
