package com.david.hibernate.beans;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import com.david.hibernate.dao.AlumnoDAO;
import com.david.hibernate.entidades.Alumno;

@ManagedBean(name = "insertaAlumno")
@RequestScoped
public class FormularioAlumnosView implements Serializable {
	private static final long serialVersionUID = 1L;

	// variables
	private Alumno alumno;
	private AlumnoDAO servicioAlumno;
	private boolean accionHecha;

	@PostConstruct
	public void inicia() {
		System.out.println("--Inicia Formulario Alumno");
		alumno = new Alumno();

		servicioAlumno = new AlumnoDAO();
	}

	@PreDestroy
	public void termina() {
		System.out.println("--Termina Formulario Alumno");
		alumno = null;
		servicioAlumno = null;
	}

	// metodos
	public void insertarAlumno() {

		System.out.println("Alumno: " + alumno);
		// servicioAlumno.insertar(alumno);

	}

	public void actualizarAlumno() {
		servicioAlumno.actualizar(alumno);
	}

	public void eliminarAlumno() {
		servicioAlumno.eliminar(alumno);
	}

	// getters and setters
	public Alumno getAlumno() {
		return alumno;
	}

	public void setAlumno(Alumno alumno) {
		this.alumno = alumno;
	}

	public boolean isAccionHecha() {
		return accionHecha;
	}

	public void setAccionHecha(boolean accionHecha) {
		this.accionHecha = accionHecha;
	}

}
