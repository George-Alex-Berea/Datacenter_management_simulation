package org.example;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Database {
    private static Database instance;
    public static Database getInstance() {
        if (instance == null) {
            instance = new Database();
        }
        return instance;
    }

    private Map<String, Server> servers;
    private Map<String, ResourceGroup> resourceGroups;
    private Map<String, Alert> alerts;

    private Database() {
        servers = new HashMap<>();
        resourceGroups = new HashMap<>();
        alerts = new HashMap<>();
    }

    public Map<String, Server> getServers() {
        return servers;
    }

    public Map<String, ResourceGroup> getResourceGroups() {
        return resourceGroups;
    }

    public Map<String, Alert> getAlerts() {
        return alerts;
    }

    public void addServer(Server server) {
        this.servers.put(server.getIpAddress(), server);
    }
    public void addServers(Map<String, Server> servers) {
        this.servers.putAll(servers);
    }
    public void addResourceGroup(ResourceGroup resourceGroup) {
        this.resourceGroups.put(resourceGroup.getIpAddress(), resourceGroup);
    }
    public void addResourceGroups(Map<String, ResourceGroup> resourceGroups) {
        this.resourceGroups.putAll(resourceGroups);
    }
    public void addAlert(Alert alert, PrintWriter alertPw) {
        alerts.put(alert.getIpAddress(), alert);
        Server server = servers.get(alert.getIpAddress());
        server.notifyResourceGroup(alert, alertPw);
    }
}
