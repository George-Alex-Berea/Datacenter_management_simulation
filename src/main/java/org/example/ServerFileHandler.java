package org.example;

import java.io.IOException;
import java.util.Map;

public class ServerFileHandler extends FileHandler {
    public ServerFileHandler(PathTypes pathType, String fileName) throws IOException {
        super(pathType, fileName);
    }

    public void executeCommand(Map<String, String> map) {
        switch (map.get("command")) {
            case "ADD SERVER" -> this.addServerExecute(map);
            default -> throw new IllegalArgumentException("Unknown command: " + map.get("command"));
        }
    }

    public void addServerExecute(Map<String, String> map) {
        Server server = Server.getFromMap(map, lineNo);
        Database db = Database.getInstance();
        db.addServer(server);
        pw.println("ADD SERVER: " + server.getIpAddress() + ": " + server.getStatus());
    }
}
