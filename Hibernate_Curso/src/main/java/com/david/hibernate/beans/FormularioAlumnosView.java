package com.david.hibernate.beans;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;

import org.primefaces.PrimeFaces;

import com.david.hibernate.entidades.Alumno;
import com.david.hibernate.servicios.ServicioAlumno;

@ManagedBean(name = "formularioAlumno")
@ViewScoped
public class FormularioAlumnosView implements Serializable {
	private static final long serialVersionUID = 1L;

	// variables
	@ManagedProperty(value = "#{Alumno}")
	private Alumno alumno;
	@ManagedProperty(value = "#{crudAlumno}")
	private ServicioAlumno servicioAlumno;

	@PostConstruct
	public void inicia() {

	}

	@PreDestroy
	public void termina() {
		alumno = null;
		servicioAlumno = null;
	}

	public void insertar() {
		String msg = "Guardado";
		servicioAlumno.guardar(this.alumno);

		if (this.alumno.getIdAlumno() != null)
			msg = "Actualizado";

		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(msg));
		PrimeFaces.current().ajax().update(":formulario-alumnos:msg");
		PrimeFaces.current().executeScript("PF('dialogForm').hide()");
	}

	// getters and setters
	public Alumno getAlumno() {
		return alumno;
	}

	public void setAlumno(Alumno alumno) {
		this.alumno = alumno;
	}

	public ServicioAlumno getServicioAlumno() {
		return servicioAlumno;
	}

	public void setServicioAlumno(ServicioAlumno servicioAlumno) {
		this.servicioAlumno = servicioAlumno;
	}
}
