package com.david.hibernate.beans;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;

import com.david.hibernate.dao.AlumnoDAO;
import com.david.hibernate.entidades.Alumno;

@ManagedBean(name = "tablaAlumnos")
@ViewScoped
public class TablaAlumnosView implements Serializable {

	private static final long serialVersionUID = 1L;
	private List<Alumno> alumnos;
	private AlumnoDAO servicioAlumnos = new AlumnoDAO();

	@PostConstruct
	public void listar() {
		alumnos = servicioAlumnos.listar();
	}
	
	

//getters and setters	
	public List<Alumno> getAlumnos() {
		return alumnos;
	}

	public void setAlumnos(List<Alumno> alumnos) {
		this.alumnos = alumnos;
	}

}
