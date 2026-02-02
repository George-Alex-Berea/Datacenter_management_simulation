package org.example;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ResourceGroup {
    private List<User> members;
    private String ipAddress;
    private Server server;

    private ResourceGroup(String ipAddress) {
        this.members = new LinkedList<>();
        this.ipAddress = ipAddress;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void addMember(User user) {
        members.add(user);
    }
    public void removeMember(User user) {
        members.remove(user);
    }
    public List<User> getMembers() {
        return members;
    }

    public static class Builder {
        private List<User> members;
        private String ipAddress;
        private Server server;

        public Builder(String ipAddress) {
            this.members = new ArrayList<>();
            this.ipAddress = ipAddress;
        }

        public Builder setServer(Server server) {
            this.server = server;
            return this;
        }

        public ResourceGroup build() {
            return new ResourceGroup(ipAddress);
        }
    }
}
