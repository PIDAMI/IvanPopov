package com.epam.tc.hw5.page.objects.composite.components;

import com.epam.tc.hw5.entities.User;
import com.epam.tc.hw5.page.objects.composite.AbstractBaseComponent;
import io.qameta.allure.Step;
import java.util.Optional;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProfileComponent extends AbstractBaseComponent {

    @FindBy(css = "li.uui-profile-menu")
    private WebElement profileDropToggle;

    @FindBy(css = "input#name")
    private WebElement loginForm;

    @FindBy(css = "input#password")
    private WebElement passwordForm;

    @FindBy(css = "button#login-button")
    private WebElement loginButton;

    @FindBy(css = "span#user-name")
    private WebElement userName;

    public ProfileComponent(WebDriver driver) {
        super(driver);
    }

    @Step("logging in with login {user.login}:{user.password}")
    public void login(User user) {
        profileDropToggle.click();
        passwordForm.sendKeys(user.getPassword());
        loginForm.sendKeys(user.getLogin());
        loginButton.click();
    }

    @Step("Getting logged user's displayed name")
    public String getUserNameIfDisplayed() {
        return userName.getText();
    }
}
