package com.proyectofinalargprogetapa2.app;

import com.proyectofinalargprogetapa2.HibernateUtil;
import com.proyectofinalargprogetapa2.model.Categoria;
import com.proyectofinalargprogetapa2.model.Cliente;
import com.proyectofinalargprogetapa2.model.Orden;
import com.proyectofinalargprogetapa2.model.Tecnico;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.Date;
import java.util.List;
import javax.swing.JEditorPane;
import javax.swing.JOptionPane;
import org.hibernate.Session;

public class App {

    private static Session session;

    public static void main(String[] args) {
        App es = new App();

        es.crearSesion();

        boolean ejecutar = true;

        while (ejecutar) {
            int m = Integer.parseInt(JOptionPane.showInputDialog("Menú \n 1. Crear orden \n 2. Ver ordenes \n 3. Salir"));

            if (m == 1) {
                Cliente cli = es.cargarCliente();
                String des = JOptionPane.showInputDialog("Añada una descripción");

                float cos = Float.parseFloat(JOptionPane.showInputDialog("Añada el costo"));
                boolean est = false;
                int cat = Integer.parseInt(JOptionPane.showInputDialog("Ingrese codigo de categoria"));
                int tec = Integer.parseInt(JOptionPane.showInputDialog("Ingrese codigo del tecnico"));

                es.guardarOrden(des, cos, new Date(), Boolean.FALSE, cli, cat, tec);

            } else if (m == 2) {
                List<Orden> ordenes = es.listaDeOrdenes();
                es.mostrarTabla(ordenes);
            } else if (m == 3) {
                ejecutar = false;
            } else {
                JOptionPane.showMessageDialog(null, "Opción incorrecta");
            }

        }

        

        

        es.cerrarSesion();

    }

    public void crearSesion() {
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
    }

    public void cerrarSesion() {
        session.getTransaction().commit();
        HibernateUtil.getSessionFactory().close();
    }

    private List<Cliente> consultaCliente(int dni) {
        List<Cliente> cli = session.createQuery("SELECT o From Cliente o WHERE o.dni = " + dni, Cliente.class).getResultList();
        return cli;
    }

    private Cliente guardarCliente(int dni, String nombre, String direccion, String mail) {
        Cliente cli = new Cliente();
        cli.setDni(dni);
        cli.setNombre(nombre);
        cli.setDireccion(direccion);
        cli.setMail(mail);
        session.save(cli);
        return cli;
    }

    private void guardarOrden(String descripcion, Float costo, Date fecha, Boolean estado, Cliente cliente, Categoria categoria, Tecnico tecnico) {
        Orden orden = new Orden(descripcion, costo, fecha, estado, cliente, categoria, tecnico);
        session.save(orden);
    }

    private List<Orden> listaDeOrdenes() {
        List<Orden> ordenes = session.createQuery("SELECT o FROM Orden o", Orden.class).getResultList();
        return ordenes;
    }

    public Cliente cargarCliente() {
        int dni = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el dni del cliente"));
        List<Cliente> cli = consultaCliente(dni);

        if (cli.isEmpty()) {
            String nom = JOptionPane.showInputDialog("Ingrese el nombre del cliente");
            String dir = JOptionPane.showInputDialog("Ingrese la dirección del cliente");
            String mail = JOptionPane.showInputDialog("Ingrese el mail del cliente");

            return guardarCliente(dni, nom, dir, mail);
        } else {
            return cli.get(cli.size() - 1);
        }

    }

    private void mostrarTabla(List<Orden> ordenes) {

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
            tablaHTML.append("<td>").append(orden.getTecnico()).append("</td>");
            tablaHTML.append("<td>").append(orden.getFecha()).append("</td>");
            tablaHTML.append("<td>").append(orden.getCategoria()).append("</td>");
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
