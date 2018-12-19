package org.gariem.utils.spock.reports.writer;

import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.*;
import org.apache.xmlbeans.XmlCursor;
import org.gariem.utils.spock.reports.Bookmark;
import org.gariem.utils.spock.reports.element.Insertable;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;


public class ParagraphWriter implements Writer {

    public void write(Bookmark bookmark, XWPFDocument document) {
        boolean hasImage = false, hasText = false;
        for (Insertable insertable : bookmark.getInsertables()) {
            hasImage = hasImage || insertable.getType().equals(Insertable.IMAGE);
            hasText = hasText || insertable.getType().equals(Insertable.TEXT);
        }
        write(bookmark, (hasImage && hasText));
    }

    public void write(Bookmark bookmark, boolean twoColumnEntry) {

        try {

            XWPFRun foundRun = bookmark.getParagraph().getRuns().get(bookmark.getRunNumber());
            XWPFParagraph paragraph1 = (XWPFParagraph) foundRun.getParent();
            XWPFParagraph paragraph2 = (XWPFParagraph) foundRun.getParent();

            for (Insertable element : bookmark.getInsertables()) {

                switch (element.getType()) {
                    case Insertable.TEXT: {

                        XWPFRun newRun = paragraph2.createRun();
                        newRun.setText(element.getContent().toString());

                        newRun.addBreak();
                        //newRun.addCarriageReturn();
                        break;
                    }
                    case Insertable.IMAGE: {

                        if (twoColumnEntry) {
                            XWPFParagraph[] paragraphs = createTable(bookmark);
                            paragraph1 = paragraphs[0];
                            paragraph2 = paragraphs[1];
                        }

                        BufferedImage bImg = ImageIO.read(new File(element.getContent().toString()));
                        int width = bImg.getWidth() / 4;
                        int height = bImg.getHeight() / 4;

                        FileInputStream is = new FileInputStream(element.getContent().toString());

                        XWPFRun newRun = paragraph1.createRun();
                        newRun.addPicture(is, XWPFDocument.PICTURE_TYPE_JPEG, element.getContent().toString(),
                                Units.toEMU(width), Units.toEMU(height));

                        newRun.addBreak();
                        //newRun.addCarriageReturn();

                        break;
                    }
                }
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    private XWPFParagraph[] createTable(Bookmark bookmark) {

        XWPFParagraph paragraph = bookmark.getParagraph();
        XmlCursor cursor = paragraph.getCTP().newCursor();

        XWPFTable table = paragraph.getBody().insertNewTbl(cursor);

        // write to first row, first column
        XWPFParagraph paragraph1 = table.getRow(0).getCell(0).getParagraphs().get(0);
        paragraph1.setAlignment(ParagraphAlignment.LEFT);

        // write to first row, second column
        XWPFParagraph paragraph2 = table.getRow(0).addNewTableCell().getParagraphs().get(0);
        paragraph2.setAlignment(ParagraphAlignment.LEFT);


        XWPFParagraph[] paragraphs = {paragraph1, paragraph2};

        return paragraphs;

    }
}
