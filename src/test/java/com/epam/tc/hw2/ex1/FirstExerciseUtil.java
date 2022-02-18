package com.epam.tc.hw2.ex1;

import static org.assertj.core.api.Assertions.assertThat;

import com.epam.tc.hw2.BaseUtil;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FirstExerciseUtil extends BaseUtil {


    public FirstExerciseUtil(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public static final String IMAGES_CSS_LOCATORS =
        "div.row.clerafix.benefits > div.col-sm-3 > div.benefit > div.benefit-icon > span.icons-benefit";
    public static final String TEXT_AFTER_IMAGES_CSS_LOCATORS =
        "div.row.clerafix.benefits > div.col-sm-3 > div.benefit > span.benefit-txt";
    public static final String HEADER_NAVIGATION_BUTTONS_CSS_LOCATORS =
        "nav > ul.uui-navigation.nav.navbar-nav.m-l8 > li";
    private static final String LEFT_SECTION_ELEMENT_CSS_SELECTOR =
        "ul.sidebar-menu.left > li > a > span";

    // 5. Assert that there are 4 items on the header section
    // are displayed and they have proper texts;
    public void checkAllHeadersItemsDisplayed(List<String> expectedHeaderButtonsText) {
        List<WebElement> headerNavigationButtons = driver.findElements(
            By.cssSelector(HEADER_NAVIGATION_BUTTONS_CSS_LOCATORS)
        );
        assertThat(headerNavigationButtons.size()).isEqualTo(expectedHeaderButtonsText.size());
        List<String> headerButtonsActualText = headerNavigationButtons
            .stream()
            .map(WebElement::getText)
            .sorted()
            .collect(Collectors.toList());

        assertThat(headerButtonsActualText).isEqualTo(expectedHeaderButtonsText);
        headerNavigationButtons.forEach(el -> assertThat(el.isDisplayed()).isTrue());
    }

    // 6. Assert that there are 4 images on the Index Page
    // and they are displayed;
    // 7. Assert that there are 4 texts on the Index Page
    // under icons and they have proper text;
    public void checkImagesDisplayedAndHaveTextBelow(Map<String, String> imageCssLocatorToTextAfterImage) {
        // locators are same for all images in bottom part
        List<WebElement> images = driver.findElements(By.cssSelector(IMAGES_CSS_LOCATORS));
        List<WebElement> textAfterImages = driver.findElements(By.cssSelector(TEXT_AFTER_IMAGES_CSS_LOCATORS));
        assertThat(images.size()).isEqualTo(imageCssLocatorToTextAfterImage.size());
        assertThat(images.size()).isEqualTo(textAfterImages.size());

        for (int i = 0; i < images.size(); i++) {
            String imageClassName = images.get(i).getAttribute("class");
            String actualText = textAfterImages.get(i).getText();
            String expectedText = imageCssLocatorToTextAfterImage.get(imageClassName);
            assertThat(actualText).isEqualTo(expectedText);
        }

    }

    // 8. Assert that there is the iframe with “Frame Button” exist;
    // 9. Switch to the iframe and check that there is “Frame Button” in the iframe;
    // there's noway to check if frame w/ button exists but to
    // switch to every frame and check if there's a button in some frame
    // so making sure 8 is true necessarily implies that actions in 9 are already done
    public void checkFrameWithButtonExists(String frameButtonValue) {
        List<WebElement> frames = driver.findElements(By.tagName("iframe"));
        boolean frameWithButtonExists = false;
        for (WebElement frame : frames) {
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frame));
            List<WebElement> frameButtons = driver.findElements(By.xpath(
                String.format("//*[@value='%s']", frameButtonValue))
            );
            int numButtons = frameButtons.size();
            switchToOriginalWindow(driver);
            if (numButtons > 0) {
                frameWithButtonExists = true;
                break;
            }
        }
        assertThat(frameWithButtonExists).isTrue();
    }

    // 11. Assert that there are 5 items in the Left Section
    // are displayed and they have proper text;
    public void checkLeftSectionHasItemsWithProperText(List<String> expectedLeftSectionItemsText) {
        // go directly to tag w/ text
        List<WebElement> leftSectionElements = driver.findElements(
            By.cssSelector(LEFT_SECTION_ELEMENT_CSS_SELECTOR)
        );
        assertThat(leftSectionElements.size()).isEqualTo(expectedLeftSectionItemsText.size());
        List<String> leftSectionElementsTexts = leftSectionElements
            .stream()
            .map(WebElement::getText)
            .sorted()
            .collect(Collectors.toList());
        assertThat(leftSectionElementsTexts).isEqualTo(expectedLeftSectionItemsText);
    }

    // 10. Switch to original window back;
    // used in checkFrameWithButtonExists multiple times
    // to switch from frame to original window
    public void switchToOriginalWindow(WebDriver driver) {
        driver.switchTo().defaultContent();
    }
}
