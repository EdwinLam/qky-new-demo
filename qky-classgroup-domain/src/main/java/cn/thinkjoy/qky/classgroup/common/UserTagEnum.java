package cn.thinkjoy.qky.classgroup.common;

/**
 * 用户类型枚举
 */
public enum UserTagEnum {

    IS_TEACHER("1", "老师"),
    IS_PARENT("2", "家长"),
    IS_STUDENT("4", "学生"),
    IS_EDUORG("8", "教育机构");
    /**
     * The code.
     */
    private final String tagValue;

    /**
     * The message.
     */
    private final String name;

    /**
     * Instantiates a new error type.
     */
    private UserTagEnum(String tagValue, String name) {
        this.tagValue = tagValue;
        this.name = name;
    }

    /**
     * Gets the code.
     *
     * @return the code
     */
    public String getTagValue() {
        return tagValue;
    }

    /**
     * Gets the message.
     *
     * @return the message
     */
    public String getName() {
        return name;
    }

}
