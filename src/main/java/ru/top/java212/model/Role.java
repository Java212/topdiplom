package ru.top.java212.model;

public enum Role  {
    USER,
    ADMIN;

    public String getAuthority() {
        return this.name();
    }
}

