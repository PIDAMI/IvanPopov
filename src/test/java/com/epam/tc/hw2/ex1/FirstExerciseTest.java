package com.epam.tc.hw2.ex1;

import static java.util.Map.entry;
import static org.assertj.core.api.Assertions.assertThat;

import com.epam.tc.hw2.BaseTest;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;

public class FirstExerciseTest extends BaseTest {

    private final Map<String, String> imageCssLocatorToTextAfterImage = Map.ofEntries(
        entry(".icons-benefit.icon-practise",
            "To include good practices\nand ideas from successful\nEPAM project"),
        entry(".icons-benefit.icon-custom",
            "To be flexible and\ncustomizable"),
        entry(".icons-benefit.icon-multi",
            "To be multiplatform"),
        entry(".icons-benefit.icon-base",
            "Already have good base\n(about 20 internal and"
            + "\nsome external projects),\nwish to get more…")
        );

    private final List<String> expectedHeaderButtonsText = Stream
        .of("HOME", "CONTACT FORM", "SERVICE", "METALS & COLORS")
        .sorted()
        .collect(Collectors.toList());

    private final String frameButtonValue = "Frame Button";
    private final List<String> expectedLeftSectionItemsText = Stream
        .of("Home", "Contact form", "Service", "Metals & Colors", "Elements packs")
        .sorted()
        .collect(Collectors.toList());





    @Test
    public void firstExerciseTest() {
        gotoSite();
        checkPageTitle(expectedBrowserTitle);
        login(user);
        checkUserIsLoggined(user);
        checkAllHeadersItemsDisplayed(expectedHeaderButtonsText);
        checkImagesDisplayedAndHaveTextBelow(imageCssLocatorToTextAfterImage);
        checkFrameWithButtonExists(frameButtonValue);
        checkLeftSectionHasItemsWithProperText(expectedLeftSectionItemsText);
    }



    // 5. Assert that there are 4 items on the header section
    // are displayed and they have proper texts;
    public void checkAllHeadersItemsDisplayed(List<String> expectedHeaderButtonsText) {
        List<WebElement> headerNavigationButtons = driver.findElements(
            By.cssSelector(".uui-navigation.nav.navbar-nav > *")
        );
        assertThat(headerNavigationButtons.size()).isEqualTo(expectedHeaderButtonsText.size());
        List<String> headerButtonsActualText = headerNavigationButtons
            .stream()
            .map(WebElement::getText)
            .sorted()
            .collect(Collectors.toList());

        assertThat(headerButtonsActualText).isEqualTo(expectedHeaderButtonsText);
        headerNavigationButtons.forEach(el -> assertThat(el.isDisplayed()).isEqualTo(true));
    }



    // 6. Assert that there are 4 images on the Index Page
    // and they are displayed;
    // 7. Assert that there are 4 texts on the Index Page
    // under icons and they have proper text;
    public void checkImagesDisplayedAndHaveTextBelow(
        Map<String, String> imageCssLocatorToTextAfterImage) {

        // both are same for all images in bottom part
        String imagesCssLocators = "div.row.clerafix.benefits > *";
        List<WebElement> images = driver.findElements(By.cssSelector(imagesCssLocators));
        assertThat(images.size()).isEqualTo(imageCssLocatorToTextAfterImage.size());

        for (String imageClassName : imageCssLocatorToTextAfterImage.keySet()) {
            WebElement image = driver.findElement(By.cssSelector(imageClassName));
            assertThat(isImageDisplayedAfterWait(image)).isEqualTo(true);
            WebElement textElement = getTextNodeFromImageNode(image);
            assertThat(textElement.getText()).isEqualTo(
                imageCssLocatorToTextAfterImage.get(imageClassName)
            );
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
            switchToOriginalWindow();
            if (numButtons > 0) {
                frameWithButtonExists = true;
                break;
            }
        }
        assertThat(frameWithButtonExists).isEqualTo(true);
    }

    // 11. Assert that there are 5 items in the Left Section
    // are displayed and they have proper text;
    public void checkLeftSectionHasItemsWithProperText(List<String> expectedLeftSectionItemsText) {
        // go directly to tag w/ text
        List<WebElement> leftSectionElements = driver.findElements(
            By.cssSelector(".sidebar-menu.left > * > * > span")
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
    public void switchToOriginalWindow() {
        driver.switchTo().defaultContent();
    }

    public WebElement getTextNodeFromImageNode(WebElement image) {
        WebElement parent = image.findElement(By.xpath("./../.."));
        String textAfterImagesXpath = "./span[@class='benefit-txt']";
        return parent.findElement(By.xpath(textAfterImagesXpath));
    }

    public boolean isImageDisplayedAfterWait(WebElement image) {
        return wait.until(ExpectedConditions.visibilityOf(image)).isDisplayed();
    }

}
