package com.proyectofinalargprogetapa2.app;

import com.proyectofinalargprogetapa2.HibernateUtil;
import com.proyectofinalargprogetapa2.model.Cliente;
import org.hibernate.Session;

public class App {
    
    private static Session session;
    
    public static void main(String[] args) {
        App es = new App();
        
        es.crearSesion();
        
        es.guardarCliente(2,"Mati");
        
        es.cerrarSesion();
        
    }
    
    public void crearSesion() {
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
    }
    
    public void cerrarSesion() {
        HibernateUtil.getSessionFactory().close();
    }
    
    private void guardarCliente(Integer id, String nombre) {
        Cliente cli = new Cliente();
        cli.setId(id);
        cli.setNombre(nombre);
        session.save(cli);
        session.getTransaction().commit();
    }
}
