package com.proyectofinalargprogetapa2.interfaz;

import com.proyectofinalargprogetapa2.model.Categoria;
import com.proyectofinalargprogetapa2.model.Orden;
import com.proyectofinalargprogetapa2.model.Tecnico;
import java.awt.BorderLayout;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class Panels {

    public Panels() {
    }

    public int menuOrden() {
        boolean noEsNumero = true;
        int opcion = 3;
        while (noEsNumero) {
            String opcionStr = JOptionPane.showInputDialog("Menú \n 1. Crear orden \n 2. Ver ordenes \n 3. Salir");
            if (opcionStr != null) {
                try {
                    opcion = Integer.parseInt(opcionStr);
                    noEsNumero = false;
                } catch (NumberFormatException e) {
                    // Capturar la excepción si la entrada no es un número válido
                    JOptionPane.showMessageDialog(null, "Por favor, ingrese un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }else{
                opcion = 3;
                noEsNumero = false;
            }
        }
        return opcion;
    }

    public int dniCliente() {
        boolean noEsNumero = true;
        int opcion = 3;
        while (noEsNumero) {
            String opcionStr = JOptionPane.showInputDialog("Ingrese el dni del cliente");
            if (opcionStr != null) {
                try {
                    opcion = Integer.parseInt(opcionStr);
                    noEsNumero = false;
                } catch (NumberFormatException e) {
                    // Capturar la excepción si la entrada no es un número válido
                    JOptionPane.showMessageDialog(null, "Por favor, ingrese un número válido de DNI.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
        return opcion;
    }

    public String[] datosCliente() {
        String nom = JOptionPane.showInputDialog("Ingrese el nombre del cliente");
        String dir = JOptionPane.showInputDialog("Ingrese la dirección del cliente");
        String mail = JOptionPane.showInputDialog("Ingrese el mail del cliente");
        String datos[] = {nom, dir, mail};
        return datos;
    }

    public String[] datosOrden(List<Categoria> categorias, List<Tecnico> tecnicos) {
        String des = JOptionPane.showInputDialog("Añada una descripción");
        
        //float cos = Float.parseFloat(JOptionPane.showInputDialog("Añada el costo"));
        
        boolean noEsNumero = true;
        //float opcion;
        String costoStr=null;
        while (noEsNumero) {
            costoStr = JOptionPane.showInputDialog("Añada el costo");
            if (costoStr != null) {
                try {
                    Float.parseFloat(costoStr);
                    noEsNumero = false;
                } catch (NumberFormatException e) {
                    // Capturar la excepción si la entrada no es un número válido
                    JOptionPane.showMessageDialog(null, "Por favor, ingrese un costo válido.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
        
        JEditorPane tablaCat = tablaCategoria(categorias);
        // Crear un campo de entrada para el usuario
        JTextField userInputField = new JTextField(10);
        // Crear un panel que contenga la tabla y el campo de entrada
        JPanel panelCat = new JPanel(new BorderLayout());
        panelCat.add(new JScrollPane(tablaCat), BorderLayout.CENTER);
        panelCat.add(new JLabel("Ingrese datos:"), BorderLayout.SOUTH);
        panelCat.add(userInputField, BorderLayout.SOUTH);
        
        int res = JOptionPane.showOptionDialog(null, panelCat, "Tabla y Entrada de Datos",JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, null, null);
        String cat = userInputField.getText();
        
        JEditorPane tablaTec = tablaTecnico(tecnicos);
        // Crear un campo de entrada para el usuario
        JTextField userInputFieldTec = new JTextField(10);
        // Crear un panel que contenga la tabla y el campo de entrada
        JPanel panelTec = new JPanel(new BorderLayout());
        panelTec.add(new JScrollPane(tablaTec), BorderLayout.CENTER);
        panelTec.add(new JLabel("Ingrese datos:"), BorderLayout.SOUTH);
        panelTec.add(userInputFieldTec, BorderLayout.SOUTH);
        
        int res1 = JOptionPane.showOptionDialog(null, panelTec, "Tabla y Entrada de Datos",JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, null, null);
        String tec = userInputField.getText();
        
        System.out.println("cat "+cat+" tec "+tec);
        String datos[] = {des, costoStr,cat, tec};
        return datos;
    }
    
    public void mostrarTabla(List<Orden> ordenes) {
        // Crear una representación de la tabla en formato HTML
        StringBuilder tablaHTML = new StringBuilder("<html><body><table border='1'>");

        // Encabezados de la tabla
        tablaHTML.append("<tr>");
        tablaHTML.append("<th>Cliente</th>");
        tablaHTML.append("<th>Nombre del Técnico</th>");
        tablaHTML.append("<th>Fecha de la Orden</th>");
        tablaHTML.append("<th>Categoría</th>");
        tablaHTML.append("</tr>");

        // Datos de la lista de órdenes
        for (Orden orden : ordenes) {
            tablaHTML.append("<tr>");
            tablaHTML.append("<td>").append(orden.getCliente().getNombre()).append("</td>");
            tablaHTML.append("<td>").append(orden.getTecnico().getNombreApellido()).append("</td>");
            tablaHTML.append("<td>").append(orden.getFecha().format(DateTimeFormatter.ISO_LOCAL_DATE)).append("</td>");
            tablaHTML.append("<td>").append(orden.getCategoria().getDescripcion()).append("</td>");
            tablaHTML.append("</tr>");
        }

        tablaHTML.append("</table></body></html>");

        // Crear un JEditorPane para mostrar la tabla
        JEditorPane editorPane = new JEditorPane("text/html", tablaHTML.toString());
        editorPane.setEditable(false);

        // Mostrar el cuadro de diálogo con la tabla
        JOptionPane.showMessageDialog(null, editorPane, "Tabla de Órdenes", JOptionPane.INFORMATION_MESSAGE);
    }
    
    private static JEditorPane tablaCategoria(List<Categoria> categorias) {
        // Crear una representación de la tabla en formato HTML
        StringBuilder tablaHTML = new StringBuilder("<html><body><table border='1'>");

        // Encabezados de la tabla
        tablaHTML.append("<tr>");
        tablaHTML.append("<th>Id</th>");
        tablaHTML.append("<th>Categoría</th>");
        tablaHTML.append("</tr>");

        // Datos de la lista de órdenes
        for (Categoria categoria : categorias) {
            tablaHTML.append("<tr>");
            tablaHTML.append("<td>").append(categoria.getId()).append("</td>");
            tablaHTML.append("<td>").append(categoria.getDescripcion()).append("</td>");
            tablaHTML.append("</tr>");
        }

        tablaHTML.append("</table></body></html>");

        // Crear un JEditorPane para mostrar la tabla
        JEditorPane editorPane = new JEditorPane("text/html", tablaHTML.toString());
        editorPane.setEditable(false);
        return editorPane;
        // Mostrar el cuadro de diálogo con la tabla
        //JOptionPane.showMessageDialog(null, editorPane, "Tabla de Órdenes", JOptionPane.INFORMATION_MESSAGE);
    }
    private static JEditorPane tablaTecnico(List<Tecnico> tecnicos) {
        // Crear una representación de la tabla en formato HTML
        StringBuilder tablaHTML = new StringBuilder("<html><body><table border='1'>");

        // Encabezados de la tabla
        tablaHTML.append("<tr>");
        tablaHTML.append("<th>Id</th>");
        tablaHTML.append("<th>Nombre del Técnico</th>");
        tablaHTML.append("</tr>");

        // Datos de la lista de órdenes
        for (Tecnico tecnico : tecnicos) {
            tablaHTML.append("<tr>");
            tablaHTML.append("<td>").append(tecnico.getId()).append("</td>");
            tablaHTML.append("<td>").append(tecnico.getNombreApellido()).append("</td>");
            tablaHTML.append("</tr>");
        }

        tablaHTML.append("</table></body></html>");

        // Crear un JEditorPane para mostrar la tabla
        JEditorPane editorPane = new JEditorPane("text/html", tablaHTML.toString());
        editorPane.setEditable(false);
        return editorPane;
        // Mostrar el cuadro de diálogo con la tabla
        //JOptionPane.showMessageDialog(null, editorPane, "Tabla de Órdenes", JOptionPane.INFORMATION_MESSAGE);
    }
}
