package org.example;

import io.ebean.DB;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;
import java.util.List;

public class UsuarioTab extends JFrame{
    private JTable table1;
    private JTextField tOD_ESFERA;
    private JTextField tOI_ESFERA;
    private JTextField tOD_CILINDROTextField;
    private JTextField tOI_CILINDRO;
    private JTextField tOI_ADICION;
    private JTextField tOD_ADICION;
    private JTextField tOI_AGUDEZA;
    private JButton actualizarButton;
    private JButton salirButton;
    private JButton añadirButton;
    private JButton borrarButton;
    private JButton limpiarButton;
    public JPanel mainPanel;
    private JTextField tOD_AGUDEZA;
    private JTextField tCliente;

    Cliente cliente;

    private Receta recetaSeleccionada;

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

        añadirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Añadiendo cita");
                try {
                    double OD_ESFERA = Double.parseDouble(tOD_ESFERA.getText());
                    double OI_ESFERA = Double.parseDouble(tOI_ESFERA.getText());
                    double OD_CILINDRO = Double.parseDouble(tOD_CILINDROTextField.getText());
                    double OI_CILINDRO = Double.parseDouble(tOI_CILINDRO.getText());
                    double OD_ADICION = Double.parseDouble(tOD_ADICION.getText());
                    double OI_ADICION = Double.parseDouble(tOI_ADICION.getText());
                    double OD_AGUDEZA = Double.parseDouble(tOD_AGUDEZA.getText());
                    double OI_AGUDEZA = Double.parseDouble(tOI_AGUDEZA.getText());
                    String NIF = seleccionado.getNIF();
                    Date date = new Date(2022,12,8);
                    Receta nuevaReceta = new Receta(NIF,date,OD_ESFERA,OD_CILINDRO,OD_ADICION,OD_AGUDEZA,OI_ESFERA,OI_CILINDRO,OI_ADICION,OI_AGUDEZA);
                    // TODO Necesitamos meter el calendario para seleccionar la fecha

                    DB.insert(nuevaReceta);
                } catch (Exception ex){
                    ex.printStackTrace();
                }
            }
        });


        actualizarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Actualizando cita");
                if (recetaSeleccionada == null)
                {
                    System.out.println("No se puede actualizar si un atributo está sin marcar");
                    return;
                }

                try {
                    if (Double.parseDouble(tOD_ESFERA.getText()) != recetaSeleccionada.getOD_ESFERA())
                        recetaSeleccionada.setOD_ESFERA(Double.parseDouble(tOD_ESFERA.getText()));
                    if (Double.parseDouble(tOI_ESFERA.getText()) != recetaSeleccionada.getOI_ESFERA())
                        recetaSeleccionada.setOI_ESFERA(Double.parseDouble(tOI_ESFERA.getText()));
                    if (Double.parseDouble(tOD_CILINDROTextField.getText()) != recetaSeleccionada.getOD_CILINDRO())
                        recetaSeleccionada.setOI_ESFERA(Double.parseDouble(tOD_CILINDROTextField.getText()));
                    if (Double.parseDouble(tOI_CILINDRO.getText()) != recetaSeleccionada.getOI_CILINDRO())
                        recetaSeleccionada.setOI_ESFERA(Double.parseDouble(tOI_CILINDRO.getText()));
                    if (Double.parseDouble(tOD_ADICION.getText()) != recetaSeleccionada.getOD_ADICION())
                        recetaSeleccionada.setOI_ESFERA(Double.parseDouble(tOD_ADICION.getText()));
                    if (Double.parseDouble(tOI_ADICION.getText()) != recetaSeleccionada.getOI_ADICION())
                        recetaSeleccionada.setOI_ESFERA(Double.parseDouble(tOI_ADICION.getText()));
                    if (Double.parseDouble(tOD_AGUDEZA.getText()) != recetaSeleccionada.getOD_AGUDEZA())
                        recetaSeleccionada.setOI_ESFERA(Double.parseDouble(tOD_AGUDEZA.getText()));
                    if (Double.parseDouble(tOI_AGUDEZA.getText()) != recetaSeleccionada.getOI_AGUDEZA())
                        recetaSeleccionada.setOI_ESFERA(Double.parseDouble(tOI_AGUDEZA.getText()));


                    DB.update(recetaSeleccionada);
                }catch (Exception ex)
                {
                    ex.printStackTrace();
                }
            }
        });

        borrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Borrando cita");
                if (recetaSeleccionada == null)
                    return;
                recetaSeleccionada.borrar();
            }
        });

        limpiarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Limpiando campos");
                clearSelection();
            }
        });

        table1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int [] data= table1.getSelectedRows();
                if (data.length == 0)
                {
                    clearSelection();
                    return;
                }
                int recetaID = (int) table1.getModel().getValueAt(data[0], 0);
                recetaSeleccionada = new Receta(recetaID);
                updateTextArea();
            }
        });

    }

    private void updateTextArea() {
        if (recetaSeleccionada == null)
        {
            clearSelection();
            return;
        }

        tOD_ESFERA.setText(String.valueOf(recetaSeleccionada.getOD_ESFERA()));
        tOD_ADICION.setText(String.valueOf(recetaSeleccionada.getOD_ADICION()));
        tOI_ESFERA.setText(String.valueOf(recetaSeleccionada.getOI_ESFERA()));
        tOI_ADICION.setText(String.valueOf(recetaSeleccionada.getOI_ADICION()));
        tOD_AGUDEZA.setText(String.valueOf(recetaSeleccionada.getOD_AGUDEZA()));
        tOI_AGUDEZA.setText(String.valueOf(recetaSeleccionada.getOI_AGUDEZA()));
        tOD_CILINDROTextField.setText(String.valueOf(recetaSeleccionada.getOD_CILINDRO()));
        tOI_CILINDRO.setText(String.valueOf(recetaSeleccionada.getOI_CILINDRO()));
    }

    private void clearSelection() {
        recetaSeleccionada = null;
        tOD_ESFERA.setText("");
        tOD_ADICION.setText("");
        tOI_ESFERA.setText("");
        tOI_ADICION.setText("");
        tOD_AGUDEZA.setText("");
        tOI_AGUDEZA.setText("");
        tOD_CILINDROTextField.setText("");
        tOI_CILINDRO.setText("");
    }

    private void createUIComponents() {
        loadTable();
        System.out.println("He terminado de cargar la tabla");
        Font font1 = new Font("SansSerif", Font.BOLD, 20);

        tCliente = new JTextField(cliente.toString());
        tCliente.setFont(font1);

        //Set JTextField font using new created font

    }

    private void loadTable() {
        String[] columnNames = {"ID", "NIF", "CONSULTA", "OD_ESFERA", "OD_CILINDRO","OD_ADICION","OD_AGUDEZA", "OI_ESFERA", "OI_CILINDRO","OI_ADICION","OI_AGUDEZA"};
        List<Receta> recetas = Receta.listaRecetas();
        Object [][]data = new Object[recetas.size()][11];
        int i = 0;
        for (Receta receta : recetas) {
            data[i][0] = receta.getID();
            data[i][1] = receta.getNIF();
            data[i][2] = receta.getCONSULTA();
            data[i][3] = receta.getOD_ESFERA();
            data[i][4] = receta.getOD_CILINDRO();
            data[i][5] = receta.getOD_ADICION();
            data[i][6] = receta.getOD_AGUDEZA();
            data[i][7] = receta.getOI_ESFERA();
            data[i][8] = receta.getOI_CILINDRO();
            data[i][9] = receta.getOI_ADICION();
            data[i][10] = receta.getOI_AGUDEZA();
            i++;
        }
        table1 = new JTable(data, columnNames);
    }
}
