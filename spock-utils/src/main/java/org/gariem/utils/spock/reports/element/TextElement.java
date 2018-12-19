package org.gariem.utils.spock.reports.element;

public class TextElement implements Insertable {

    String content;

    public TextElement() {

    }

    public TextElement(String content) {
        this.content = content;
    }

    @Override

    public String getType() {
        return TEXT;
    }

    @Override
    public Object getContent() {
        return this.content;
    }
}
