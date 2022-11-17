package com.david.hibernate.beans;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;

import org.primefaces.PrimeFaces;

import com.david.hibernate.entidades.Alumno;
import com.david.hibernate.servicios.ServicioAlumno;

@ManagedBean(name = "tablaAlumnos")
@ViewScoped
public class TablaAlumnosView implements Serializable {

	private static final long serialVersionUID = 1L;
	private List<Alumno> alumnos;
	@ManagedProperty(value = "#{Alumno}")
	private Alumno alumnoSelec;
	@ManagedProperty(value = "#{crudAlumno}")
	private ServicioAlumno servicioAlumnos;

	@PostConstruct
	public void inicia() {
		listar();
	}

	@PreDestroy
	public void termina() {
		alumnos = null;
		alumnoSelec = null;
		servicioAlumnos = null;
	}

	public void btnEliminar(ActionEvent event) {
		alumnoSelec = (Alumno) event.getComponent().getAttributes().get("alumno");
		servicioAlumnos.eliminar(alumnoSelec);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Eliminado"));
		PrimeFaces.current().ajax().update(":alumnos:messages");
		listar();
	}

	public void listar() {
		alumnos = servicioAlumnos.listar();
		PrimeFaces.current().ajax().update(":alumnos:dt-alumnos");
	}

	public void refrescar() {
		listar();
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Refrescado"));
		PrimeFaces.current().ajax().update(":alumnos:messages");
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

	public Alumno getAlumnoSelec() {
		return alumnoSelec;
	}

	public void setAlumnoSelec(Alumno alumnoSelec) {
		this.alumnoSelec = alumnoSelec;
	}
	
	


}
