package com.epam.tc.hw6.ex1;

import com.epam.tc.hw4.AbstractBaseData;
import com.epam.tc.hw4.entities.User;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FirstExerciseData extends AbstractBaseData {
    private User user = loadUserFromProperties();
    private List<String> expectedTextAfterImages = List.of(
        "To include good practices\nand ideas from successful\nEPAM project",
        "To be flexible and\ncustomizable",
        "To be multiplatform",
        "Already have good base\n(about 20 internal and"
            + "\nsome external projects),\nwish to get more…"
    );
    private List<String> expectedHeaderButtonsText = Stream
        .of("HOME", "CONTACT FORM", "SERVICE", "METALS & COLORS")
        .sorted()
        .collect(Collectors.toList());
    private List<String> expectedLeftSectionItemsText = Stream
        .of("Home", "Contact form", "Service", "Metals & Colors", "Elements packs")
        .sorted()
        .collect(Collectors.toList());

    private String frameButtonValue = "Frame Button";

    public User getUser() {
        return user;
    }

    public List<String> getExpectedTextAfterImages() {
        return expectedTextAfterImages;
    }

    public List<String> getExpectedHeaderButtonsText() {
        return expectedHeaderButtonsText;
    }

    public List<String> getExpectedLeftSectionItemsText() {
        return expectedLeftSectionItemsText;
    }

    public String getFrameButtonValue() {
        return frameButtonValue;
    }
}

