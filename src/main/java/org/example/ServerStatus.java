package org.example;

import java.util.Locale;

public enum ServerStatus {
    UP("up"),
    DEGRADED("degraded"),
    DOWN("down");

    private final String value;

    ServerStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

    public static ServerStatus parseServerStatus(String value) {
        switch (value.toUpperCase()) {
            case "UP" -> {
                return UP;
            }
            case "DEGRADED" -> {
                return DEGRADED;
            }
            case "DOWN" -> {
                return DOWN;
            }
            default -> throw new IllegalArgumentException("Unknown server status: " + value);
        }
    }
}
