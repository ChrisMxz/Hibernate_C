package com.david.hibernate.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;

import org.primefaces.PrimeFaces;

import com.david.hibernate.entidades.Asignacion;
import com.david.hibernate.entidades.Curso;
import com.david.hibernate.servicios.ServicioAsignaciones;

@ManagedBean
@ViewScoped
public class AsignacionBean implements Serializable {
	// variables
	private static final long serialVersionUID = 1L;
	
	private Curso curso;
	private Asignacion asignacion;
	private List<Asignacion> listaAsignaciones;

	@ManagedProperty(value = "#{crudAsignacion}")
	private ServicioAsignaciones servicioAsignaciones;

	// Metodos
	@PostConstruct
	public void inicia() {
		nuevo();
	}

	public void nuevo() {
		asignacion = new Asignacion();
		listaAsignaciones= new ArrayList<Asignacion>();
	}

	public void listar() {
		System.out.println("Curso recibido: "+curso);
		listaAsignaciones = servicioAsignaciones.listar();

	}

	public void refrescar() {
		listar();
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Refrescado"));
		PrimeFaces.current().ajax().update(":asignaciones:messages");
	}

	public void guardar() {
		System.out.println("Guardar: " + asignacion);
		String msg = "Asignado";

		servicioAsignaciones.guardar(asignacion);

		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(msg));
		PrimeFaces.current().ajax().update(":formulario-asignacion:msg");
		PrimeFaces.current().executeScript("PF('dialogoAsignacion').hide()");

	}

	public void eliminar() {
		System.out.println("Elimina: " + asignacion);
		servicioAsignaciones.eliminar(asignacion);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Eliminado"));
		PrimeFaces.current().ajax().update(":asignaciones:messages");
		//listar();
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

	public List<Asignacion> getListaAsignaciones() {
		return listaAsignaciones;
	}

	public void setListaAsignaciones(List<Asignacion> listaAsignaciones) {
		this.listaAsignaciones = listaAsignaciones;
	}

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}
	
	

}
