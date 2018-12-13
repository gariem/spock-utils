package org.gariem.utils.spock.datapipe;


import com.google.common.collect.Lists;
import org.apache.commons.csv.CSVRecord;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;

import java.util.Iterator;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class DataIterator implements Iterator<Iterable<String>> {

    Iterator<?> iterator;

    private DataIterator() {
    }

    public DataIterator(Iterator<?> iterator) {
        this.iterator = iterator;
    }

    @Override
    public boolean hasNext() {
        return iterator.hasNext();
    }

    @Override
    public Iterable<String> next() {
        Iterable<String> iterable = null;

        Object nextObject = iterator.next();

        if (nextObject instanceof CSVRecord) {
            CSVRecord next = (CSVRecord) nextObject;
            iterable = Lists.newArrayList(next.iterator());
        } else if (nextObject instanceof Row) {

            DataFormatter dataFormatter = new DataFormatter();
            Row row = (Row) nextObject;
            Stream<Cell> cellStream = StreamSupport.stream(Spliterators.spliteratorUnknownSize(row.cellIterator(), Spliterator.ORDERED), false);
            iterable = cellStream.map(cell -> dataFormatter.formatCellValue(cell)).collect(Collectors.toList());
        }

        return iterable;
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException("Not supported");
    }
}
