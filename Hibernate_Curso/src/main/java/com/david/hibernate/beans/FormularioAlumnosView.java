package com.david.hibernate.beans;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.primefaces.PrimeFaces;

import com.david.hibernate.entidades.Alumno;
import com.david.hibernate.servicios.ServicioAlumno;

@ManagedBean(name = "formularioAlumno")
@RequestScoped
public class FormularioAlumnosView implements Serializable {
	private static final long serialVersionUID = 1L;

	// variables
	@ManagedProperty(value = "#{Alumno}")
	private Alumno alumno;
	@ManagedProperty(value = "#{crudAlumno}")
	private ServicioAlumno servicioAlumno;
	
	@PostConstruct
	public void inicia() {
		System.out.println("--Inicia Formulario Alumno");
	}
	

	@PreDestroy
	public void termina() {
		System.out.println("--Termina Formulario Alumno");
		System.out.println(this.alumno);
		alumno = null;
		servicioAlumno = null;
	}

	public void insertar() {
		String msg = "Guaradado";
		servicioAlumno.guardar(this.alumno);

		if (this.alumno.getIdAlumno() != null)
			msg = "Actualizado";
		
		System.out.println("--Alumno: "+msg);
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

	public ServicioAlumno getServicioAlumno() {
		return servicioAlumno;
	}

	public void setServicioAlumno(ServicioAlumno servicioAlumno) {
		this.servicioAlumno = servicioAlumno;
	}

}
