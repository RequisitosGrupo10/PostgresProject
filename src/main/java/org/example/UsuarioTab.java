package org.example;

import com.toedter.calendar.JDateChooser;
import io.ebean.DB;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.Date;

public class UsuarioTab extends JFrame {
    JTable table1;
    private JTextField tOD_ESFERA;
    private JTextField tOI_ESFERA;
    private JTextField tOD_CILINDRO;
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

    private Receta receta;

    public UsuarioTab(Cliente cliente) {
        super("Registro");
        this.cliente = cliente;
        setContentPane(mainPanel);
        table1.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int[] data = table1.getSelectedRows();
                if (data.length == 0) {
                    receta = null;
                } else {
                    int recetaID = (int) table1.getValueAt(data[0], 0);
                    receta = Receta.getReceta(recetaID);
                }
                mostrarRecetaSeleccionada();
            }
        });
        salirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showParent();
                UsuarioTab.super.dispose();
            }
        });

        añadirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    double OD_ESFERA = Double.parseDouble(tOD_ESFERA.getText());
                    double OI_ESFERA = Double.parseDouble(tOI_ESFERA.getText());
                    double OD_CILINDRO = Double.parseDouble(tOD_CILINDRO.getText());
                    double OI_CILINDRO = Double.parseDouble(tOI_CILINDRO.getText());
                    double OD_ADICION = Double.parseDouble(tOD_ADICION.getText());
                    double OI_ADICION = Double.parseDouble(tOI_ADICION.getText());
                    double OD_AGUDEZA = Double.parseDouble(tOD_AGUDEZA.getText());
                    double OI_AGUDEZA = Double.parseDouble(tOI_AGUDEZA.getText());
                    String NIF = cliente.getNIF();
                    Date date = new java.sql.Date(dateChooser.getDate().getTime());
                    Receta nuevaReceta = new Receta(NIF, date, OD_ESFERA, OD_CILINDRO, OD_ADICION, OD_AGUDEZA, OI_ESFERA, OI_CILINDRO, OI_ADICION, OI_AGUDEZA);
                    model.addRow(new Object[]{nuevaReceta.getID(), nuevaReceta.getNIF(), nuevaReceta.getCONSULTA(),
                            nuevaReceta.getOD_ESFERA(), nuevaReceta.getOD_CILINDRO(), nuevaReceta.getOD_ADICION(), nuevaReceta.getOD_AGUDEZA(),
                            nuevaReceta.getOI_ESFERA(), nuevaReceta.getOI_CILINDRO(), nuevaReceta.getOI_ADICION(), nuevaReceta.getOI_AGUDEZA()});

                    receta = null;
                    table1.clearSelection();
                    mostrarRecetaSeleccionada();


                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        actualizarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (receta == null) {
                    JOptionPane.showMessageDialog(null, "No se puede actualizar un objeto sin seleccionar", "ERROR", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                try {
                    if (Double.parseDouble(tOD_ESFERA.getText()) != receta.getOD_ESFERA())
                        receta.setOD_ESFERA(Double.parseDouble(tOD_ESFERA.getText()));
                    if (Double.parseDouble(tOI_ESFERA.getText()) != receta.getOI_ESFERA())
                        receta.setOI_ESFERA(Double.parseDouble(tOI_ESFERA.getText()));
                    if (Double.parseDouble(tOD_CILINDRO.getText()) != receta.getOD_CILINDRO())
                        receta.setOD_CILINDRO(Double.parseDouble(tOD_CILINDRO.getText()));
                    if (Double.parseDouble(tOI_CILINDRO.getText()) != receta.getOI_CILINDRO())
                        receta.setOI_CILINDRO(Double.parseDouble(tOI_CILINDRO.getText()));
                    if (Double.parseDouble(tOD_ADICION.getText()) != receta.getOD_ADICION())
                        receta.setOD_ADICION(Double.parseDouble(tOD_ADICION.getText()));
                    if (Double.parseDouble(tOI_ADICION.getText()) != receta.getOI_ADICION())
                        receta.setOI_ADICION(Double.parseDouble(tOI_ADICION.getText()));
                    if (Double.parseDouble(tOD_AGUDEZA.getText()) != receta.getOD_AGUDEZA())
                        receta.setOD_AGUDEZA(Double.parseDouble(tOD_AGUDEZA.getText()));
                    if (Double.parseDouble(tOI_AGUDEZA.getText()) != receta.getOI_AGUDEZA())
                        receta.setOI_AGUDEZA(Double.parseDouble(tOI_AGUDEZA.getText()));
                    if (dateChooser.getDate().compareTo(receta.getCONSULTA()) != 0)
                        receta.setCONSULTA(new Date(dateChooser.getDate().getTime()));

                    DB.update(receta);

                    receta = null;
                    table1.clearSelection();
                    mostrarRecetaSeleccionada();
                    loadData();

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        borrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (receta == null) {
                    JOptionPane.showMessageDialog(null, "No se puede borrar un objeto sin seleccionar", "ERROR", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                receta.borrar();
                receta = null;
                mostrarRecetaSeleccionada();
                loadData();
            }
        });

        limpiarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                receta = null;
                table1.clearSelection();
                mostrarRecetaSeleccionada();
            }
        });
        setFocusListeners();

    }

    private void mostrarRecetaSeleccionada() {
        if (receta == null) {
            tOD_ESFERA.setText("");
            tOD_ADICION.setText("");
            tOI_ESFERA.setText("");
            tOI_ADICION.setText("");
            tOD_AGUDEZA.setText("");
            tOI_AGUDEZA.setText("");
            tOD_CILINDRO.setText("");
            tOI_CILINDRO.setText("");
        } else {
            tOD_ESFERA.setText(String.valueOf(receta.getOD_ESFERA()));
            tOD_ADICION.setText(String.valueOf(receta.getOD_ADICION()));
            tOI_ESFERA.setText(String.valueOf(receta.getOI_ESFERA()));
            tOI_ADICION.setText(String.valueOf(receta.getOI_ADICION()));
            tOD_AGUDEZA.setText(String.valueOf(receta.getOD_AGUDEZA()));
            tOI_AGUDEZA.setText(String.valueOf(receta.getOI_AGUDEZA()));
            tOD_CILINDRO.setText(String.valueOf(receta.getOD_CILINDRO()));
            tOI_CILINDRO.setText(String.valueOf(receta.getOI_CILINDRO()));
            dateChooser.setDate(receta.getCONSULTA());
        }
    }

    private void createUIComponents() {

        dateChooser = new JDateChooser();
        dateChooser.setDate(new java.util.Date());
        dateChooser.setDateFormatString("dd/MM/yyyy");

        model = new DefaultTableModel();
        model.setColumnIdentifiers(new String[]{"ID", "NIF", "CONSULTA", "OD_ESFERA", "OD_CILINDRO", "OD_ADICION", "OD_AGUDEZA", "OI_ESFERA", "OI_CILINDRO", "OI_ADICION", "OI_AGUDEZA"});
        loadTable();

        Font font1 = new Font("SansSerif", Font.BOLD, 20);

        tCliente = new JTextField(cliente.toString());
        tCliente.setFont(font1);
    }

    private void loadTable() {
        table1 = new JTable();
        table1.setModel(model);
        table1.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        loadData();
    }

    private void loadData() {
        int i = model.getRowCount();
        while (i > 0) {
            model.removeRow(i - 1);
            i--;
        }
        for (Receta receta : Receta.listaRecetasCliente(cliente)) {
            model.insertRow(i, new Object[]{receta.getID(), receta.getNIF(), receta.getCONSULTA(), receta.getOD_ESFERA(), receta.getOD_CILINDRO(), receta.getOD_ADICION(), receta.getOD_AGUDEZA(), receta.getOI_ESFERA(), receta.getOI_CILINDRO(), receta.getOI_ADICION(), receta.getOI_AGUDEZA()});
            i++;
        }
    }

    private void showParent() {
        Oftalmologia.getInstance().toggleVisibility();
    }

    private void setFocusListeners() {
        tOD_ESFERA.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                tOD_ESFERA.selectAll();
            }
        });
        tOI_ESFERA.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                tOI_ESFERA.selectAll();
            }
        });
        tOD_ADICION.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                tOD_ADICION.selectAll();
            }
        });
        tOI_ADICION.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                tOI_ADICION.selectAll();
            }
        });

        tOD_AGUDEZA.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                tOD_AGUDEZA.selectAll();
            }
        });
        tOI_AGUDEZA.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                tOI_AGUDEZA.selectAll();
            }
        });

        tOD_CILINDRO.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                tOD_CILINDRO.selectAll();
            }
        });
        tOI_CILINDRO.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                tOI_CILINDRO.selectAll();
            }
        });
    }
}
