package org.gariem.utils.spock.datapipe;

import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.util.Iterator;

public class DataPipeProvider {

    private String fileName;
    private String index;
    private File file;
    private DataReader reader;

    public DataPipeProvider(String fileName) {
        this(fileName, null);
    }

    public DataPipeProvider(String fileName, String index) {
        this.fileName = fileName;
        this.index = index;

        this.file = new File(fileName);
        this.reader = getReader(this.file, this.index);

    }

    private DataReader getReader(File file, String index) {
        DataReader reader = null;
        String extension = FilenameUtils.getExtension(file.getAbsolutePath());

        if ("xlsx".equals(extension) && index == null) {
            throw new IllegalArgumentException("Sheet name required for excel data pipes");
        }

        switch (extension.toLowerCase()) {
            case "csv":
                reader = new CVSReader(file);
                break;
            case "xlsx":
                reader = new ExcelReader(file, index);
                break;
        }
        return reader;
    }

    public Iterator<Iterable<String>> getDataIterator() {
        return getDataIterator(false);
    }

    public Iterator<Iterable<String>> getDataIterator(boolean includeHeaders) {
        Iterator<Iterable<String>> iterator = reader.read();
        if (!includeHeaders) {
            iterator.next();
        }
        return iterator;
    }
}
