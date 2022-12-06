package com.david.hibernate.dao;

import java.util.List;

import com.david.hibernate.entidades.Asignacion;
import com.david.hibernate.entidades.Curso;

public class CursoDAO extends GenericDAO {

	public List<Curso> listar() {
		String consulta = "SELECT c FROM Curso c";
		em = getEntityManager();
		return em.createQuery(consulta, Curso.class).getResultList();

	}

	public List<Curso> listarPor(String texto, int filtro) {
		String consulta = "SELECT c FROM Curso c ";
		em = getEntityManager();
		List<Curso> cursos = null;

		// existe un texto que buscar
		if (texto != null) {
			// Busqueda por id
			if (filtro == 1)
				consulta = consulta + "where c.idCurso like:id";
			// Busqueda por nombre
			if (filtro == 2)
				consulta = consulta + "where c.nombre like:texto";

			if (filtro != 1) {
				cursos = em.createQuery(consulta, Curso.class).setParameter("texto", "%" + texto + "%").getResultList();
			} else {
				try {
					int id;
					id = Integer.parseInt(texto);
					cursos = em.createQuery(consulta, Curso.class).setParameter("id", id).getResultList();
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		} else {
			cursos = em.createQuery(consulta, Curso.class).getResultList();
		}

		return cursos;

	}

	public List<Asignacion> listarAsignaciones(Integer id) {
		String consulta = "SELECT a FROM Asignacion a WHERE a.curso.idCurso=:id";
		em = getEntityManager();
		return em.createQuery(consulta, Asignacion.class).setParameter("id", id).getResultList();
	}

	public void insertar(Curso curso) {
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			em.persist(curso);
			em.getTransaction().commit();
		} catch (Exception ex) {
			ex.printStackTrace(System.out);
		}

		/*
		 * finally { if (em != null) { em.close(); } }
		 */
	}

	public void actualizar(Curso curso) {
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			em.merge(curso);
			em.getTransaction().commit();
		} catch (Exception ex) {
			ex.printStackTrace(System.out);
		}

		/*
		 * finally { if (em != null) { em.close(); } }
		 */
	}

	public void eliminar(Curso curso) {
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			em.remove(em.merge(curso));
			em.getTransaction().commit();
		} catch (Exception ex) {
			ex.printStackTrace(System.out);
		}

		/*
		 * finally { if (em != null) { em.close(); } }
		 */
	}

	public Object buscarPorId(Integer id) {
		em = getEntityManager();
		return em.find(Curso.class, id);
	}

}