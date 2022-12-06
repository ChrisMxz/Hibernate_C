package com.david.hibernate.beans;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.PrimeFaces;

import com.david.hibernate.entidades.Alumno;
import com.david.hibernate.servicios.ServicioAlumno;

@ManagedBean
@ViewScoped
public class AlumnoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private int filtro;
	private String textoBuscar;
	private Alumno alumno;
	private ServicioAlumno servicioAlumno;

	private List<Alumno> listaAlumnos;

	@PostConstruct
	public void inicia() {
		servicioAlumno = new ServicioAlumno();
		filtro = 1;
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
		textoBuscar=null;
		PrimeFaces.current().ajax().update(":alumnos");
	}

	public void guardar() {
		String msg = "Guardado";
		if (alumno.getIdAlumno() != null)
			msg = "Actualizado";

		servicioAlumno.guardar(alumno);

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

	public void buscar() {
		listaAlumnos = servicioAlumno.listarPor(textoBuscar, filtro);
	}

	// Getters an setters

	public Alumno getAlumno() {
		return alumno;
	}

	public void setAlumno(Alumno alumno) {
		this.alumno = alumno;
	}

	public List<Alumno> getListaAlumnos() {
		return listaAlumnos;
	}

	public void setListaAlumnos(List<Alumno> listaAlumnos) {
		this.listaAlumnos = listaAlumnos;
	}

	public int getFiltro() {
		return filtro;
	}

	public void setFiltro(int filtro) {
		this.filtro = filtro;
	}

	public String getTextoBuscar() {
		return textoBuscar;
	}

	public void setTextoBuscar(String textoBuscar) {
		this.textoBuscar = textoBuscar;
	}

}
