package org.gariem.utils.spock.reports.element;

public interface Insertable {

    String IMAGE = "IMAGE";
    String TEXT = "TEXT";

    String getType();

    Object getContent();

}
