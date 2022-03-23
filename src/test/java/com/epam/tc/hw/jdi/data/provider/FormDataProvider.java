package com.epam.tc.hw.jdi.data.provider;

import com.epam.tc.hw.jdi.data.util.MetalsColorsJsonReader;
import org.testng.annotations.DataProvider;

public class FormDataProvider {

    @DataProvider(name = "metalsColorsFormData")
    public static Object[][] metalsColorsFormData() {
        return MetalsColorsJsonReader
            .readData()
            .stream()
            .map(entry -> new Object[]{entry})
            .toArray(Object[][]::new);
    }
}
