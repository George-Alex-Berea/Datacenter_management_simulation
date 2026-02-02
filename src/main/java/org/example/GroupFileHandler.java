package org.example;

import java.io.IOException;
import java.util.Map;

public class GroupFileHandler extends FileHandler {
    public GroupFileHandler(PathTypes pathType, String fileName) throws IOException {
        super(pathType, fileName);
    }

    public void executeCommand(Map<String, String> map) {
        switch (map.get("command")) {
            case "ADD GROUP" -> this.addGroupExecute(map);
            case "FIND GROUP" -> this.findGroupExecute(map);
            case "REMOVE GROUP" -> this.removeGroupExecute(map);
            case "ADD MEMBER" -> this.addMemberExecute(map);
            case "FIND MEMBER" -> this.findMemberExecute(map);
            case "REMOVE MEMBER" -> this.removeMemberExecute(map);
            default -> throw new IllegalArgumentException("Unknown command: " + map.get("command"));
        }
    }

    public void addGroupExecute(Map<String, String> map) {
        if (map.get("ip_address").isEmpty())
            throw new MissingIpAddressException(
                    "ADD GROUP: MissingIpAddressException: " +
                            "Server IP Address was not provided. " +
                            "## line no: " + lineNo);
        Database db = Database.getInstance();
        ResourceGroup.Builder rgBuilder = new ResourceGroup.Builder(map.get("ip_address"));

        if (db.getServers().containsKey(map.get("ip_address")))
            rgBuilder.setServer(db.getServers().get(map.get("ip_address")));

        db.addResourceGroup(rgBuilder.build());
        pw.println("ADD GROUP: " + map.get("ip_address"));
    }

    public void findGroupExecute(Map<String, String> map) {
        if (map.get("ip_address").isEmpty())
            throw new MissingIpAddressException(
                    "FIND GROUP: MissingIpAddressException: " +
                            "Server IP Address was not provided. " +
                            "## line no: " + lineNo);
        Database db = Database.getInstance();
        if (db.getResourceGroups().containsKey(map.get("ip_address"))) {
            pw.println("FIND GROUP: " + map.get("ip_address"));
        } else {
            pw.println("FIND GROUP: Group not found: ipAddress = " + map.get("ip_address"));
        }
    }

    public void removeGroupExecute(Map<String, String> map) {
        if (map.get("ip_address").isEmpty())
            throw new MissingIpAddressException(
                    "REMOVE GROUP: MissingIpAddressException: " +
                            "Server IP Address was not provided. " +
                            "## line no: " + lineNo);
        Database db = Database.getInstance();
        ResourceGroup resourceGroup = db.getResourceGroups().remove(map.get("ip_address"));
        if (resourceGroup != null) {
            pw.println("REMOVE GROUP: " + map.get("ip_address"));
        } else {
            pw.println("REMOVE GROUP: Group not found: ipAddress = " + map.get("ip_address"));
        }
    }

    public void addMemberExecute(Map<String, String> map) {
        if (map.get("ip_address").isEmpty())
            throw new MissingIpAddressException(
                    "ADD MEMBER: MissingIpAddressException: " +
                            "Server IP Address was not provided. " +
                            "## line no: " + lineNo);
        Database db = Database.getInstance();
        ResourceGroup resourceGroup = db.getResourceGroups().get(map.get("ip_address"));
        if (resourceGroup != null) {
            GroupUserFactory.createUser(map.get("user_role"), map);
            pw.println("ADD MEMBER: " + map.get("ip_address") +
                    ": name = " + map.get("use_name") +
                    " && role = " + map.get("user_role"));
        } else {
            pw.println("ADD MEMBER: Group not found: ipAddress = " + map.get("ip_address"));
        }
    }

    public void findMemberExecute(Map<String, String> map) {
        if (map.get("ip_address").isEmpty())
            throw new MissingIpAddressException(
                    "FIND MEMBER: MissingIpAddressException: " +
                            "Server IP Address was not provided. " +
                            "## line no: " + lineNo);
        if (map.get("user_role").isEmpty() || map.get("use_name").isEmpty())
            throw new UserException(
                    "FIND MEMBER: UserException: " +
                            "Name and role can't be empty. " +
                            "## line no: " + lineNo);
        Database db = Database.getInstance();
        ResourceGroup resourceGroup = db.getResourceGroups().get(map.get("ip_address"));
        if (resourceGroup == null) {
            pw.println("FIND MEMBER: Group not found: ipAddress = " + map.get("ip_address"));
            return;
        }
        for (User member : resourceGroup.getMembers()) {
            if (member.getName().equals(map.get("use_name")) && member.getRole().equals(map.get("user_role"))) {
                pw.println("FIND MEMBER: " + map.get("ip_address") +
                        ": name = " + map.get("use_name") +
                        " && role = " + map.get("user_role"));
                return;
            }
        }
        pw.println("FIND MEMBER: Member not found: ipAddress = " + map.get("ip_address") +
                ": name = " + map.get("use_name") +
                " && role = " + map.get("user_role"));
    }

    public void removeMemberExecute(Map<String, String> map) {
        if (map.get("ip_address").isEmpty())
            throw new MissingIpAddressException(
                    "REMOVE MEMBER: MissingIpAddressException: " +
                            "Server IP Address was not provided. " +
                            "## line no: " + lineNo);
        if (map.get("user_role").isEmpty() || map.get("use_name").isEmpty())
            throw new UserException(
                    "REMOVE MEMBER: UserException: " +
                            "Name and role can't be empty. " +
                            "## line no: " + lineNo);
        Database db = Database.getInstance();
        ResourceGroup resourceGroup = db.getResourceGroups().get(map.get("ip_address"));
        if (resourceGroup == null) {
            pw.println("REMOVE MEMBER: Group not found: ipAddress = " + map.get("ip_address"));
            return;
        }
        for (User member : resourceGroup.getMembers()) {
            if (member.getName().equals(map.get("use_name")) && member.getRole().equals(map.get("user_role"))) {
                resourceGroup.removeMember(member);
                pw.println("REMOVE MEMBER: " + map.get("ip_address") +
                        ": name = " + map.get("use_name") +
                        " && role = " + map.get("user_role"));
                return;
            }
        }
        pw.println("REMOVE MEMBER: Member not found: ipAddress = " + map.get("ip_address") +
                ": name = " + map.get("use_name") +
                " && role = " + map.get("user_role"));
    }
}
