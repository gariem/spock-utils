package org.gariem.utils.spock.reports;


import lombok.Data;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.gariem.utils.spock.reports.element.Insertable;

import java.util.ArrayList;
import java.util.List;

@Data
public class Bookmark {


    private List<Insertable> insertables;
    private String tag;

    private int runNumber;
    private XWPFParagraph paragraph;

    public Bookmark() {
        this.insertables = new ArrayList<>();
    }

    public Bookmark(String tag) {
        this();
        this.tag = tag;
    }

    public Bookmark(String tag, List<Insertable> insertables) {
        this(tag);
        this.insertables = insertables;
    }


}
