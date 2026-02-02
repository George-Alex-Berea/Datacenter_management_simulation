package org.example;

public enum PathTypes {
    GROUPS("groups"),
    LISTENER("listener"),
    SERVERS("servers");

    private final String value;

    PathTypes(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

    public static PathTypes parsePathTypes(String value) {
        return switch (value.toLowerCase()) {
            case "groups" -> GROUPS;
            case "listener" -> LISTENER;
            case "servers" -> SERVERS;
            default -> throw new IllegalArgumentException("Invalid path type");
        };
    }
}
