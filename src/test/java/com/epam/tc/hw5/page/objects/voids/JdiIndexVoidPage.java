package com.epam.tc.hw5.page.objects.voids;

import com.epam.tc.hw5.page.objects.composite.components.HeaderComponent;
import com.epam.tc.hw5.page.objects.composite.components.ProfileComponent;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class JdiIndexVoidPage extends AbstractBaseVoidPage {

    private final ProfileComponent profile;
    private final HeaderComponent header;
    private final String indexPageUrl = "https://jdi-testing.github.io/jdi-light/index.html";

    // these locators can't be simplified
    // as there are 2 navigation bars w/ very similar long class locators
    @FindBy(xpath = "//ul[@class='uui-navigation nav navbar-nav m-l8']"
        + "//*[contains(text(),'Service')]")
    private WebElement serviceButton;

    @FindBy(xpath = "//ul[@class='uui-navigation nav navbar-nav m-l8']"
        + "//*[contains(text(),'Different elements')]")
    private WebElement differentElementsButton;

    public JdiIndexVoidPage() {
        super();
        PageFactory.initElements(driver, this);
        profile = new ProfileComponent(driver);
        header = new HeaderComponent(driver);
    }

    public ProfileComponent profile() {
        return this.profile;
    }

    public HeaderComponent header() {
        return header;
    }

    public String getUrl() {
        return indexPageUrl;
    }

    @Step("Go to Different Elements page")
    public void gotoDifferentElementPage() {
        serviceButton.click();
        differentElementsButton.click();
    }
}
