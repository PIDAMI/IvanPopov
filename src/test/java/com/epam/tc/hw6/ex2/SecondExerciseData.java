package com.epam.tc.hw6.ex2;

import com.epam.tc.hw4.AbstractBaseData;
import com.epam.tc.hw4.entities.User;
import java.util.List;

public class SecondExerciseData extends AbstractBaseData {
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
