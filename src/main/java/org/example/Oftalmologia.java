package org.example;

import io.ebean.DB;
import org.example.Cliente;
import org.example.UsuarioTab;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.Vector;

public class Oftalmologia extends JFrame{
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

    public Oftalmologia() {
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
                System.out.println("Añadiendo nuevo estudiante");
                try {
                    String nif = tNIF.getText();
                    String nombre = tNombre.getText();
                    String apellido = tApellidos.getText();
                    Integer edad = Integer.parseInt(lEdad.getModel().getElementAt(lEdad.getSelectedIndex()).toString());

                    seleccionado = new Cliente(nif, nombre, apellido, edad);
                    seleccionado=null;
                    //Actualizamos tabla
                    model.addRow(new Object[] {nif, nombre, apellido, edad});
                    mostrarSeleccionado();
                } catch (Exception ex){
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        actualizarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (seleccionado == null
                        ||tNombre.getText() == null || tApellidos.getText() == null
                        || tNIF.getText() == null|| lEdad.getSelectedIndex() == -1)
                {
                    JOptionPane.showMessageDialog(null, "No se puede actualizar si no hay selección", "ERROR", JOptionPane.ERROR_MESSAGE);
                }else {
                    try {
                        int index = findInTable(seleccionado);
                        if (!tNombre.getText().equals(seleccionado.getNOMBRE())) {
                            seleccionado.setNOMBRE(tNombre.getText());
                        }

                        if (!tApellidos.getText().equals(seleccionado.getAPELLIDOS()))
                            seleccionado.setAPELLIDOS(tApellidos.getText());

                        Integer seleccionadoEdad = Integer.parseInt(lEdad.getModel().getElementAt(lEdad.getSelectedIndex()).toString());
                        if (!seleccionadoEdad.equals(seleccionado.getEDAD()))
                            seleccionado.setEDAD(seleccionadoEdad);

                        DB.update(seleccionado);
                        model.removeRow(index);
                        //Actualizamos valor en tabla
                        model.insertRow(index, new Object[]{seleccionado.getNIF(), seleccionado.getNOMBRE(), seleccionado.getAPELLIDOS(), seleccionado.getEDAD()});
                        seleccionado = null;
                        mostrarSeleccionado();
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
                seleccionado=null;
                mostrarSeleccionado();
            }
        });
        limpiarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                seleccionado=null;
                mostrarSeleccionado();
            }
        });
        revisionesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (seleccionado != null)
                {
                    JFrame frame = new UsuarioTab(seleccionado);

                    // Display the window.
                    frame.pack();
                    frame.setVisible(true);
                }else{
                    JOptionPane.showMessageDialog(null, "No se pueden ver revisiones de un objeto vacío", "ERROR", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        table1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int [] data= table1.getSelectedRows();
                if (data.length == 0)
                {
                    seleccionado = null;
                }else {
                    String seleccionadoNIF = table1.getModel().getValueAt(data[0], 0).toString();
                    seleccionado = new Cliente(seleccionadoNIF);
                }
                mostrarSeleccionado();
            }
        });
    }
    private int findInTable(Cliente cliente){
        int i = 0;
        for (Vector o : model.getDataVector()){
            if (o.get(0).equals(cliente.getNIF())){
                break;
            }
        }
        return i;
    }
    private void createUIComponents() {
        model=new DefaultTableModel();
        model.setColumnIdentifiers(new String[]{"NIF", "NOMBRE", "APELLIDOS", "EDAD"});
        loadTable();
        loadEdades();
    }

    private void loadEdades() {
        lEdad = new JList<>(Cliente.listaEdades().toArray());
    }

    private void loadTable() {
        table1 = new JTable(model);
        table1.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        //Borramos las filas insertadas

        int i = 0;
        for (Cliente cliente : Cliente.listaClientes()) {
            System.out.println(cliente.getNIF() + cliente.getNOMBRE() + cliente.getAPELLIDOS() + cliente.getEDAD());
            model.insertRow(i,new Object[]{cliente.getNIF(), cliente.getNOMBRE(), cliente.getAPELLIDOS(), cliente.getEDAD()});
        }
    }

    private void mostrarSeleccionado(){
        if (seleccionado!= null){
            tNIF.setText(seleccionado.getNIF());
            tNombre.setText(seleccionado.getNOMBRE());
            tApellidos.setText(seleccionado.getAPELLIDOS());
            lEdad.setSelectedValue(seleccionado.getEDAD(),true);
        }else{
            tNIF.setText("");
            tNombre.setText("");
            tApellidos.setText("");
            lEdad.clearSelection();
            table1.clearSelection();
        }
    }
}
