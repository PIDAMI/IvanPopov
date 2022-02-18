package com.epam.tc.hw3.ex1;

import com.epam.tc.hw3.BaseData;
import com.epam.tc.hw3.entities.User;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.testng.annotations.DataProvider;

public class FirstExerciseData extends BaseData {

    @DataProvider(name = "first exercise data")
    public static Object[][] provideData() {
        User user = loadUserFromProperties();
        List<String> expectedTextAfterImages = List.of(
            "To include good practices\nand ideas from successful\nEPAM project",
            "To be flexible and\ncustomizable",
            "To be multiplatform",
            "Already have good base\n(about 20 internal and"
                + "\nsome external projects),\nwish to get more…"
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
            {EXPECTED_BROWSER_TITLE, user,
                expectedTextAfterImages, expectedHeaderButtonsText,
                expectedLeftSectionItemsText, frameButtonValue}
        };
    }

}

