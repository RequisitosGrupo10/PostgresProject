package org.example;

import io.ebean.DB;
import org.example.query.QCliente;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Cliente {
    @Id
    private String NIF;
    private String NOMBRE;
    private String APELLIDOS;
    private int EDAD;

    private void checkSQLInjection(String string)
    {
        if (string.contains(";") || string.indexOf("DROP") != -1)
            throw new RuntimeException("Posible inyección de datos");
    }

    public Cliente(String NIF, String NOMBRE, String APELLIDOS, int EDAD) {
        checkSQLInjection(NIF);
        checkSQLInjection(NOMBRE);
        checkSQLInjection(APELLIDOS);

        this.NIF = NIF;
        this.NOMBRE = NOMBRE;
        this.APELLIDOS = APELLIDOS;
        this.EDAD = EDAD;
        DB.save(this);
    }

    public static Cliente getCliente(String nif) {
        return new QCliente().NIF.iequalTo(nif).findOne();
    }

    public static List<Cliente> listaClientes() {
        List<Cliente> res = new QCliente().findList();
        return res;
    }

    public void setNOMBRE(String NOMBRE) {
        checkSQLInjection(NOMBRE);
        this.NOMBRE = NOMBRE;
    }

    public void setAPELLIDOS(String APELLIDOS) {
        checkSQLInjection(APELLIDOS);
        this.APELLIDOS = APELLIDOS;
    }

    public void setEDAD(int EDAD) {
        this.EDAD = EDAD;
    }


    public String getNIF() {
        return NIF;
    }

    public String getNOMBRE() {
        return NOMBRE;
    }

    public String getAPELLIDOS() {
        return APELLIDOS;
    }

    public int getEDAD() {
        return EDAD;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cliente cliente = (Cliente) o;
        return NIF.equals(cliente.NIF);
    }

    @Override
    public int hashCode() {
        return Objects.hash(NIF);
    }

    @Override
    public String toString() {
        return (NIF + ";" + NOMBRE + ";" + APELLIDOS + ";" + EDAD);
    }

    public void borrar() {
        DB.delete(this);
        this.NOMBRE = null;
        this.NIF = null;
        this.APELLIDOS = null;
        this.EDAD = -1;
    }
}
