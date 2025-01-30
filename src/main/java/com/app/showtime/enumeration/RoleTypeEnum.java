package com.app.showtime.enumeration;

public enum RoleTypeEnum {

    ADMIN("ADMIN"),
    USER("USER");

    private final String text;

    RoleTypeEnum(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

}
