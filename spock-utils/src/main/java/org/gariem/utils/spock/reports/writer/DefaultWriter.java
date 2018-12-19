package org.gariem.utils.spock.reports.writer;

import org.apache.poi.xwpf.usermodel.*;
import org.gariem.utils.spock.reports.Bookmark;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class DefaultWriter {

    Writer paragraphWriter = new ParagraphWriter();

    public void write(List<Bookmark> bookmarkList, File templateFile) {

        try {
            InputStream templateIS = new FileInputStream(templateFile);
            XWPFDocument document = new XWPFDocument(templateIS);

            findParagraphCursors(bookmarkList, document);

            findTableCursors(bookmarkList, document);

            for (Bookmark bookmark : bookmarkList) {
                this.write(bookmark, document);
            }

            //Remove bookmarks
            for (Bookmark bookmark : bookmarkList) {
                bookmark.getParagraph().removeRun(bookmark.getRunNumber());
            }

            DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
            String datePart = dateFormat.format(new Date());
            String outFileNameString = new File(templateFile.getParent()).getParent() + File.separator + templateFile.getName().replace("Template_", "") + "_" + datePart + ".docx";

            final FileOutputStream out = new FileOutputStream(outFileNameString);
            document.write(out);
            out.close();


        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void findParagraphCursors(List<Bookmark> bookmarkList, XWPFDocument document) {
        for (Bookmark bookmark : bookmarkList) {

            List<XWPFParagraph> paragraphs = document.getParagraphs();
            for (int i = 0; i < paragraphs.size(); i++) {
                XWPFParagraph paragraph = document.getParagraphs().get(i);
                findBookmarkInParagraph(bookmark, paragraph);
            }
        }
    }


    void findTableCursors(List<Bookmark> bookmarkList, XWPFDocument document) {
        for (Bookmark bookmark : bookmarkList) {
            for (XWPFTable tbl : document.getTables()) {
                for (XWPFTableRow row : tbl.getRows()) {
                    for (XWPFTableCell cell : row.getTableCells()) {
                        List<XWPFParagraph> paragraphs = cell.getParagraphs();
                        for (int i = 0; i < paragraphs.size(); i++) {
                            XWPFParagraph paragraph = paragraphs.get(i);
                            findBookmarkInParagraph(bookmark, paragraph);
                        }
                    }
                }
            }
        }

    }

    private void findBookmarkInParagraph(Bookmark bookmark, XWPFParagraph paragraph) {
        List<XWPFRun> runs = paragraph.getRuns();
        for (int j = 0; j < runs.size(); j++) {
            XWPFRun run = runs.get(j);
            String text = run.getText(0);
            if (text != null && text.contains(bookmark.getTag())) {
                bookmark.setRunNumber(j);
                bookmark.setParagraph(paragraph);
            }
        }

    }

    void write(Bookmark bookmark, XWPFDocument document) {
        paragraphWriter.write(bookmark, document);
    }
}
