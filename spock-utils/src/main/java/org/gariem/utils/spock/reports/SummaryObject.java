package org.gariem.utils.spock.reports;

import lombok.Data;

@Data
public class SummaryObject {
    String descripcion;
    String esperado;
    String actual;
}