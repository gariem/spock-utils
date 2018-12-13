package org.gariem.utils.spock.datapipe;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.io.File;
import java.util.Iterator;

public class ExcelReader implements DataReader {

    File file;
    String sheetName;

    public ExcelReader(File file, String sheetName) {
        this.file = file;
        this.sheetName = sheetName;
    }

    public Iterator<Iterable<String>> read() {

        try {
            Workbook workbook = WorkbookFactory.create(this.file);

            for (Sheet sheet : workbook) {
                if (sheet.getSheetName().equals(this.sheetName)) {
                    Iterator<Row> rowIterator = sheet.rowIterator();
                    return new DataIterator(rowIterator);
                }
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.print(ex.toString());
        }

        return null;
    }
}
