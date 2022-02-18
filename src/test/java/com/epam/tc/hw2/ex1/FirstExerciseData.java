package com.epam.tc.hw2.ex1;

import static java.util.Map.entry;

import com.epam.tc.hw2.BaseData;
import com.epam.tc.hw2.entities.User;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.testng.annotations.DataProvider;

public class FirstExerciseData extends BaseData {

    @DataProvider(name = "first exercise data")
    public static Object[][] provideData() {
        User user = loadUserFromProperties();
        Map<String, String> imageCssLocatorToTextAfterImage = Map.ofEntries(
            entry("icons-benefit icon-practise",
                "To include good practices\nand ideas from successful\nEPAM project"),
            entry("icons-benefit icon-custom",
                "To be flexible and\ncustomizable"),
            entry("icons-benefit icon-multi",
                "To be multiplatform"),
            entry("icons-benefit icon-base",
                "Already have good base\n(about 20 internal and"
                    + "\nsome external projects),\nwish to get more…")
        );
        List<String> expectedHeaderButtonsText = Stream
            .of("HOME", "CONTACT FORM", "SERVICE", "METALS & COLORS")
            .sorted()
            .collect(Collectors.toList());

        List<String> expectedLeftSectionItemsText = Stream
            .of("Home", "Contact form", "Service", "Metals & Colors", "Elements packs")
            .sorted()
            .collect(Collectors.toList());

        String frameButtonValue = "Frame Button";

        return new Object[][]{
            {INDEX_PAGE_URL, EXPECTED_BROWSER_TITLE, user,
                imageCssLocatorToTextAfterImage, expectedHeaderButtonsText,
                expectedLeftSectionItemsText, frameButtonValue}
        };
    }



}
