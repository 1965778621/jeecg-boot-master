package org.jeecg.modules.demo.cms.client;

/**
 * @author ZH
 */

public enum TopicTypeEnum {
    JUDGE_TOPIC("判断题", 3),
    multiple_Choice("多选题", 2),
    single_Choice("单选题", 1);

    private String type;
    private int value;

    TopicTypeEnum(String type, int value) {
        this.type = type;
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
