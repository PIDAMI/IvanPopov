package com.epam.tc.hw.api2.service;

import static io.restassured.RestAssured.given;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;
import org.testng.annotations.BeforeSuite;

public class BaseTrelloService {

    protected static String KEY;
    protected static String SECRET;
    protected static String TOKEN;
    protected static String DOMAIN;
    protected static String PORT;
    private static final String AUTH_HEADER = "OAuth oauth_consumer_key=\"%s\", oauth_token=\"%s\"";
    private static final String GET_BOARD_BY_ID = "board/%l";

    private RequestSpecification requestSpecification;

    public BaseTrelloService() {
        requestSpecification = new RequestSpecBuilder()
            .setBaseUri(DOMAIN)
            .addHeader("Authorization", String.format(AUTH_HEADER, KEY, TOKEN))
            .build();
    }

    @BeforeSuite
    public void beforeSuite() {
        loadCreditentials();
        loadProperties();
    }

    public Response getNoParams(String uri) {
        return given(requestSpecification).get(uri);
    }

    public Response getWithParams(String uri, Map<String, Object> params) {
        return given(requestSpecification).params(params).get(uri);
    }

    private static void loadProperties() {
        Properties prop = new Properties();
        try (InputStream inputStream = BaseTrelloService
            .class
            .getResourceAsStream("/test.properties")) {

            prop.load(inputStream);
            KEY = prop.getProperty("key");
            SECRET = prop.getProperty("secret");
            TOKEN = prop.getProperty("token");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void loadCreditentials() {
        Properties prop = new Properties();
        try (InputStream inputStream = BaseTrelloService
            .class
            .getResourceAsStream("/creditentials.properties")) {

            prop.load(inputStream);
            DOMAIN = prop.getProperty("domain");
            PORT = prop.getProperty("1");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
