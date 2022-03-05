package com.epam.tc.hw5.ex1;

import com.epam.tc.hw5.AbstractBaseData;
import com.epam.tc.hw5.entities.User;
import java.util.List;

public class FirstExerciseData extends AbstractBaseData {
    private User user = loadUserFromProperties();
    private List<String> elementsCheckboxesText = List.of("Water", "Wind");
    private String radioCheckboxesText = "Selen";
    private String color = "Yellow";

    public User getUser() {
        return user;
    }

    public List<String> getElementsCheckboxesText() {
        return elementsCheckboxesText;
    }

    public String getRadioCheckboxesText() {
        return radioCheckboxesText;
    }

    public String getColor() {
        return color;
    }
}
