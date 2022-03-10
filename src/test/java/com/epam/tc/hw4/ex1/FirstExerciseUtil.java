package com.epam.tc.hw4.ex1;

import static org.assertj.core.api.Assertions.assertThat;

import com.epam.tc.hw4.AbstractBaseUtil;
import com.epam.tc.hw4.page.objects.voids.JdiIndexVoidPage;
import io.qameta.allure.Step;
import java.util.List;
import java.util.stream.Collectors;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FirstExerciseUtil extends AbstractBaseUtil {

    public FirstExerciseUtil(WebDriver driver, WebDriverWait wait, JdiIndexVoidPage indexPage) {
        super(driver, wait, indexPage);
    }

    @Step("Check if header items are displaeyd and have text equal to {expectedItemsText}")
    public void checkIfHeaderItemsHaveText(List<String> expectedItemsText) {
        List<WebElement> buttonsDisplayed = indexPage.header().getNavigationButtons();
        buttonsDisplayed.forEach(el -> assertThat(el.isDisplayed()).isTrue());

        List<String> buttonsText = indexPage.header().getNavigationButtonsText();
        buttonsText.sort(String::compareTo);
        assertThat(buttonsText).isEqualTo(expectedItemsText);

    }

    @Step("Check if images in bottom part are displayed and have text below equal to {expectedTextAfterImages}")
    public void checkIfImagesDisplayedAndHaveProperTextBelow(List<String> expectedTextAfterImages) {
        List<WebElement> imagesDisplayed = indexPage.bottomPart().getImages();
        assertThat(imagesDisplayed.size()).isEqualTo(expectedTextAfterImages.size());
        imagesDisplayed.forEach(el -> assertThat(el.isDisplayed()).isTrue());
        List<String> textAfterImages = indexPage.bottomPart().getTextAfterImage();
        assertThat(textAfterImages).isEqualTo(expectedTextAfterImages);
    }

    @Step("Check if left section items are displayed and have text equal to {expectedLeftSectionItemsText}")
    public void checkIfLeftSectionItemsDisplayedAndHaveProperText(List<String> expectedLeftSectionItemsText) {
        List<WebElement> leftSectionElements = indexPage.leftSection().getLeftSectionElements();
        assertThat(leftSectionElements.size()).isEqualTo(expectedLeftSectionItemsText.size());

        leftSectionElements.forEach(el -> assertThat(el.isDisplayed()).isTrue());
        List<String> leftSectionText = leftSectionElements
            .stream()
            .map(WebElement::getText)
            .sorted()
            .collect(Collectors.toList());
        assertThat(leftSectionText).isEqualTo(expectedLeftSectionItemsText);
    }
}
