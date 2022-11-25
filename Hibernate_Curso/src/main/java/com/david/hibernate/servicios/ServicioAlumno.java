package com.david.hibernate.servicios;

import java.io.Serializable;
import java.util.List;

import com.david.hibernate.dao.AlumnoDAO;
import com.david.hibernate.entidades.Alumno;

public class ServicioAlumno implements Serializable {

	private static final long serialVersionUID = 1L;
	private AlumnoDAO alumnoDao = new AlumnoDAO();

	public List<Alumno> listar() {
		return alumnoDao.listar();
	}

	public void guardar(Alumno alumno) {
		if (alumno != null && alumno.getIdAlumno() == null) {
			alumnoDao.insertar(alumno);
		} else {
			alumnoDao.actualizar(alumno);
		}
	}

	public void eliminar(Alumno alumno) {
		alumnoDao.eliminar(alumno);
	}

	public Alumno buscar(Integer id) {
		return (Alumno) alumnoDao.buscarPorId(id);
	}
}
