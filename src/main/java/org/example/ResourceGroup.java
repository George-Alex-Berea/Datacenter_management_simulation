package org.example;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ResourceGroup {
    private List<User> members;
    private String ipAddress;
    private Server server;

    private ResourceGroup(Builder b) {
        this.members = b.members;
        this.ipAddress = b.ipAddress;
        this.server = b.server;
        if (this.server != null)
            this.server.setResourceGroup(this);
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

    public void notifyMembers(Alert alert, PrintWriter alertPw) {
        for (User user : this.members) {
            user.recognizeAlert(alert, alertPw);
        }
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
            return new ResourceGroup(this);
        }
    }
}
