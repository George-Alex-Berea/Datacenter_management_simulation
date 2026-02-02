package org.example;

public enum AlertTypes {
    ANOMALY("anomaly"),
    ADVISORY("advisory");

    private final String value;

    AlertTypes(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

    public static AlertTypes parseAlertTypes(String value) {
        return switch (value.toLowerCase()) {
            case "anomaly" -> ANOMALY;
            case "advisory" -> ADVISORY;
            default -> null;
        };
    }
}
