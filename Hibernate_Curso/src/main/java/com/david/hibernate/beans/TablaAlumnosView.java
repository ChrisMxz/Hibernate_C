package com.david.hibernate.beans;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.view.ViewScoped;

import org.primefaces.PrimeFaces;

import com.david.hibernate.entidades.Alumno;
import com.david.hibernate.servicios.ServicioAlumno;

@ManagedBean(name = "tablaAlumnos")
@ViewScoped
public class TablaAlumnosView implements Serializable {

	private static final long serialVersionUID = 1L;
	private List<Alumno> alumnos;
	@ManagedProperty(value = "#{crudAlumno}")
	private ServicioAlumno servicioAlumnos;

	@PostConstruct
	public void listar() {
		this.alumnos = servicioAlumnos.listar();
		PrimeFaces.current().ajax().update(":alumnos:dt-alumnos");
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

}
