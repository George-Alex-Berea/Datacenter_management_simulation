package org.example;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

public class AlertFileHandler extends FileHandler {
    private final PrintWriter alertPw;

    public AlertFileHandler(PathTypes pathType, String fileName) throws IOException {
        super(pathType, fileName);
        this.alertPw = new PrintWriter(fileName + ".alerts");
    }

    public void executeCommand(Map<String, String> map) {
        switch (map.get("command")) {
            case "ADD EVENT" -> this.addAlert(map);
            default -> throw new IllegalArgumentException("Unknown command: " + map.get("command"));
        }
    }

    @Override
    public void closeFiles() {
        super.closeFiles();
        alertPw.close();
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
                map.get("ip_address")), alertPw);
        pw.println(
                "ADD EVENT: " + map.get("ip_address") +
                ": type = " + map.get("alert_type") +
                " && severity = " + map.get("alert_severity") +
                " && message = " + map.get("message"));
    }
}
