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

import com.david.hibernate.entidades.Asignacion;
import com.david.hibernate.entidades.Curso;
import com.david.hibernate.servicios.ServicioAsignaciones;
import com.david.hibernate.servicios.ServicioCurso;

@ManagedBean
@ViewScoped
public class CursoBean implements Serializable {

	private static final long serialVersionUID = 1L;
	// Variables
	private Curso curso;
	private Asignacion asignacion;
	private List<Curso> listaCursos;

	@ManagedProperty(value = "#{crudCurso}")
	private ServicioCurso servicioCurso;

	@ManagedProperty(value = "#{crudAsignacion}")
	private ServicioAsignaciones servicioAsignaciones;

	@PostConstruct
	public void inicia() {
		nuevo();
		listar();
	}

	public void nuevo() {
		curso = new Curso();
		asignacion = new Asignacion();
	}

	public void listar() {
		listaCursos = servicioCurso.listar();
		// PrimeFaces.current().ajax().update(":cursos:dt-cursos");
	}

	public void refrescar() {
		listar();
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Refrescado"));
		PrimeFaces.current().ajax().update(":cursos:messages");
	}
	
	public void refrescarAsignaciones() {
		listar();
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Refrescado"));
		PrimeFaces.current().ajax().update(":asignaciones:messages");
	}

	public void guardar() {
		System.out.println("Guardar: " + curso);
		String msg = "Guardado";

		if (curso.getIdCurso() != null)
			msg = "Actualizado";

		servicioCurso.guardar(curso);

		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(msg));
		PrimeFaces.current().ajax().update(":formulario-cursos:msg", "cursos");
		PrimeFaces.current().executeScript("PF('dialogForm').hide()");
		listar();
	}

	public void eliminar() {
		servicioCurso.eliminar(curso);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Eliminado"));
		PrimeFaces.current().ajax().update(":cursos:messages");
		listar();
	}

	// asignaciones

	public void guardarAsignacion() {
		System.out.println("Guardar: " + asignacion);
		String msg = "Asignado";

		servicioAsignaciones.guardar(asignacion);

		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(msg));
		PrimeFaces.current().ajax().update(":formulario-asignacion:msg");
		PrimeFaces.current().executeScript("PF('dialogoAsignacion').hide()");

	}

	public void eliminarAsignacion() {
		System.out.println("Elimina: " + asignacion);
		servicioAsignaciones.eliminar(asignacion);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Eliminado"));
		PrimeFaces.current().ajax().update(":asignaciones:messages");
		// listar();
	}

	// Getters an setters
	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	public List<Curso> getListaCursos() {
		return listaCursos;
	}

	public void setListaCursos(List<Curso> listaCursos) {
		this.listaCursos = listaCursos;
	}

	public ServicioCurso getServicioCurso() {
		return servicioCurso;
	}

	public void setServicioCurso(ServicioCurso servicioCurso) {
		this.servicioCurso = servicioCurso;
	}

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
