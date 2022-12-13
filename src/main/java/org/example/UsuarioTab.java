package org.example;

import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;
import io.ebean.DB;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.Date;
import java.util.List;

public class UsuarioTab extends JFrame{
    JTable table1;
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

    private JDateChooser dateChooser;

    private DefaultTableModel model;

    private Cliente cliente;

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
                    Date date = new java.sql.Date(dateChooser.getDate().getTime());
                    Receta nuevaReceta = new Receta(NIF,date,OD_ESFERA,OD_CILINDRO,OD_ADICION,OD_AGUDEZA,OI_ESFERA,OI_CILINDRO,OI_ADICION,OI_AGUDEZA);
                    model.addRow(new Object [] {nuevaReceta.getID(), NIF, date.toString(), tOD_ESFERA.getText(), tOD_CILINDROTextField.getText(),
                            tOD_ADICION.getText(),tOD_AGUDEZA.getText(),tOI_ESFERA.getText(),
                            tOI_CILINDRO.getText(), tOI_ADICION.getText(), tOI_AGUDEZA.getText() } );

                    recetaSeleccionada=null;
                    mostrarRecetaSeleccionada();


                } catch (Exception ex){
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
                }
            }
        });


        actualizarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Actualizando cita");
                if (recetaSeleccionada == null)
                {
                    JOptionPane.showMessageDialog(null, "No se puede actualizar un objeto sin seleccionar", "ERROR", JOptionPane.ERROR_MESSAGE);
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

                    recetaSeleccionada=null;
                    mostrarRecetaSeleccionada();

                }catch (Exception ex)
                {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        borrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Borrando cita");
                if (recetaSeleccionada == null) {
                    JOptionPane.showMessageDialog(null, "No se puede borrar un objeto sin seleccionar", "ERROR", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                recetaSeleccionada.borrar();
                recetaSeleccionada=null;
                mostrarRecetaSeleccionada();
            }
        });

        limpiarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Limpiando campos");
                recetaSeleccionada=null;
                mostrarRecetaSeleccionada();
            }
        });

        table1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int [] data= table1.getSelectedRows();
                if (data.length == 0)
                {
                    recetaSeleccionada = null;
                    mostrarRecetaSeleccionada();
                }else {
                    int recetaID = (int) table1.getModel().getValueAt(data[0], 0);
                    System.out.println(recetaID);
                    recetaSeleccionada = new Receta(recetaID);
                    mostrarRecetaSeleccionada();
                }
            }
        });

        mainPanel.addComponentListener(new ComponentAdapter() {
        });
    }

    private void mostrarRecetaSeleccionada() {
        if (recetaSeleccionada == null)
        {
            tOD_ESFERA.setText("");
            tOD_ADICION.setText("");
            tOI_ESFERA.setText("");
            tOI_ADICION.setText("");
            tOD_AGUDEZA.setText("");
            tOI_AGUDEZA.setText("");
            tOD_CILINDROTextField.setText("");
            tOI_CILINDRO.setText("");
        }else {

            tOD_ESFERA.setText(String.valueOf(recetaSeleccionada.getOD_ESFERA()));
            tOD_ADICION.setText(String.valueOf(recetaSeleccionada.getOD_ADICION()));
            tOI_ESFERA.setText(String.valueOf(recetaSeleccionada.getOI_ESFERA()));
            tOI_ADICION.setText(String.valueOf(recetaSeleccionada.getOI_ADICION()));
            tOD_AGUDEZA.setText(String.valueOf(recetaSeleccionada.getOD_AGUDEZA()));
            tOI_AGUDEZA.setText(String.valueOf(recetaSeleccionada.getOI_AGUDEZA()));
            tOD_CILINDROTextField.setText(String.valueOf(recetaSeleccionada.getOD_CILINDRO()));
            tOI_CILINDRO.setText(String.valueOf(recetaSeleccionada.getOI_CILINDRO()));
        }
    }

    private void createUIComponents() {

        dateChooser = new JDateChooser();
        dateChooser.setDateFormatString("dd/MM/yyyy");

        model=new DefaultTableModel();
        model.setColumnIdentifiers(new String[]  {"ID", "NIF", "CONSULTA", "OD_ESFERA", "OD_CILINDRO","OD_ADICION","OD_AGUDEZA", "OI_ESFERA", "OI_CILINDRO","OI_ADICION","OI_AGUDEZA"});
        loadTable();

        System.out.println("He terminado de cargar la tabla");
        Font font1 = new Font("SansSerif", Font.BOLD, 20);

        tCliente = new JTextField(cliente.toString());
        tCliente.setFont(font1);
    }

    private void loadTable() {
        table1 = new JTable();
        table1.setModel(model);
        table1.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

        int i = 0;
        for (Receta receta : Receta.listaRecetasCliente(cliente)) {
            System.out.println(receta.toString());
            model.insertRow(i,new Object[]{receta.getID(),receta.getNIF(),receta.getCONSULTA(),receta.getOD_ESFERA(), receta.getOD_CILINDRO(),receta.getOD_ADICION(),receta.getOD_AGUDEZA(),receta.getOI_ESFERA(),receta.getOI_CILINDRO(),receta.getOI_ADICION(),receta.getOI_AGUDEZA()});
            i++;
        }
    }
}
