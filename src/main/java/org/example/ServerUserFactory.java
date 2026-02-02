package org.example;

import java.util.Map;

public class ServerUserFactory {
    public static User createUser(String type, Map<String, String> params) {
        switch (type) {
            case "Admin" -> {
                return new Admin(
                        params.get("user_name"),
                        params.get("user_role"),
                        params.get("user_email"),
                        params.get("user_department"),
                        Integer.parseInt(params.get("user_clearance_level"))
                );
            }
            case "Operator" -> {
                return new Operator(
                        params.get("user_name"),
                        params.get("user_role"),
                        params.get("user_email"),
                        params.get("user_department")
                );
            }

            case "User" -> {
                return new User(
                        params.get("user_name"),
                        params.get("user_role"),
                        params.get("user_email")
                );
            }
            default -> throw new IllegalArgumentException("Tip de utilizator necunoscut: " + type);
        }
    }
}
