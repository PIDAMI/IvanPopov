package com.epam.tc.hw2;

import com.epam.tc.hw2.entities.User;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class BaseData {

    protected String indexPageUrl = "https://jdi-testing.github.io/jdi-light/index.html";
    protected String expectedBrowserTitle = "Home Page";

    public String getIndexPageUrl() {
        return indexPageUrl;
    }

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
