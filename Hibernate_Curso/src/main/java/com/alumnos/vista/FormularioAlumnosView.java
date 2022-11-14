package com.alumnos.vista;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;

import dao.AlumnoDAO;
import entidades.Alumno;


@ManagedBean(name = "insertaAlumno")
@ViewScoped
public class FormularioAlumnosView implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//variables
	private Alumno alumno;
	private AlumnoDAO servicioAlumno;

	
	@PostConstruct
	public void inicia() {
		System.out.println("--Inicia Formulario Alumno");
		alumno=new Alumno();
		servicioAlumno= new AlumnoDAO();
	}
	
	public void termina() {
		System.out.println("--Termina Formulario Alumno");
		alumno=null;
		servicioAlumno=null;
	}
	
	
	//metodos
	public void insertarAlumno() {
		System.out.println("Alumno: "+alumno);
		servicioAlumno.insertar(alumno);
	}
	
	public void actualizarAlumno() {
		servicioAlumno.actualizar(alumno);
	}
	
	public void eliminarAlumno() {
		servicioAlumno.eliminar(alumno);
	}
	
	
	//getters and setters
	public Alumno getAlumno() {
		return alumno;
	}
	public void setAlumno(Alumno alumno) {
		this.alumno = alumno;
	}
	
}
