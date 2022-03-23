package com.epam.tc.hw3;

import com.epam.tc.hw3.entities.User;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class BaseData {

    protected String expectedBrowserTitle = "Home Page";

    public String getExpectedBrowserTitle() {
        return expectedBrowserTitle;
    }

    public static User loadUserFromProperties() {
        Properties prop = new Properties();
        try (InputStream inputStream = BaseData.class
            .getResourceAsStream("/loginData.properties")) {

            prop.load(inputStream);
            String username = prop.getProperty("username");
            String password = prop.getProperty("password");
            String displayedName = prop.getProperty("displayedName");

            return new User(username, password, displayedName);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
