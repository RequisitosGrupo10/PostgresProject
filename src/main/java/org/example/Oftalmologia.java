package org.example;

import org.example.Cliente;
import org.example.UsuarioTab;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Oftalmologia {
    private JButton añadirButton;
    private JButton salirButton;
    private JButton actualizarButton;
    private JButton borrarButton;
    private JButton limpiarButton;
    private JTable table1;
    private JTextField tNIF;
    public JPanel mainPanel;
    private JLabel NIF;
    private JTextField tNombre;
    private JTextField tApellidos;
    private JButton revisionesButton;
    private JList lEdad;

    private Cliente seleccionado;

    public Oftalmologia() {
        salirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        añadirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        actualizarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        borrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        limpiarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tNIF.setText("");
                tNombre.setText("");
                tApellidos.setText("");
                lEdad.clearSelection();
            }
        });
        revisionesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (seleccionado != null)
                {
                    JFrame frame = new JFrame(seleccionado.toString());
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    frame.setContentPane(new UsuarioTab().mainPanel);
                    // Display the window.
                    frame.pack();
                    frame.setVisible(true);
                }
            }
        });
    }
}
