package com.abuob.eb.enums;

/**
 * Enum which contains all available classes in the application
 */
public enum ClassEnum {

    ANIMAL("animal"),
    ART("art"),
    BIOGRAPHY("biography"),
    EVENT("event"),
    PLACE("place"),
    PLANT("plant"),
    SCIENCE("science"),
    SPORTS("sports"),
    TECHNOLOGY("technology"),
    TOPIC("topic");

    private final String value;

    ClassEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static ClassEnum findByValue(String value) {
        for (ClassEnum classEnum : values()) {
            if (classEnum.getValue().equals(value)) {
                return classEnum;
            }
        }
        return null;
    }
}