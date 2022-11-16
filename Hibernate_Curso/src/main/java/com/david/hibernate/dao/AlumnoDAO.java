package com.david.hibernate.dao;

import java.util.List;

import com.david.hibernate.entidades.Alumno;

public class AlumnoDAO extends GenericDAO {

	public List<Alumno> listar() {
		String consulta = "SELECT a FROM Alumno a";
		em = getEntityManager();
		return em.createQuery(consulta, Alumno.class).getResultList();

	}

	public void insertar(Alumno alumno) {
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			em.persist(alumno);
			em.getTransaction().commit();
		} catch (Exception ex) {
			ex.printStackTrace(System.out);
		}

		/*
		 * finally { if (em != null) { em.close(); } }
		 */
	}

	public void actualizar(Alumno alumno) {
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			em.merge(alumno);
			em.getTransaction().commit();
		} catch (Exception ex) {
			ex.printStackTrace(System.out);
		} /*
			 * finally { if (em != null) { em.close(); } }
			 */
	}

	public void eliminar(Alumno alumno) {
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			em.remove(em.merge(alumno));
			em.getTransaction().commit();
		} catch (Exception ex) {
			ex.printStackTrace(System.out);
		} /*
			 * finally { if (em != null) { em.close(); } }
			 */
	}

	public Object buscarPorId(Alumno alumno) {
		em = getEntityManager();
		return em.find(Alumno.class, alumno.getIdAlumno());
	}

}