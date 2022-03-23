package com.epam.tc.hw4.page.objects.voids;

import static com.epam.tc.hw4.AbstractBaseTest.TIMEOUT_SECONDS;

import com.epam.tc.hw4.page.objects.composite.components.BottomPartComponent;
import com.epam.tc.hw4.page.objects.composite.components.HeaderComponent;
import com.epam.tc.hw4.page.objects.composite.components.LeftSectionComponent;
import com.epam.tc.hw4.page.objects.composite.components.ProfileComponent;
import io.qameta.allure.Step;
import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class JdiIndexVoidPage {

    private final WebDriver driver;
    private final WebDriverWait wait;
    private final ProfileComponent profile;
    private final HeaderComponent header;
    private final BottomPartComponent bottomPart;
    private final LeftSectionComponent leftSection;
    private final String indexPageUrl = "https://jdi-testing.github.io/jdi-light/index.html";

    @FindBy(tagName = "iframe")
    private List<WebElement> frames;

    @FindBy(xpath = "//*[@class='uui-navigation nav navbar-nav m-l8']"
        + "//*[contains(text(),'Service')]")
    private WebElement serviceButton;

    @FindBy(xpath = "//*[@class='uui-navigation nav navbar-nav m-l8']"
        + "//*[contains(text(),'Different elements')]")
    private WebElement differentElementsButton;

    public JdiIndexVoidPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT_SECONDS));
        PageFactory.initElements(driver, this);
        profile = new ProfileComponent(driver);
        header = new HeaderComponent(driver);
        bottomPart = new BottomPartComponent(driver);
        leftSection = new LeftSectionComponent(driver);
        driver.get(indexPageUrl);
    }

    public ProfileComponent profile() {
        return this.profile;
    }

    public HeaderComponent header() {
        return header;
    }

    public BottomPartComponent bottomPart() {
        return bottomPart;
    }

    public LeftSectionComponent leftSection() {
        return leftSection;
    }

    public String getPageTitle() {
        return driver.getTitle();
    }

    // throws NoSuchElementException if such frame doesn't exist
    @Step("Switching to frame with button with value {buttonValue}")
    public void switchToFrameWithButton(final String buttonValue) {
        for (WebElement frame : frames) {
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frame));
            List<WebElement> frameButtons = driver.findElements(By.xpath(
                String.format("//*[@value='%s']", buttonValue))
            );
            int numButtons = frameButtons.size();
            if (numButtons > 0) {
                return;
            }
            switchToMainWindow();
        }
        throw new NoSuchElementException(String.format("frame with button with value %s not found", buttonValue));
    }


    public void switchToMainWindow() {
        driver.switchTo().defaultContent();
    }

    @Step("Go to Different Elements page")
    public void gotoDifferentElementPage() {
        serviceButton.click();
        differentElementsButton.click();
    }

}
