package com.epam.tc.hw3.entities;

public class User {

    private final String login;
    private final String password;
    private final String displayedName;

    public User(final String login, final String password, final String displayedName) {
        this.login = login;
        this.password = password;
        this.displayedName = displayedName;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getDisplayedName() {
        return displayedName;
    }

}
