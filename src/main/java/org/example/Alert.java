package org.example;

public class Alert {
    private AlertTypes type;
    private Severity severity;
    private String message;
    private String ipAddress;

    public Alert(AlertTypes type, Severity severity, String message, String ipAddress) {
        this.type = type;
        this.severity = severity;
        this.message = message;
        this.ipAddress = ipAddress;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public String getMessage() {
        return message;
    }

    public Severity getSeverity() {
        return severity;
    }

    public AlertTypes getType() {
        return type;
    }
}
