package pt.mleiria.mlalgo.dataset;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

public class CsvReader {

    private static final Logger LOG = Logger.getLogger(CsvReader.class.getName());

    public static List<CSVRecord> getCsvRecords(final String filePath) {
        try {
            // Read CSV file
            try (final FileReader reader = new FileReader(filePath)) {
                try (final CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader())) {
                    // Parse CSV records
                    return csvParser.getRecords();
                }
            }
        } catch (IOException e) {
            LOG.severe(e.getMessage());
            return Collections.emptyList();
        }
    }

}
