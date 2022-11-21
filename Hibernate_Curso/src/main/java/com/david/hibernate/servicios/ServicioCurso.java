package com.david.hibernate.servicios;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import com.david.hibernate.dao.CursoDAO;
import com.david.hibernate.entidades.Curso;

@ManagedBean(name = "crudCurso")
@RequestScoped
public class ServicioCurso {
	private CursoDAO cursoDao = new CursoDAO();

	public List<Curso> listar() {
		return cursoDao.listar();
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
