package org.example;

import io.ebean.DB;
import org.example.Cliente;
import org.example.UsuarioTab;

import javax.swing.*;
import javax.swing.table.TableColumn;
import java.awt.event.*;
import java.util.List;

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

                    Cliente nuevoCliente = new Cliente(nif, nombre, apellido, edad);
                    // REFRESH TODO, que hay que hacerlo, vaya
                } catch (Exception ex){
                    ex.printStackTrace();
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
                    System.out.println("No se puede actualizar si un atributo está sin marcar");
                    return;
                }

                try {
                    if (!tNombre.getText().equals(seleccionado.getNOMBRE()))
                        seleccionado.setNOMBRE(tNombre.getText());

                    if (!tApellidos.getText().equals(seleccionado.getAPELLIDOS()))
                        seleccionado.setAPELLIDOS(tApellidos.getText());

                    Integer seleccionadoEdad = Integer.parseInt(lEdad.getModel().getElementAt(lEdad.getSelectedIndex()).toString());
                    if (!seleccionadoEdad.equals(seleccionado.getEDAD()))
                        seleccionado.setEDAD(seleccionadoEdad);

                    DB.update(seleccionado);
                }catch (Exception ex)
                {
                    ex.printStackTrace();
                }
            }
        });
        borrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (seleccionado == null)
                    return;
                seleccionado.borrar();
            }
        });
        limpiarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearSelection();
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
                    clearSelection();
                    return;
                }
                String seleccionadoNIF = table1.getModel().getValueAt(data[0], 0).toString();
                seleccionado = new Cliente(seleccionadoNIF);
                updateTextArea();
            }
        });
    }

    private void updateTextArea() {
        if (seleccionado == null)
        {
            clearSelection();
            return;
        }

        tNIF.setText(seleccionado.getNIF());
        tNombre.setText(seleccionado.getNOMBRE());
        tApellidos.setText(seleccionado.getAPELLIDOS());
    }

    private void clearSelection() {
        seleccionado = null;
        tNIF.setText("");
        tNombre.setText("");
        tApellidos.setText("");
        lEdad.clearSelection();
        table1.clearSelection();
    }

    private void createUIComponents() {
        loadTable();
        loadEdades();
    }

    private void loadEdades() {
        lEdad = new JList<>(Cliente.listaEdades().toArray());
    }

    private void loadTable() {
        String[] columnNames = {"NIF", "NOMBRE", "APELLIDOS", "EDAD"};
        List<Cliente> clientes = Cliente.listaClientes();
        Object [][]data = new Object[clientes.size()][4];
        int i = 0;
        for (Cliente cliente : clientes) {
            data[i][0] = cliente.getNIF();
            data[i][1] = cliente.getNOMBRE();
            data[i][2] = cliente.getAPELLIDOS();
            data[i][3] = cliente.getEDAD();
            i++;
        }
        table1 = new JTable(data, columnNames);
    }
}
