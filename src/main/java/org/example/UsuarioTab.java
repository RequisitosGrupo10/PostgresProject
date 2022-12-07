package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UsuarioTab extends JFrame{
    private JTable table1;
    private JTextField tOD_ESFERA;
    private JTextField tOI_ESFERA;
    private JTextField tOD_CILINDROTextField;
    private JTextField tOI_CILINDRO;
    private JTextField tOI_ADICION;
    private JTextField tOD_ADICION;
    private JTextField textField7;
    private JTextField tOI_AGUDEZA;
    private JButton actualizarButton;
    private JButton salirButton;
    private JButton añadirButton;
    private JButton borrarButton;
    private JButton limpiarButton;
    public JPanel mainPanel;
    private JLabel tOD_AGUDEZA;
    private JTextField tCliente;

    Cliente cliente;

    public UsuarioTab(Cliente seleccionado)
    {
        super("Registro");

        cliente = seleccionado;
        setContentPane(mainPanel);
        salirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UsuarioTab.super.dispose();
            }
        });
    }

    private void createUIComponents() {
        Font font1 = new Font("SansSerif", Font.BOLD, 20);

        tCliente = new JTextField(cliente.toString());
        tCliente.setFont(font1);

        //Set JTextField font using new created font

    }
}
