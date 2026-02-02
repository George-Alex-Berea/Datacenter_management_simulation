package org.example;

import java.util.Map;

public class Server {
    private String ipAddress;
    private Location location;
    private User owner;
    private String hostname;
    private ServerStatus status;
    private int cpuCores;
    private int ramGb;
    private int storageGb;
    private ResourceGroup resourceGroup;

    private Server(Builder b) {
        this.ipAddress = b.ipAddress;
        this.location = b.location;
        this.owner = b.owner;
        this.hostname = b.hostname;
        this.status = b.status;
        this.cpuCores = b.cpuCores;
        this.ramGb = b.ramGb;
        this.storageGb = b.storageGb;
    }

    public ServerStatus getStatus() {
        return status;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setResourceGroup(ResourceGroup resourceGroup) {
        this.resourceGroup = resourceGroup;
    }

    public void notifyResourceGroup(Alert alert) {
        //this.resourceGroup;
    }

    public static class Builder {
        private String ipAddress;
        private Location location;
        private User owner;
        private String hostname;
        private ServerStatus status;
        private int cpuCores;
        private int ramGb;
        private int storageGb;

        public Builder(String ipAddress, Location location, User owner) {
            this.ipAddress = ipAddress;
            this.location = location;
            this.owner = owner;
        }

        public Builder setHostname(String hostname) {
            this.hostname = hostname;
            return this;
        }
        public Builder setStatus(ServerStatus status) {
            this.status = status;
            return this;
        }
        public Builder setCpuCores(int cpuCores) {
            this.cpuCores = cpuCores;
            return this;
        }
        public Builder setRamGb(int ramGb) {
            this.ramGb = ramGb;
            return this;
        }
        public Builder setStorageGb(int storageGb) {
            this.storageGb = storageGb;
            return this;
        }
        public Server build() {
            return new Server(this);
        }
    }

    public static Server getFromMap(Map<String, String> map, int lineNo) {
        if (map.get("ip_address").isEmpty())
            throw new MissingIpAddressException("ADD SERVER: MissingIpAddressException: Server IP Address was not provided. ## line no: " + lineNo);

        if (map.get("country").isEmpty())
            throw new LocationException("ADD SERVER: LocationException: Country is missing. ## line no: " + lineNo);

        if (map.get("user_role").isEmpty() || map.get("user_name").isEmpty())
            throw new LocationException("ADD SERVER: UserException: Name and role can't be empty. ## line no: " + lineNo);

        Location.Builder lBuilder = new Location.Builder(map.get("country"));
        if (!map.get("city").isEmpty())
            lBuilder.setCity(map.get("city"));
        if (!map.get("address").isEmpty())
            lBuilder.setAddress(map.get("address"));
        if (!map.get("latitude").isEmpty() && !map.get("longitude").isEmpty())
            lBuilder.setCoordinates(Double.parseDouble(map.get("latitude")), Double.parseDouble(map.get("longitude")));
        Location location = lBuilder.build();

        User user = ServerUserFactory.createUser(map.get("user_role"), map);

        Server.Builder sBuilder = new Server.Builder(map.get("ip_address"), location, user);
        if (!map.get("hostname").isEmpty())
            sBuilder.setHostname(map.get("hostname"));
        if (!map.get("server_status").isEmpty())
            sBuilder.setStatus(ServerStatus.parseServerStatus(map.get("server_status")));
        if (!map.get("cpu_cores").isEmpty())
            sBuilder.setCpuCores(Integer.parseInt(map.get("cpu_cores")));
        if (!map.get("ram_gb").isEmpty())
            sBuilder.setRamGb(Integer.parseInt(map.get("ram_gb")));
        if (!map.get("storage").isEmpty())
            sBuilder.setStorageGb(Integer.parseInt(map.get("storage")));

        return sBuilder.build();
    }
}
