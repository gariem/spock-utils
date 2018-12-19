package org.gariem.utils.spock.reports.listener.reportmanager;


import io.appium.java_client.AppiumDriver;
import org.apache.commons.io.FileUtils;
import org.gariem.utils.spock.reports.Bookmark;
import org.gariem.utils.spock.reports.element.ImageElement;
import org.gariem.utils.spock.reports.element.TextElement;
import org.gariem.utils.spock.reports.writer.DefaultWriter;
import org.openqa.selenium.OutputType;


import java.io.File;
import java.util.ArrayList;

import java.util.List;
import java.util.stream.Collectors;

public class EvidenceManager {

    DefaultWriter writer;
    List<Bookmark> bookmarks;
    File templateFile;
    AppiumDriver driver;


    public EvidenceManager(String templateFileUrl) {

        String fileName = System.getProperty("user.dir") + File.separator + templateFileUrl;
        File file = new File(fileName);

        if (!file.exists()) {
            fileName = getClass().getResource("/report_templates/" + templateFileUrl).getPath();
            file = new File(fileName);
        }

        if (!file.exists()) {
            throw new RuntimeException("No se encontró la plantilla para generar la evidencia personalizada");
        } else {
            this.templateFile = file;
            this.writer = new DefaultWriter();
            bookmarks = new ArrayList<>();
        }
    }

    public void setDriver(AppiumDriver driver) {
        this.driver = driver;
    }

    public void addScreenshot(String tag) {
        try {
            File srcFile = this.driver.getScreenshotAs(OutputType.FILE);
            File targetFile = File.createTempFile("evidence_", ".jpg");
            FileUtils.copyFile(srcFile, targetFile);

            Bookmark bookmark = getBookmark(tag);
            bookmark.getInsertables().add(new ImageElement(targetFile.getAbsolutePath()));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void addText(String tag, String text) {
        Bookmark bookmark = getBookmark(tag);
        bookmark.getInsertables().add(new TextElement(text));
    }

    public void write(List<Bookmark> bookmarks) {
        this.bookmarks = bookmarks;
        this.writer.write(this.bookmarks, this.templateFile);
    }

    public String addStep(String tag, String text) {
        File targetFile = null;

        try {
            File srcFile = this.driver.getScreenshotAs(OutputType.FILE);
            targetFile = File.createTempFile("evidence_", ".jpg");
            FileUtils.copyFile(srcFile, targetFile);

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        addScreenshot(tag);
        addText(tag, text);

        return targetFile.getAbsolutePath();

    }

    public void flush() {
        write(this.bookmarks);
    }


    private Bookmark getBookmark(String tag) {
        Bookmark bookmark;

        List<Bookmark> candidateBookmarks = this.bookmarks.stream()
                .filter(b -> b.getTag().equals(tag))
                .collect(Collectors.toList());

        if (candidateBookmarks.isEmpty()) {
            bookmark = new Bookmark(tag);
            this.bookmarks.add(bookmark);
        } else if (candidateBookmarks.size() == 1) {
            bookmark = candidateBookmarks.get(0);
        } else {
            throw new IllegalStateException("Found two bookmarks for the same tag.");
        }
        return bookmark;
    }

}
