package com.david.hibernate.dao;

import java.util.List;

import com.david.hibernate.entidades.Asignacion;

public class AsignacionDAO extends GenericDAO {

	public List<Asignacion> listar() {
		String consulta = "SELECT a FROM Asignacion a";
		em = getEntityManager();
		return em.createQuery(consulta, Asignacion.class).getResultList();

	}

	public void insertar(Asignacion asignacion) {
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			em.persist(asignacion);
			em.getTransaction().commit();
		} catch (Exception ex) {
			ex.printStackTrace(System.out);
		} 
	}

	public void actualizar(Asignacion asignacion) {
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			em.merge(asignacion);
			em.getTransaction().commit();
		} catch (Exception ex) {
			ex.printStackTrace(System.out);
		} 
	}

	public void eliminar(Asignacion asignacion) {
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			em.remove(em.merge(asignacion));
			em.getTransaction().commit();
		} catch (Exception ex) {
			ex.printStackTrace(System.out);
		} 
	}

	public Object buscarPorId(Asignacion asignacion) {
		em = getEntityManager();
		return em.find(Asignacion.class, asignacion.getIdAsignacion());
	}

}