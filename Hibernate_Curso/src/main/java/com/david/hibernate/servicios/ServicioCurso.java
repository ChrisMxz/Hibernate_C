package com.david.hibernate.servicios;

import java.util.List;

import com.david.hibernate.dao.CursoDAO;
import com.david.hibernate.entidades.Curso;

public class ServicioCurso {
	private CursoDAO cursoDao = new CursoDAO();

	public List<Curso> listar() {
		return cursoDao.listar();
	}
	
	public List<Curso> listarPor(String x, int y) {
		return cursoDao.listarPor(x, y);
	}

	public void guardar(Curso curso) {
		if (curso != null && curso.getIdCurso() == null) {
			cursoDao.insertar(curso);
		} else {
			cursoDao.actualizar(curso);
		}
	}

	public void eliminar(Curso curso) {
		cursoDao.eliminar(curso);
	}

	public Curso buscar(Integer id) {
		return (Curso) cursoDao.buscarPorId(id);
	}
}
