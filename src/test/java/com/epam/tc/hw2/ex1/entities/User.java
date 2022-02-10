package com.epam.tc.hw2.ex1.entities;

public class User {

    private final String login;
    private final String password;

    public User(final String login, final String password) {
        this.login = login;
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

}
