package com.evilgeniuses.sistemicpublications;

public class AuthorsModel {

    private String name;
    private String email;
    private String membership;
    private boolean active;

    public AuthorsModel(){}

    public AuthorsModel(String name, String email, String membership, boolean active) {
        this.name = name;
        this.email = email;
        this.membership = membership;
        this.active = active;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMembership() {
        return membership;
    }

    public void setMembership(String membership) {
        this.membership = membership;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
