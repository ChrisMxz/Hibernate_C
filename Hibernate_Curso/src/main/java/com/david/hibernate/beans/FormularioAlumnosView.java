package com.david.hibernate.beans;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;

import org.primefaces.PrimeFaces;

import com.david.hibernate.dao.AlumnoDAO;
import com.david.hibernate.entidades.Alumno;

@ManagedBean(name = "formularioAlumno")
@ViewScoped
public class FormularioAlumnosView implements Serializable {
	private static final long serialVersionUID = 1L;

	// variables
	@ManagedProperty(value = "#{Alumno}")
	private Alumno alumno;
	@ManagedProperty(value = "#{crudAlumno}")
	private AlumnoDAO servicioAlumno;

	public void insertar() {
		System.out.println("--Alumno guardado");
		String msg = "Guaradado";
		if (this.alumno.getIdAlumno() == null) {
			servicioAlumno.insertar(this.alumno);
		} else {
			servicioAlumno.actualizar(this.alumno);
			msg = "Actualizado";
		}
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(msg));
		PrimeFaces.current().executeScript("PF('dialogForm').hide()");
	}

	public void eliminar() {
		System.out.println("--Alumno Eliminado");
		servicioAlumno.eliminar(this.alumno);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Eliminado"));
		PrimeFaces.current().executeScript("PF('dialogForm').hide()");

	}

	// getters and setters
	public Alumno getAlumno() {
		return alumno;
	}

	public void setAlumno(Alumno alumno) {
		this.alumno = alumno;
	}

	public AlumnoDAO getServicioAlumno() {
		return servicioAlumno;
	}

	public void setServicioAlumno(AlumnoDAO servicioAlumno) {
		this.servicioAlumno = servicioAlumno;
	}

}
