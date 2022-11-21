package com.david.hibernate.beans;

import java.io.Serializable;
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

@ManagedBean
@ViewScoped
public class AlumnoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Alumno alumno;
	@ManagedProperty(value = "#{crudAlumno}")
	private ServicioAlumno servicioAlumno;

	private List<Alumno> listaAlumnos;

	@PostConstruct
	public void inicia() {
		nuevo();
		listar();
	}

	public void nuevo() {
		alumno = new Alumno();
	}

	public void listar() {
		listaAlumnos = servicioAlumno.listar();
	}

	public void refrescar() {
		listar();
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Refrescado"));
		PrimeFaces.current().ajax().update(":alumnos:messages");
	}

	public void guardar() {
		String msg = "Guardado";
		servicioAlumno.guardar(this.alumno);

		if (this.alumno.getIdAlumno() != null)
			msg = "Actualizado";

		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(msg));
		PrimeFaces.current().ajax().update(":formulario-alumnos:msg");
		PrimeFaces.current().executeScript("PF('dialogForm').hide()");
		listar();
	}

	public void eliminar() {
		servicioAlumno.eliminar(alumno);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Eliminado"));
		PrimeFaces.current().ajax().update(":alumnos:messages");
		listar();
	}

	// Getters an setters

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

	public List<Alumno> getListaAlumnos() {
		return listaAlumnos;
	}

	public void setListaAlumnos(List<Alumno> listaAlumnos) {
		this.listaAlumnos = listaAlumnos;
	}
	
	

}
