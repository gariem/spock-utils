package org.gariem.utils.spock.reports.element;


public class ImageElement implements Insertable {

    String content;

    public ImageElement(String imageUrl) {
        this.content = imageUrl;
    }

    @Override
    public String getType() {
        return IMAGE;
    }

    @Override
    public Object getContent() {
        return this.content;
    }
}
