package com.epam.tc.hw3.ex1;

import static org.assertj.core.api.Assertions.assertThat;

import com.epam.tc.hw3.AbstractBaseTest;
import com.epam.tc.hw3.page.objects.voids.JdiIndexPage;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

public class FirstExerciseTest extends AbstractBaseTest {



    private final List<String> expectedHeaderButtonsText = Stream
        .of("HOME", "CONTACT FORM", "SERVICE", "METALS & COLORS")
        .sorted()
        .collect(Collectors.toList());

    private final int numImages = 4;
    private final List<String> expectedTextAfterImages = List.of(
        "To include good practices\nand ideas from successful\nEPAM project",
        "To be flexible and\ncustomizable",
        "To be multiplatform",
        "Already have good base\n(about 20 internal and"
            + "\nsome external projects),\nwish to get more…"
    );

    private final String frameButtonValue = "Frame Button";

    private final int numLeftSectionItems = 5;
    private final List<String> expectedLeftSectionItemsText = Stream
        .of("Home", "Contact form", "Service", "Metals & Colors", "Elements packs")
        .sorted()
        .collect(Collectors.toList());

    @Test
    public void firstExerciseTest() {

        JdiIndexPage indexPage = new JdiIndexPage(driver);
        // 2. Assert Browser title
        assertThat(indexPage.getPageTitle()).isEqualTo(siteTitle);

        // 3. Perform login
        indexPage.profile().login(user);

        // 4. Assert Username is loggined
        Optional<String> userName = indexPage.profile()
                                             .getUserNameIfDisplayed();
        assertThat(userName.isPresent()).isEqualTo(true);
        assertThat(userName.get()).isEqualTo(expectedUserName);

        // 5. Assert that there are 4 items on the header section
        // are displayed and they have proper texts
        List<WebElement> buttonsDisplayed = indexPage.header()
                                                     .getNavigationButtons();
        buttonsDisplayed.forEach(el -> assertThat(el.isDisplayed()).isEqualTo(true));

        List<String> buttonsText = indexPage.header().getNavigationButtonsText();
        buttonsText.sort(String::compareTo);
        assertThat(buttonsText).isEqualTo(expectedHeaderButtonsText);

        // 6. Assert that there are 4 images
        // on the Index Page and they are displayed
        List<WebElement> imagesDisplayed = indexPage.bottomPart().getImages();
        assertThat(imagesDisplayed.size()).isEqualTo(numImages);
        imagesDisplayed.forEach(el -> assertThat(el.isDisplayed()).isEqualTo(true));

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
        List<WebElement> leftSectionElements = indexPage.leftSection()
                                                        .getLeftSectionElements();
        assertThat(leftSectionElements.size()).isEqualTo(numLeftSectionItems);
        leftSectionElements.forEach(el -> assertThat(el.isDisplayed()).isEqualTo(true));
        List<String> leftSectionText = leftSectionElements
            .stream()
            .map(WebElement::getText)
            .sorted()
            .collect(Collectors.toList());
        assertThat(leftSectionText).isEqualTo(expectedLeftSectionItemsText);
    }
}
