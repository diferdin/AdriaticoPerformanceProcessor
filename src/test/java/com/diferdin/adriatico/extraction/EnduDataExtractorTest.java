package com.diferdin.adriatico.extraction;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class EnduDataExtractorTest {

    private EnduDataExtractionManager extractor;

    @Before
    public void setup() {
        extractor = new EnduDataExtractionManager(null, "15", "Test");
    }

    @Test
    public void shouldConvertStringIntoPasso() {
        String tempo = "2:31:13";

        String passo = extractor.calcolaPasso(tempo);

        assertEquals("00:10:04", passo);
    }

}
