package com.david.hibernate.beans;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.view.ViewScoped;

import com.david.hibernate.dao.AlumnoDAO;
import com.david.hibernate.entidades.Alumno;

@ManagedBean(name = "tablaAlumnos")
@ViewScoped
public class TablaAlumnosView implements Serializable {

	private static final long serialVersionUID = 1L;
	private List<Alumno> alumnos;
	@ManagedProperty(value = "#{crudAlumno}")
	private AlumnoDAO servicioAlumnos;

	@PostConstruct
	public void listar() {
		this.alumnos = servicioAlumnos.listar();
	}

//getters and setters	
	public List<Alumno> getAlumnos() {
		return alumnos;
	}

	public void setAlumnos(List<Alumno> alumnos) {
		this.alumnos = alumnos;
	}

	public AlumnoDAO getServicioAlumnos() {
		return servicioAlumnos;
	}

	public void setServicioAlumnos(AlumnoDAO servicioAlumnos) {
		this.servicioAlumnos = servicioAlumnos;
	}

}
