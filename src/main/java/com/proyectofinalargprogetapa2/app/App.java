package com.proyectofinalargprogetapa2.app;

import com.proyectofinalargprogetapa2.HibernateUtil;
import com.proyectofinalargprogetapa2.interfaz.Panels;
import com.proyectofinalargprogetapa2.model.Categoria;
import com.proyectofinalargprogetapa2.model.Cliente;
import com.proyectofinalargprogetapa2.model.Orden;
import com.proyectofinalargprogetapa2.model.Tecnico;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.JOptionPane;
import org.hibernate.Session;

public class App {

    private static Session session;

    public static void main(String[] args) {
        App es = new App();

        es.crearSesion();

        boolean ejecutar = true;
        Panels ventanas = new Panels();

        while (ejecutar) {
            
            int opcion = ventanas.menuOrden();
            
            switch (opcion) {
                    case 1 -> {
                        Cliente cli = es.cargarCliente();
                        String datosOrden[] = ventanas.datosOrden(es.buscarCategias(), es.buscarTecnicos());
                        String des = datosOrden[0];
                        float cos = Float.parseFloat(datosOrden[1]);
                        Categoria cat = es.consultaCategoria(Integer.parseInt(datosOrden[2]));
                        Tecnico tec = es.consultaTecnico(Integer.parseInt(datosOrden[3]));
                        es.guardarOrden(des, cos, LocalDate.now(), Boolean.FALSE, cli, cat, tec);
                    }
                    case 2 -> {
                        List<Orden> ordenes = es.mostrarEntreFechas();
                        ventanas.mostrarTabla(ordenes);
                    }
                    case 3 -> ejecutar = false;
                    default -> JOptionPane.showMessageDialog(null, "Opción incorrecta");
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
    
    private List<Categoria> buscarCategias() {
        List<Categoria> cat = session.createQuery("SELECT o From Categoria o", Categoria.class).getResultList();
        return cat;
    }
    
    private List<Tecnico> buscarTecnicos() {
        List<Tecnico> tec = session.createQuery("SELECT o From Tecnico o", Tecnico.class).getResultList();
        return tec;
    }

    private Categoria consultaCategoria(int id_categoria) {
        Categoria cate = session.find(Categoria.class, id_categoria);
        return cate;
    }

    private Tecnico consultaTecnico(int id_tecnico) {
        Tecnico tec = session.find(Tecnico.class, id_tecnico);
        return tec;
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

    private void guardarOrden(String descripcion, Float costo, LocalDate fecha, Boolean estado, Cliente cliente, Categoria categoria, Tecnico tecnico) {
        Orden orden = new Orden(descripcion, costo, fecha, estado, cliente, categoria, tecnico);
        session.save(orden);
    }

    private List<Orden> listaDeOrdenes() {
        List<Orden> ordenes = session.createQuery("SELECT o FROM Orden o", Orden.class).getResultList();
        return ordenes;
    }

    public Cliente cargarCliente() {
        Panels ventanas = new Panels();
        
        int dni = ventanas.dniCliente();
        
        List<Cliente> cli = consultaCliente(dni);

        if (cli.isEmpty()) {
            
            String datosCliente[] = ventanas.datosCliente();

            return guardarCliente(dni, datosCliente[0], datosCliente[1], datosCliente[2]);
        } else {
            return cli.get(cli.size() - 1);
        }
    }
    public List<Orden> mostrarEntreFechas() {
        List<Orden> ordenes = listaDeOrdenes();
        JOptionPane.showMessageDialog(null, "A continuación ingrese las fechas de consulta de las ordenes, en el formato yyyy-MM-dd");

        LocalDate fechaInicio = LocalDate.parse(JOptionPane.showInputDialog(null, "Ingrese la fecha de inicio"));

        LocalDate fechaFin = LocalDate.parse(JOptionPane.showInputDialog(null, "Ingrese la fecha de finalización"));

        List<Orden> entreFechas = ordenes.stream().filter(orden -> 
                        (orden.getFecha().isAfter(fechaInicio) || orden.getFecha().isEqual(fechaInicio)) && 
                        (orden.getFecha().isBefore(fechaFin) || orden.getFecha().isEqual(fechaFin)))
                .collect(Collectors.toList());

        return entreFechas;
    }
}
