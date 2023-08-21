package com.ryan.openapi.model.enums;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * enum of interface information
 *
 * @author ryan
 */
public enum InterfaceStatusEnum {

    OFFLINE("offline", 0),
    ONLINE("online", 1);

    private final String text;

    private final int value;

    InterfaceStatusEnum(String text, int value) {
        this.text = text;
        this.value = value;
    }

    /**
     * get values
     *
     * @return
     */
    public static List<Integer> getValues() {
        return Arrays.stream(values()).map(item -> item.value).collect(Collectors.toList());
    }

    public int getValue() {
        return value;
    }

    public String getText() {
        return text;
    }
}
