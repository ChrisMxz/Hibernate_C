package com.david.hibernate.servicios;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import com.david.hibernate.dao.AlumnoDAO;
import com.david.hibernate.entidades.Alumno;

@ManagedBean(name = "crudAlumno")
@RequestScoped
public class ServicioAlumno {
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

	public Alumno buscar(Alumno alumno) {
		return (Alumno) alumnoDao.buscarPorId(alumno);
	}
}
