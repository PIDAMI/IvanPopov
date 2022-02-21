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

    private Map<String, String> imageCssLocatorToTextAfterImage = Map.ofEntries(
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
    private List<String> expectedHeaderButtonsText = Stream
        .of("HOME", "CONTACT FORM", "SERVICE", "METALS & COLORS")
        .sorted()
        .collect(Collectors.toList());
    private List<String> expectedLeftSectionItemsText = Stream
        .of("Home", "Contact form", "Service", "Metals & Colors", "Elements packs")
        .sorted()
        .collect(Collectors.toList());
    private User user = loadUserFromProperties();
    private final String frameButtonValue = "Frame Button";

    public Map<String, String> getImageCssLocatorToTextAfterImage() {
        return imageCssLocatorToTextAfterImage;
    }

    public List<String> getExpectedHeaderButtonsText() {
        return expectedHeaderButtonsText;
    }

    public List<String> getExpectedLeftSectionItemsText() {
        return expectedLeftSectionItemsText;
    }

    public User getUser() {
        return user;
    }

    public String getFrameButtonValue() {
        return frameButtonValue;
    }
}
