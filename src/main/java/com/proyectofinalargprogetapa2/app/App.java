package com.proyectofinalargprogetapa2.app;

import com.proyectofinalargprogetapa2.HibernateUtil;
import com.proyectofinalargprogetapa2.model.Cliente;
import com.proyectofinalargprogetapa2.model.Orden;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import org.hibernate.Session;

public class App {
    
    private static Session session;
    
    public static void main(String[] args) {
        App es = new App();
        
        es.crearSesion();
        
        Cliente cliente;
        
        cliente = es.guardarCliente("Esteban");
        
        es.guardarOrden("casa", 10.50f, new Date(), Boolean.FALSE, cliente, 1, 1);
        //JOptionPane.showMessageDialog(null, "Mensaje");
        List<Orden> ordenes = es.listaDeOrdenes();
        
        JOptionPane.showMessageDialog(null, "Cantidad de ordenes "+ordenes.size());
        
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
    
    private Cliente guardarCliente(String nombre) {
        Cliente cli = new Cliente();
        cli.setNombre(nombre);
        session.save(cli);
        return cli;
    }
    
    private void guardarOrden(String descripcion, Float costo, Date fecha, Boolean estado, Cliente cliente, Integer categoria, Integer tecnico) {
        Orden orden = new Orden(descripcion, costo, fecha, estado, cliente, categoria, tecnico);
        session.save(orden);
    }
    
    private List<Orden> listaDeOrdenes() {
        List<Orden> ordenes = session.createQuery("SELECT o FROM Orden o", Orden.class).getResultList();
        return ordenes;
    }
}
