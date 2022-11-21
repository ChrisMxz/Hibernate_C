package com.david.hibernate.beans;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;

import org.primefaces.PrimeFaces;

import com.david.hibernate.entidades.Asignacion;
import com.david.hibernate.servicios.ServicioAsignaciones;

@ManagedBean
@ViewScoped
public class AsignacionBean implements Serializable {
	// variables
	private static final long serialVersionUID = 1L;

	private Asignacion asignacion;

	@ManagedProperty(value = "#{crudAsignacion}")
	private ServicioAsignaciones servicioAsignaciones;

	// Metodos
	@PostConstruct
	public void inicia() {
		nuevo();
	}

	public void nuevo() {
		asignacion = new Asignacion();
	}

	public void guardar() {
		System.out.println("Guardar: " + asignacion);
		String msg = "Asignado";

		servicioAsignaciones.guardar(asignacion);

		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(msg));
		PrimeFaces.current().ajax().update(":formulario-asignacion:msg");
		PrimeFaces.current().executeScript("PF('dialogoAsignacion').hide()");

	}

	// getters and setters

	public Asignacion getAsignacion() {
		return asignacion;
	}

	public void setAsignacion(Asignacion asignacion) {
		this.asignacion = asignacion;
	}

	public ServicioAsignaciones getServicioAsignaciones() {
		return servicioAsignaciones;
	}

	public void setServicioAsignaciones(ServicioAsignaciones servicioAsignaciones) {
		this.servicioAsignaciones = servicioAsignaciones;
	}

}
