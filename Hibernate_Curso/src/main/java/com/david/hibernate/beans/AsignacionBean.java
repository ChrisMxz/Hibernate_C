package com.david.hibernate.beans;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.PrimeFaces;

import com.david.hibernate.entidades.Asignacion;
import com.david.hibernate.servicios.ServicioAsignaciones;

@ManagedBean
@ViewScoped
public class AsignacionBean implements Serializable {
	// variables
	private static final long serialVersionUID = 1L;
	private Asignacion asignacion;
	private ServicioAsignaciones servicioAsignaciones;

	// Metodos
	@PostConstruct
	public void inicia() {
		servicioAsignaciones = new ServicioAsignaciones();
	}

	public void nuevo() {
		asignacion = new Asignacion();
	}

	public void guardar() {
		String msg = "Asignado";

		if (asignacion.getIdAsignacion() != null)
			msg = "Actualizado";

		servicioAsignaciones.guardar(asignacion);

		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(msg));
		PrimeFaces.current().ajax().update(":formulario-asignacion:msg");
		PrimeFaces.current().executeScript("PF('dialogoAsignacion').hide()");
	}

	public void eliminar() {
		servicioAsignaciones.eliminar(asignacion);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Eliminado"));
		PrimeFaces.current().ajax().update(":asignaciones:messages", ":asignaciones:dt-asignaciones");
	}

	// getters and setters

	public Asignacion getAsignacion() {
		return asignacion;
	}

	public void setAsignacion(Asignacion asignacion) {
		this.asignacion = asignacion;
	}

}
