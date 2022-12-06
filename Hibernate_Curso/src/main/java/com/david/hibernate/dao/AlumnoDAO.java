package com.david.hibernate.dao;

import java.util.List;

import com.david.hibernate.entidades.Alumno;
import com.david.hibernate.entidades.Asignacion;

public class AlumnoDAO extends GenericDAO {

	public List<Alumno> listar() {
		String consulta = "SELECT a FROM Alumno a";
		em = getEntityManager();
		return em.createQuery(consulta, Alumno.class).getResultList();

	}

	public List<Asignacion> listarAsignaciones(Integer id) {
		String consulta = "SELECT a FROM Asignacion a WHERE a.alumno.idAlumno=:id";
		em = getEntityManager();
		return em.createQuery(consulta, Asignacion.class).setParameter("id", id).getResultList();
	}

	public List<Alumno> listarPor(String texto, int filtro) {
		String consulta = "SELECT a FROM Alumno a ";
		em = getEntityManager();
		List<Alumno> alumnos = null;

		// existe un texto que buscar
		if (texto != null) {
			// Busqueda por id
			if (filtro == 1)
				consulta = consulta + "where a.idAlumno like:id";
			// Busqueda por nombre
			if (filtro == 2)
				consulta = consulta + "where a.nombre like:texto";
			// Busqueda por apellido
			if (filtro == 3)
				consulta = consulta + "where a.apellido like:texto";
			// Busqueda por pais
			if (filtro == 4)
				consulta = consulta + "where a.domicilio.pais like:texto";
			// Busqueda por correo
			if (filtro == 5)
				consulta = consulta + "where a.contacto.email like:texto";

			if (filtro != 1) {
				alumnos = em.createQuery(consulta, Alumno.class).setParameter("texto", "%" + texto + "%")
						.getResultList();
			} else {
				try {
					int id;
					id = Integer.parseInt(texto);
					alumnos = em.createQuery(consulta, Alumno.class).setParameter("id", id).getResultList();
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		} else {
			alumnos = em.createQuery(consulta, Alumno.class).getResultList();
		}

		return alumnos;

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

	public void eliminar(Object obj) {

		try {
			em = getEntityManager();
			em.getTransaction().begin();
			em.remove(em.merge(obj));
			em.getTransaction().commit();
		} catch (Exception ex) {
			ex.printStackTrace(System.out);
		} /*
			 * finally { if (em != null) { em.close(); } }
			 */

	}

	public Object buscarPorId(Integer id) {
		em = getEntityManager();
		return em.find(Alumno.class, id);
	}

}