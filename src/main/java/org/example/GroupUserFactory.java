package org.example;

import java.util.Map;

public class GroupUserFactory {
    public static User createUser(String type, Map<String, String> params) {
        User user;
        switch (type) {
            case "Admin" ->
                user = new Admin(
                        params.get("use_name"),
                        params.get("user_role"),
                        params.get("user_email"),
                        params.get("department"),
                        Integer.parseInt(params.get("clearance_level"))
                );

            case "Operator" ->
                user = new Operator(
                        params.get("use_name"),
                        params.get("user_role"),
                        params.get("user_email"),
                        params.get("department")
                );

            case "User" ->
                user = new User(
                        params.get("use_name"),
                        params.get("user_role"),
                        params.get("user_email")
                );
            default -> throw new IllegalArgumentException("Tip de utilizator necunoscut: " + type);
        }
        Database db = Database.getInstance();
        ResourceGroup resourceGroup = db.getResourceGroups().get(params.get("ip_address"));
        resourceGroup.addMember(user);
        return user;
    }
}
