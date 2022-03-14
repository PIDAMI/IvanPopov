package com.epam.tc.hw.jdi.data.provider;

import com.epam.tc.hw.jdi.entities.User;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class UserDataProvider {

    public static User USER = loadUserFromProperties();

    private static User loadUserFromProperties() {
        Properties prop = new Properties();
        try (InputStream inputStream = User.class
            .getResourceAsStream("/loginData.properties")) {

            prop.load(inputStream);
            return new User().set(user -> {
                user.name = prop.getProperty("name");
                user.password = prop.getProperty("password");
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
