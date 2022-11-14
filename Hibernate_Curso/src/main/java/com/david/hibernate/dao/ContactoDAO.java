package com.david.hibernate.dao;

import java.util.List;

import com.david.hibernate.entidades.Contacto;


public class ContactoDAO extends GenericDAO {

    public List<Contacto> listar() {
        String consulta = "SELECT c FROM Contacto c";
        em = getEntityManager();
        return em.createQuery(consulta, Contacto.class).getResultList();

    }

    public void insertar(Contacto contacto) {
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(contacto);
            em.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace(System.out);
        } 
    }

    public void actualizar(Contacto contacto) {
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.merge(contacto);
            em.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace(System.out);
        } 
    }

    public void eliminar(Contacto contacto) {
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.remove(em.merge(contacto));
            em.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace(System.out);
        } 
    }
    
    public Object buscarPorId(Contacto contacto){
        em = getEntityManager();
        return em.find(Contacto.class, contacto.getIdContacto());
    }

}
