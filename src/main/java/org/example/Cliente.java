package org.example;
import javax.persistence.*;
import java.util.Objects;

@Entity
  public class Cliente {
    @Id
    private String NIF;
    private String NOMBRE;
    private String APELLIDOS;
    private int EDAD;

    public Cliente(String NIF, String NOMBRE, String APELLIDOS, int EDAD) {
      this.NIF = NIF;
      this.NOMBRE = NOMBRE;
      this.APELLIDOS = APELLIDOS;
      this.EDAD = EDAD;
    }

    public void setNOMBRE(String NOMBRE) {
      this.NOMBRE = NOMBRE;
    }

    public void setAPELLIDOS(String APELLIDOS) {
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
      return "Cliente{" +
        "NIF='" + NIF + '\'' +
        ", NOMBRE='" + NOMBRE + '\'' +
        ", APELLIDOS='" + APELLIDOS + '\'' +
        ", EDAD=" + EDAD +
        '}';
    }
  }
