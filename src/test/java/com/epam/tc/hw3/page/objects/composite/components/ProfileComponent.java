package com.epam.tc.hw3.page.objects.composite.components;

import com.epam.tc.hw3.entities.User;
import com.epam.tc.hw3.page.objects.composite.AbstractBaseComponent;
import java.util.Optional;
import javax.annotation.Nullable;
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

    public void login(User user) {
        //        dropToggle = wait.until(ExpectedConditions.elementToBeClickable(dropToggle));
        profileDropToggle.click();
        passwordForm.sendKeys(user.getPassword());
        loginForm.sendKeys(user.getLogin());
        //        loginButton = wait.until(ExpectedConditions.elementToBeClickable(loginButton));
        loginButton.click();
    }


    public Optional<String> getUserNameIfDisplayed() {
        return userName.isDisplayed()
            ? Optional.of(userName.getText())
            : Optional.empty();
    }
}
