package org.example;

import io.ebean.DB;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class Oftalmologia extends JFrame {
    private JButton añadirButton;
    private JButton salirButton;
    private JButton actualizarButton;
    private JButton borrarButton;
    private JButton limpiarButton;
    JTable table1;
    private JTextField tNIF;
    public JPanel mainPanel;
    private JLabel NIF;
    private JTextField tNombre;
    private JTextField tApellidos;
    private JButton revisionesButton;
    private JList lEdad;
    private DefaultTableModel model;
    private Cliente seleccionado;
    private static Oftalmologia singleton;

    private Oftalmologia() {
        // Configuración
        super("Revisión Ocular");
        setContentPane(mainPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Listeners
        salirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        añadirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String nif, nombre, apellido;
                    Integer edad;
                    System.out.println(tNIF.getText().length());
                    if (tNIF.getText().length() == 0)
                        throw new RuntimeException("No hay NIF");
                    if (tNombre.getText().length() == 0)
                        throw new RuntimeException("No hay nombre");
                    if (tApellidos.getText().length() == 0)
                        throw new RuntimeException("No hay apellido");
                    if (lEdad.getSelectedIndex() == -1)
                        throw new RuntimeException("No hay edad seleccionada");
                    nif = tNIF.getText();
                    nombre = tNombre.getText();
                    apellido = tApellidos.getText();
                    edad = Integer.parseInt(lEdad.getModel().getElementAt(lEdad.getSelectedIndex()).toString());

                    new Cliente(nif, nombre, apellido, edad); // Añadir nuevo cliente
                    seleccionado = null;

                    //Actualizamos tabla
                    model.addRow(new Object[]{nif, nombre, apellido, edad});
                    mostrarSeleccionado();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        actualizarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (seleccionado == null
                        || tNombre.getText() == null || tApellidos.getText() == null
                        || tNIF.getText() == null || lEdad.getSelectedIndex() == -1) {
                    JOptionPane.showMessageDialog(null, "No se puede actualizar si no hay selección", "ERROR", JOptionPane.ERROR_MESSAGE);
                } else {
                    try {
                        int index = findInTable(seleccionado);
                        if (!tNombre.getText().equals(seleccionado.getNOMBRE()))
                            seleccionado.setNOMBRE(tNombre.getText());

                        if (!tApellidos.getText().equals(seleccionado.getAPELLIDOS()))
                            seleccionado.setAPELLIDOS(tApellidos.getText());

                        int seleccionadoEdad = Integer.parseInt(lEdad.getModel().getElementAt(lEdad.getSelectedIndex()).toString());
                        if (seleccionadoEdad != seleccionado.getEDAD())
                            seleccionado.setEDAD(seleccionadoEdad);

                        DB.update(seleccionado);
                        seleccionado = null;
                        mostrarSeleccionado();
                        loadData();
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
        borrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (seleccionado == null)
                    return;
                seleccionado.borrar();
                //Actualizamos la tabla
                model.removeRow(findInTable(seleccionado));
                seleccionado = null;
                mostrarSeleccionado();
            }
        });

        limpiarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                seleccionado = null;
                mostrarSeleccionado();
            }
        });
        revisionesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (seleccionado != null) {
                    showRecetas();
                } else {
                    JOptionPane.showMessageDialog(null, "No se pueden ver revisiones de un objeto vacío", "ERROR", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        table1.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int[] data = table1.getSelectedRows();
                if (data.length == 0) {
                    seleccionado = null;
                } else {
                    String seleccionadoNIF = table1.getValueAt(data[0], 0).toString();
                    seleccionado = Cliente.getCliente(seleccionadoNIF);
                }
                mostrarSeleccionado();
            }
        });
    }

    public static Oftalmologia getInstance() {
        if (singleton == null) {
            singleton = new Oftalmologia();
        }
        return singleton;
    }

    private void showRecetas() {
        JFrame frame = new UsuarioTab(seleccionado);

        // Display the window.
        frame.pack();
        frame.setVisible(true);
        toggleVisibility();
    }

    public void toggleVisibility() {
        this.setVisible(!isVisible());
    }

    private int findInTable(Cliente cliente) {
        int i = 0;
        for (Vector o : model.getDataVector()) {
            if (o.get(0).equals(cliente.getNIF())) {
                break;
            }
        }
        return i;
    }

    private void createUIComponents() {
        model = new DefaultTableModel();
        model.setColumnIdentifiers(new String[]{"NIF", "NOMBRE", "APELLIDOS", "EDAD"});
        loadTable();
        loadEdades();
    }

    private void loadEdades() {
        Integer[] e = new Integer[100];
        for (int i = 0; i < e.length; i++) {
            e[i] = i;
        }
        lEdad = new JList<>(e);
    }

    private void loadTable() {
        table1 = new JTable(model);
        table1.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        //Borramos las filas insertadas
        loadData();
    }

    private void loadData() {
        int i = model.getRowCount();
        while (i > 0) {
            model.removeRow(i - 1);
            i--;
        }
        for (Cliente cliente : Cliente.listaClientes()) {
            model.insertRow(i, new Object[]{cliente.getNIF(), cliente.getNOMBRE(), cliente.getAPELLIDOS(), cliente.getEDAD()});
        }
    }

    private void mostrarSeleccionado() {
        if (seleccionado != null) {
            tNIF.setText(seleccionado.getNIF());
            tNombre.setText(seleccionado.getNOMBRE());
            tApellidos.setText(seleccionado.getAPELLIDOS());
            lEdad.setSelectedValue(seleccionado.getEDAD(), true);
        } else {
            tNIF.setText("");
            tNombre.setText("");
            tApellidos.setText("");
            lEdad.clearSelection();
            table1.clearSelection();
        }
    }
}
