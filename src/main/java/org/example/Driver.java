package org.example;

import io.ebean.DB;
import io.ebean.Database;
import io.ebean.DatabaseFactory;
import io.ebean.config.DatabaseConfig;
import io.ebean.datasource.DataSourceConfig;

import javax.swing.*;

public class Driver {
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }

    private static void createAndShowGUI() {
        // Create and set up the window.
        JFrame oftalmologia = Oftalmologia.getInstance();
        Database db = DB.getDefault();

        // Display the window.
        oftalmologia.pack();
        oftalmologia.setVisible(true);
    }
}
