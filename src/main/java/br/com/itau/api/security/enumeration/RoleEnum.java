package br.com.itau.api.security.enumeration;

public enum RoleEnum {
    ADMIN("Admin"),
    MEMBER("Member"),
    EXTERNAL("External");

    private final String value;

    RoleEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}