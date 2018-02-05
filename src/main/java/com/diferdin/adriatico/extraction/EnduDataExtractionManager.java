package com.diferdin.adriatico.extraction;

import com.diferdin.adriatico.DataEntry;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

import static com.diferdin.adriatico.constants.EnduColumns.*;

import static com.diferdin.adriatico.EnduSpreadsheetProcessor.ADRIATICO_TEAM;

public class EnduDataExtractionManager implements DataExtractionManager {

    private final String SPECIALITA = "Podismo";

    private final String fileName;
    private final String distanza;
    private final String gara;

    public EnduDataExtractionManager(String fileName, String distanza, String gara) {
        this.fileName = fileName;
        this.distanza = distanza;
        this.gara = gara;
    }

    public List<DataEntry> extract() throws IOException {

        List<DataEntry> result = new ArrayList<>();

        HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(fileName));
        HSSFSheet generale = workbook.getSheetAt(0);

        Iterator<Row> rowIterator = generale.rowIterator();
        while(rowIterator.hasNext()) {
            Row row = rowIterator.next();

            if(row.getCell(TEAM).getStringCellValue().equals(ADRIATICO_TEAM)) {


                DataEntry entry = DataEntry.builder()
                        .cognome(row.getCell(COGNOME).getStringCellValue())
                        .nome(row.getCell(NOME).getStringCellValue())
                        .posizioneAssoluta(row.getCell(POS_ASSOLUTA).getStringCellValue())
                        .posizioneDiCategoria(row.getCell(POS_CAT).getNumericCellValue())
                        .posizioneDiGenere(row.getCell(POS_SESSO).getNumericCellValue())
                        .tempoUfficiale(row.getCell(TEMPO_UFFICIALE).getStringCellValue())
                        .distacco(row.getCell(DISTACCO).getStringCellValue())
                        .data(formatDate())
                        .specialita(SPECIALITA)
                        .distanza(Integer.valueOf(distanza))
                        .passo(calcolaPasso(row.getCell(TEMPO_UFFICIALE).getStringCellValue()))
                        .nomeGara(gara)
                        .build();

                result.add(entry);
            }
        }

        return result;
    }

    private String formatDate() {
        return new SimpleDateFormat("yyyy-MM-dd").format(new Date());
    }

    public String calcolaPasso(String tempoTotale) {

        //00:34:39
        String[] units = tempoTotale.split(":");
        int hours = Integer.parseInt(units[0]);
        int minutes = Integer.parseInt(units[1]);
        int seconds = Integer.parseInt(units[2]);

        int tempoTotaleInSecs = (hours * 3600) + (minutes * 60) + seconds;

        return formatDuration(tempoTotaleInSecs/ new Long(distanza));
    }

    private String formatDuration(long seconds) {
        Date d = new Date(seconds * 1000L);
        SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss"); // HH for 0-23
        df.setTimeZone(TimeZone.getTimeZone("GMT"));
        return df.format(d);
    }
}
