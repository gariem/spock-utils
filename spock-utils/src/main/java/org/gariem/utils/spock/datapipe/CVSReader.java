package org.gariem.utils.spock.datapipe;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Iterator;

public class CVSReader implements DataReader {

    private File file;

    public CVSReader(File file) {
        this.file = file;
    }

    public Iterator<Iterable<String>> read() {

        Iterable<CSVRecord> records;

        try {
            Reader in = new FileReader(file.getAbsolutePath());
            records = CSVFormat.EXCEL.parse(in);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        final Iterator<CSVRecord> it = records.iterator();

        return new DataIterator(it);

    }
}
