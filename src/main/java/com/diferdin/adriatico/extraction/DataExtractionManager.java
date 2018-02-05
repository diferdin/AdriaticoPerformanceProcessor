package com.diferdin.adriatico.extraction;


import com.diferdin.adriatico.DataEntry;

import java.io.IOException;
import java.util.List;

public interface DataExtractionManager {

    List<DataEntry> extract() throws IOException;
}
