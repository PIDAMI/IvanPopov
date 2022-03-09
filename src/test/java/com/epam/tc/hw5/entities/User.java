package com.epam.tc.hw5.entities;

public class User {

    private final String login;
    private final String password;
    private final String name;

    public User(final String login, final String password, final String name) {
        this.login = login;
        this.password = password;
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }
}
