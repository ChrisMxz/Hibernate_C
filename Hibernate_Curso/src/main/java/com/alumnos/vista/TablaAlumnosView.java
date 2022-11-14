package com.alumnos.vista;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;

import dao.AlumnoDAO;
import entidades.Alumno;

@ManagedBean
@ViewScoped
public class TablaAlumnosView implements Serializable {

	private static final long serialVersionUID = 1L;
	private List<Alumno> alumnos;
	private AlumnoDAO servicioAlumnos= new AlumnoDAO();
	
	
	@PostConstruct
	public void listar() {
		alumnos= servicioAlumnos.listar();
	}
	
	public List<Alumno> getAlumnos() {
		return alumnos;
	}


	public void setAlumnos(List<Alumno> alumnos) {
		this.alumnos = alumnos;
	}
	
	

}
