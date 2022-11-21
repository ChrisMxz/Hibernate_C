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

import com.david.hibernate.entidades.Curso;
import com.david.hibernate.servicios.ServicioCurso;

@ManagedBean
@ViewScoped
public class CursoBean implements Serializable {

	private static final long serialVersionUID = 1L;
	// Variables
	@ManagedProperty(name = "curso", value = "#{null}")
	private Curso curso;
	private List<Curso> listaCursos;

	@ManagedProperty(value = "#{crudCurso}")
	private ServicioCurso servicioCurso;

	@PostConstruct
	public void inicia() {
		nuevo();
		listar();
	}

	public void nuevo() {
		curso = new Curso();
	}

	public void listar() {
		listaCursos = servicioCurso.listar();
		//PrimeFaces.current().ajax().update(":cursos:dt-cursos");
	}

	public void refrescar() {
		listar();
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Refrescado"));
		PrimeFaces.current().ajax().update(":cursos:messages");
	}

	public void guardar() {
		System.out.println("Guardar: "+curso);
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

}
