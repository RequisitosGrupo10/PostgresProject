package org.example;

import io.ebean.DB;
import io.ebean.Database;
public class Main {

  public static void main(String[] args) {

    System.out.println("running ...");
    Database db = DB.getDefault();

    Cliente c = new Cliente("222", "Javier", "Luque", 20);
    DB.insert(c);

    //DB.save(c);

    System.out.println("done");
  }
}
