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

import com.david.hibernate.entidades.Alumno;
import com.david.hibernate.servicios.ServicioAlumno;

@ManagedBean(name = "tablaAlumnos")
@ViewScoped
public class TablaAlumnosView implements Serializable {

	private static final long serialVersionUID = 1L;
	private List<Alumno> alumnos;
	private List<Alumno> alumnosSeleccionados;
	@ManagedProperty(value = "#{crudAlumno}")
	private ServicioAlumno servicioAlumnos;

	@PostConstruct
	public void inicia() {
		this.alumnosSeleccionados = new ArrayList<Alumno>();
		listar();
	}

	public void listar() {
		this.alumnos = servicioAlumnos.listar();
		PrimeFaces.current().ajax().update(":alumnos:dt-alumnos");
	}

	public void refrescar() {
		listar();
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Refrescado"));
	}

	public String getDeleteButtonMessage() {
		if (hasSelectedAlumnos()) {
			int size = this.alumnosSeleccionados.size();
			return size > 1 ? "Eliminar " + size : "Eliminar 1";
		}

		return "Eliminar";
	}

	public void eliminaAlumnos() {
		
		String msg = " Alumno eliminado";
		int cantidad = 0;
		cantidad = this.alumnosSeleccionados.size();
		
		System.out.println(cantidad);

		for (int i = 0; i < this.alumnosSeleccionados.size(); i++) {
			// this.servicioAlumnos.eliminar(alumnosSeleccionados.get(i));
			System.out.println(alumnosSeleccionados.get(i));
		}
		this.alumnosSeleccionados = null;

		// mensaje
		if (cantidad > 1)
			msg = " Alumnos Eliminados";

		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(cantidad + msg));

		PrimeFaces.current().ajax().update(":alumnos:dt-alumnos",":opciones");
		PrimeFaces.current().executeScript("PF('dtAlumnos').clearFilters()");
		System.out.println("  -Eliminados ");
		
		listar();
	}

	public boolean hasSelectedAlumnos() {
		return this.alumnosSeleccionados != null && !this.alumnosSeleccionados.isEmpty();
	}

//getters and setters	
	public List<Alumno> getAlumnos() {
		return alumnos;
	}

	public void setAlumnos(List<Alumno> alumnos) {
		this.alumnos = alumnos;
	}

	public ServicioAlumno getServicioAlumnos() {
		return servicioAlumnos;
	}

	public void setServicioAlumnos(ServicioAlumno servicioAlumnos) {
		this.servicioAlumnos = servicioAlumnos;
	}

	public List<Alumno> getAlumnosSeleccionados() {
		return alumnosSeleccionados;
	}

	public void setAlumnosSeleccionados(List<Alumno> alumnosSeleccionados) {
		this.alumnosSeleccionados = alumnosSeleccionados;
	}

}
