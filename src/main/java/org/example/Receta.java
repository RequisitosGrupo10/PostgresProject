package org.example;

import io.ebean.DB;
import io.ebean.annotation.Identity;
import org.example.query.QCliente;
import org.example.query.QReceta;
import org.example.query.assoc.QAssocReceta;

import javax.persistence.*;
import java.sql.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

import static io.ebean.annotation.IdentityGenerated.BY_DEFAULT;

@Entity
@Table(name = "Receta")
public class Receta {
    @Id
    @Identity(generated = BY_DEFAULT, start = 10000, cache = 1000)
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

    public static Receta getReceta(int recetaID) {
        return new QReceta().ID.eq(recetaID).findOne();
    }

    public static List<Receta> listaRecetas() {
        List<Receta> res = new QReceta().findList();
        return res;
    }

    public static List<Receta> listaRecetasCliente(Cliente c) {
        return new QReceta()
                .NIF.iequalTo(c.getNIF())
                .findList();
    }

    public Receta(String NIF, Date CONSULTA, double OD_ESFERA, double OD_CILINDRO, double OD_ADICION, double OD_AGUDEZA, double OI_ESFERA, double OI_CILINDRO, double OI_ADICION, double OI_AGUDEZA) {
        this.NIF = NIF;
        this.CONSULTA = CONSULTA;
        this.OD_ESFERA = OD_ESFERA;
        this.OD_CILINDRO = OD_CILINDRO;
        this.OD_ADICION = OD_ADICION;
        this.OD_AGUDEZA = OD_AGUDEZA;
        this.OI_ESFERA = OI_ESFERA;
        this.OI_CILINDRO = OI_CILINDRO;
        this.OI_ADICION = OI_ADICION;
        this.OI_AGUDEZA = OI_AGUDEZA;
        DB.save(this);
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getNIF() {
        return NIF;
    }

    public void setNIF(String NIF) {
        this.NIF = NIF;
    }

    public Date getCONSULTA() {
        return CONSULTA;
    }

    public void setCONSULTA(Date CONSULTA) {
        this.CONSULTA = CONSULTA;
    }

    public double getOD_ESFERA() {
        return OD_ESFERA;
    }

    public void setOD_ESFERA(double OD_ESFERA) {
        this.OD_ESFERA = OD_ESFERA;
    }

    public double getOD_CILINDRO() {
        return OD_CILINDRO;
    }

    public void setOD_CILINDRO(double OD_CILINDRO) {
        this.OD_CILINDRO = OD_CILINDRO;
    }

    public double getOD_ADICION() {
        return OD_ADICION;
    }

    public void setOD_ADICION(double OD_ADICION) {
        this.OD_ADICION = OD_ADICION;
    }

    public double getOD_AGUDEZA() {
        return OD_AGUDEZA;
    }

    public void setOD_AGUDEZA(double OD_AGUDEZA) {
        this.OD_AGUDEZA = OD_AGUDEZA;
    }

    public double getOI_ESFERA() {
        return OI_ESFERA;
    }

    public void setOI_ESFERA(double OI_ESFERA) {
        this.OI_ESFERA = OI_ESFERA;
    }

    public double getOI_CILINDRO() {
        return OI_CILINDRO;
    }

    public void setOI_CILINDRO(double OI_CILINDRO) {
        this.OI_CILINDRO = OI_CILINDRO;
    }

    public double getOI_ADICION() {
        return OI_ADICION;
    }

    public void setOI_ADICION(double OI_ADICION) {
        this.OI_ADICION = OI_ADICION;
    }

    public double getOI_AGUDEZA() {
        return OI_AGUDEZA;
    }

    public void setOI_AGUDEZA(double OI_AGUDEZA) {
        this.OI_AGUDEZA = OI_AGUDEZA;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Receta receta = (Receta) o;
        return ID == receta.getID();
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID);
    }

    @Override
    public String toString() {
        return "Receta{" +
                "ID=" + ID +
                ", NIF='" + NIF + '\'' +
                ", CONSULTA=" + CONSULTA +
                ", OD_ESFERA=" + OD_ESFERA +
                ", OD_CILINDRO=" + OD_CILINDRO +
                ", OD_ADICION=" + OD_ADICION +
                ", OD_AGUDEZA=" + OD_AGUDEZA +
                ", OI_ESFERA=" + OI_ESFERA +
                ", OI_CILINDRO=" + OI_CILINDRO +
                ", OI_ADICION=" + OI_ADICION +
                ", OI_AGUDEZA=" + OI_AGUDEZA +
                '}';
    }

    public void borrar() {
        DB.delete(this);
        this.NIF = null;
        this.CONSULTA = null;
        this.OD_ESFERA = -1;
        this.OD_CILINDRO = -1;
        this.OD_ADICION = -1;
        this.OD_AGUDEZA = -1;
        this.OI_ESFERA = -1;
        this.OI_CILINDRO = -1;
        this.OI_ADICION = -1;
        this.OI_AGUDEZA = -1;
    }
}
