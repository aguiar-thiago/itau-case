package br.com.itau.api.security.enumeration;

public enum ClaimKeyEnum {
    NAME("Name"),
    ROLE("Role"),
    SEED("Seed");

    private final String key;

    ClaimKeyEnum(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }
}