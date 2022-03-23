package com.epam.tc.hw.jdi.data.util;

import com.epam.tc.hw.jdi.entities.MetalsColorsFormEntry;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.Map;

public class MetalsColorsJsonReader {

    private static final String JSON_PATH =
        "src/test/resources/JDI_ex8_metalsColorsDataSet.json";

    public static Collection<MetalsColorsFormEntry> readData() {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            File f = new File(JSON_PATH);
            return objectMapper.readValue(f,
                new TypeReference<Map<String, MetalsColorsFormEntry>>(){}).values();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
