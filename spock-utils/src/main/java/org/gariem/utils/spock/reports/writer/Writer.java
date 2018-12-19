package org.gariem.utils.spock.reports.writer;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.gariem.utils.spock.reports.Bookmark;

public interface Writer {

    void write(Bookmark bookmark, XWPFDocument document);

}
