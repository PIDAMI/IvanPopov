package com.epam.tc.hw3.ex1;

import static org.assertj.core.api.Assertions.assertThat;

import com.epam.tc.hw3.AbstractBaseTest;
import com.epam.tc.hw3.entities.User;
import com.epam.tc.hw3.page.objects.fluents.JdiIndexFluentPage;
import com.epam.tc.hw3.page.objects.voids.JdiIndexVoidPage;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

public class FirstExerciseTest extends AbstractBaseTest {


    @Test(dataProvider = "first exercise data",
          dataProviderClass = FirstExerciseData.class)
    public void firstExerciseWithVoidPagesTest(final String expectedBrowserTitle,
                                               User user,
                                               List<String> expectedTextAfterImages,
                                               List<String> expectedHeaderButtonsText,
                                               List<String> expectedLeftSectionItemsText,
                                               String frameButtonValue) {

        JdiIndexVoidPage indexPage = new JdiIndexVoidPage(driver);
        // 2. Assert Browser title
        assertThat(indexPage.getPageTitle()).isEqualTo(expectedBrowserTitle);

        // 3. Perform login
        indexPage.profile().login(user);

        // 4. Assert Username is loggined
        Optional<String> userName = indexPage.profile().getUserNameIfDisplayed();
        assertThat(userName.isPresent()).isTrue();
        assertThat(userName.get()).isEqualTo(user.getDisplayedName());

        // 5. Assert that there are 4 items on the header section
        // are displayed and they have proper texts
        List<WebElement> buttonsDisplayed = indexPage.header().getNavigationButtons();
        buttonsDisplayed.forEach(el -> assertThat(el.isDisplayed()).isTrue());

        List<String> buttonsText = indexPage.header().getNavigationButtonsText();
        buttonsText.sort(String::compareTo);
        assertThat(buttonsText).isEqualTo(expectedHeaderButtonsText);

        // 6. Assert that there are 4 images
        // on the Index Page and they are displayed
        List<WebElement> imagesDisplayed = indexPage.bottomPart().getImages();
        assertThat(imagesDisplayed.size()).isEqualTo(expectedTextAfterImages.size());
        imagesDisplayed.forEach(el -> assertThat(el.isDisplayed()).isTrue());

        // 7. Assert that there are 4 texts on
        // the Index Page under icons and they have proper text
        List<String> textAfterImages = indexPage.bottomPart().getTextAfterImage();
        assertThat(textAfterImages).isEqualTo(expectedTextAfterImages);

        // 8. Assert that there is the iframe with “Frame Button” exist
        // 9. Switch to the iframe and check that
        // there is “Frame Button” in the iframe;
        // there's noway to check if frame w/ button exists but to
        // switch to every frame and check if there's a button in some frame
        // so making sure 8 is true necessarily implies that actions in 9 are already done
        indexPage.switchToFrameWithButton(frameButtonValue);

        // 10. Switch to original window back
        indexPage.switchToMainWindow();

        // 11. Assert that there are 5 items in the
        // Left Section are displayed and they have proper text
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

    @Test(dataProvider = "first exercise data",
          dataProviderClass = FirstExerciseData.class)
    public void firstExerciseWithFluentPagesTest(final String expectedBrowserTitle,
                                               User user,
                                               List<String> expectedTextAfterImages,
                                               List<String> expectedHeaderButtonsText,
                                               List<String> expectedLeftSectionItemsText,
                                               String frameButtonValue) {

        JdiIndexFluentPage indexPage = new JdiIndexFluentPage(driver);
        // 2. Assert Browser title
        assertThat(indexPage.getPageTitle()).isEqualTo(expectedBrowserTitle);

        // 3. Perform login
        indexPage.profile().login(user);

        // 4. Assert Username is loggined
        Optional<String> userName = indexPage.profile().getUserNameIfDisplayed();
        assertThat(userName.isPresent()).isTrue();
        assertThat(userName.get()).isEqualTo(user.getDisplayedName());

        // 5. Assert that there are 4 items on the header section
        // are displayed and they have proper texts
        List<WebElement> buttonsDisplayed = indexPage.header().getNavigationButtons();
        buttonsDisplayed.forEach(el -> assertThat(el.isDisplayed()).isTrue());

        List<String> buttonsText = indexPage.header().getNavigationButtonsText();
        buttonsText.sort(String::compareTo);
        assertThat(buttonsText).isEqualTo(expectedHeaderButtonsText);

        // 6. Assert that there are 4 images
        // on the Index Page and they are displayed
        List<WebElement> imagesDisplayed = indexPage.bottomPart().getImages();
        assertThat(imagesDisplayed.size()).isEqualTo(expectedTextAfterImages.size());
        imagesDisplayed.forEach(el -> assertThat(el.isDisplayed()).isTrue());

        // 7. Assert that there are 4 texts on
        // the Index Page under icons and they have proper text
        List<String> textAfterImages = indexPage.bottomPart().getTextAfterImage();
        assertThat(textAfterImages).isEqualTo(expectedTextAfterImages);

        // 8. Assert that there is the iframe with “Frame Button” exist
        // 9. Switch to the iframe and check that
        // there is “Frame Button” in the iframe;
        // 10. Switch to original window back
        indexPage.switchToFrameWithButton(frameButtonValue).switchToMainWindow();


        // 11. Assert that there are 5 items in the
        // Left Section are displayed and they have proper text
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
