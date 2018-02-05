package com.diferdin.adriatico;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DataEntry {
    private final String cognome;
    private final String nome;
    private final String posizioneAssoluta;
    private final Number posizioneDiCategoria;
    private final Number posizioneDiGenere;
    private final String tempoUfficiale;
    private final String distacco;
    private final String data;
    private final String specialita;
    private final Number distanza;
    private final String passo;
    private final String nomeGara;
}
