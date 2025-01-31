package br.com.itau.api.security.enumeration;

public enum ClaimKey {
    NAME("Name"),
    ROLE("Role"),
    SEED("Seed");

    private final String key;

    ClaimKey(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }
}