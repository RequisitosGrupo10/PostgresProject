package org.example;

import javax.persistence.Id;
import java.sql.Date;

public class Receta {
    @Id
    private int ID;
    private String NIF;
    private Date CONSULTA;
    private double OD_ESFERA;
    private double OD_CILINDRO;
    private double OD_ADICION;
    private double OD_AGUDEZA;
    private double OI_ESFERA;
    private double OI_CILINDRO;
    private double OI_ADICION;
    private double OI_AGUDEZA;
}
