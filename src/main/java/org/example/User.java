package org.example;

public class User {
    private String name;
    private String role;
    private String email;
    private ResourceGroup resourceGroup;

    public String getName() {
        return name;
    }

    public String getRole() {
        return role;
    }

    public String getEmail() {
        return email;
    }

    public User(String name, String role, String email) {
        this.name = name;
        this.role = role;
        this.email = email;
    }

    public void setResourceGroup(ResourceGroup resourceGroup) {
        this.resourceGroup = resourceGroup;
        resourceGroup.addMember(this);
    }

    public void recognizeAlert(Alert alert) {
        System.out.println(this.name + ", " + this.role + " has recognized alert of severity " + alert.getSeverity() + ": " + alert.getMessage());
    }
}
