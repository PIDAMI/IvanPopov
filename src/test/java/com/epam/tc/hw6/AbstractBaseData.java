package com.epam.tc.hw6;

import com.epam.tc.hw6.entities.User;
import io.qameta.allure.Step;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public abstract class AbstractBaseData {

    protected String expectedBrowserTitle = "Home Page";

    public String getExpectedBrowserTitle() {
        return expectedBrowserTitle;
    }

    @Step("load user from properties")
    public static User loadUserFromProperties() {
        Properties prop = new Properties();
        try (InputStream inputStream = AbstractBaseData.class
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
