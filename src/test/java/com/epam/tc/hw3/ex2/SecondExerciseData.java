package com.epam.tc.hw3.ex2;

import com.epam.tc.hw3.BaseData;
import com.epam.tc.hw3.entities.User;
import java.util.List;

public class SecondExerciseData extends BaseData {
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

    public String getRadioButtonsText() {
        return radioCheckboxesText;
    }

    public String getColor() {
        return color;
    }
}
