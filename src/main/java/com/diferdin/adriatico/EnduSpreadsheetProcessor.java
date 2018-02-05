package com.diferdin.adriatico;

import com.diferdin.adriatico.extraction.EnduDataExtractionManager;

import java.io.IOException;
import java.util.List;

public class EnduSpreadsheetProcessor {

    public static final String ADRIATICO_TEAM = "ADRIATICO TEAM POLISPORTIVA";

    public static void main(String[] args) throws IOException {
        if(args.length != 3) {
            System.out.println("Error: Bad command or filename. Syntax: java [filename.xls] distanza nomeGara");
                    System.exit(0);
        }

        final String spreadsheetName = args[0];
        final String distanza = args[1];
        final String gara = args[2];

        List<DataEntry> entries = new EnduDataExtractionManager(spreadsheetName, distanza, gara).extract();


    }
}
