package org.example;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class AlertFileHandler extends FileHandler {
    public AlertFileHandler(PathTypes pathType, String fileName) throws IOException {
        super(pathType, fileName);
    }

    public void executeCommand(Map<String, String> map) {
        switch (map.get("command")) {
            case "ADD EVENT" -> this.addAlert(map);
            default -> throw new IllegalArgumentException("Unknown command: " + map.get("command"));
        }
    }

    public void addAlert(Map<String, String> map) {
        if (map.get("ip_address").isEmpty())
            throw new MissingIpAddressException(
                    "ADD EVENT: MissingIpAddressException: " +
                            "Server IP Address was not provided. " +
                            "## line no: " + lineNo);
        Database db = Database.getInstance();
        db.addAlert(new Alert(
                AlertTypes.parseAlertTypes(map.get("alert_type")),
                Severity.parseSeverity(map.get("alert_severity")),
                map.get("message"),
                map.get("ip_address")));
        pw.println(
                "ADD EVENT: " + map.get("ip_address") +
                ": type = " + map.get("alert_type") +
                " && severity = " + map.get("alert_severity") +
                " && message = " + map.get("message"));
    }
}
