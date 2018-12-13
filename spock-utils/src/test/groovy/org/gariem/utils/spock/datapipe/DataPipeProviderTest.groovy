package org.gariem.utils.spock.datapipe

import spock.lang.Specification

class DataPipeProviderTest extends Specification {


    DataPipeProvider provider

    def 'Read data from csv file - with headers'() {
        given:
        int rows = 0
        provider = new DataPipeProvider(getClass().getResource("/input_files/input_data.csv").getFile())

        when:
        Iterator<Iterable<String>> iterator = provider.getDataIterator(true)

        while (iterator.hasNext()) {
            println iterator.next().toString()
            rows += 1
        }

        then:
        rows == 2
    }

    def 'Read data from xlsx file - without headers'(){
        given:
        int rows = 0
        provider = new DataPipeProvider(getClass().getResource("/input_files/input_data.xlsx").getFile(), "MyDataSheet")

        when:
        Iterator<Iterable<String>> iterator = provider.getDataIterator()

        while (iterator.hasNext()) {
            println iterator.next().toString()
            rows += 1
        }

        then:
        rows == 2
    }
}
