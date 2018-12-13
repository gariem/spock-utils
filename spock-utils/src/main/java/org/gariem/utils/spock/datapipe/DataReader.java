package org.gariem.utils.spock.datapipe;

import java.util.Iterator;

public interface DataReader {

    Iterator<Iterable<String>> read();

}
