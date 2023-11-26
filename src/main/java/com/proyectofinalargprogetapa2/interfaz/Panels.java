package com.proyectofinalargprogetapa2.interfaz;

import com.proyectofinalargprogetapa2.model.Categoria;
import com.proyectofinalargprogetapa2.model.Orden;
import com.proyectofinalargprogetapa2.model.Tecnico;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.swing.JEditorPane;
import javax.swing.JOptionPane;

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

    public String[] datosOrden() {
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
        
        String cat = JOptionPane.showInputDialog("Ingrese codigo de categoria");
        String tec = JOptionPane.showInputDialog("Ingrese codigo del tecnico");

        String datos[] = {des, costoStr, cat, tec};
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
}
