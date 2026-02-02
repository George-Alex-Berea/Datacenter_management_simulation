package org.example;

public enum Severity {
    LOW("low"),
    MEDIUM("medium"),
    HIGH("high"),
    CRITICAL("critical");

    private final String value;

    Severity(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

    public static Severity parseSeverity(String value) {
        return switch (value.toLowerCase()) {
            case "low" -> LOW;
            case "medium" -> MEDIUM;
            case "high" -> HIGH;
            case "critical" -> CRITICAL;
            default -> null;
        };
    }
}
