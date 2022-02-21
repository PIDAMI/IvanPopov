package com.epam.tc.hw2.ex2;

import static java.util.Map.entry;

import com.epam.tc.hw2.BaseData;
import com.epam.tc.hw2.entities.User;
import java.util.Map;
import org.testng.annotations.DataProvider;

public class SecondExerciseData extends BaseData {

    private User user = loadUserFromProperties();
    private Map<String, String> checkboxElementsTextToLogFormat = Map.ofEntries(
        entry("Water", "Water: condition changed to true"),
        entry("Wind", "Wind: condition changed to true")
    );
    private Map<String, String> checkboxRadioTextToLogFormat = Map.ofEntries(
        entry("Selen", "Selen")
    );
    private Map<String, String> colorTextToLogFormat = Map.ofEntries(
        entry("Yellow", "Colors: value changed to Yellow")
    );

    public User getUser() {
        return user;
    }

    public Map<String, String> getCheckboxElementsTextToLogFormat() {
        return checkboxElementsTextToLogFormat;
    }

    public Map<String, String> getCheckboxRadioTextToLogFormat() {
        return checkboxRadioTextToLogFormat;
    }

    public Map<String, String> getColorTextToLogFormat() {
        return colorTextToLogFormat;
    }
}
