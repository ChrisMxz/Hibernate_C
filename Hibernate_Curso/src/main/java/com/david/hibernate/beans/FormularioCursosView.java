package com.david.hibernate.beans;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;

import org.primefaces.PrimeFaces;

import com.david.hibernate.entidades.Curso;
import com.david.hibernate.servicios.ServicioCurso;


@ManagedBean(name = "formularioCurso")
@ViewScoped
public class FormularioCursosView implements Serializable {
	private static final long serialVersionUID = 1L;

	// variables
	@ManagedProperty(value = "#{Curso}")
	private Curso curso;
	@ManagedProperty(value = "#{crudCurso}")
	private ServicioCurso servicioCurso;

	@PostConstruct
	public void inicia() {

	}

	@PreDestroy
	public void termina() {
		curso = null;
		servicioCurso = null;
	}

	public void insertar() {
		String msg = "Guardado";
		servicioCurso.guardar(this.curso);

		if (this.curso.getIdCurso() != null)
			msg = "Actualizado";

		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(msg));
		PrimeFaces.current().ajax().update(":formulario-cursos:msg");
		PrimeFaces.current().executeScript("PF('dialogForm').hide()");
	}

	// getters and setters

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	public ServicioCurso getServicioCurso() {
		return servicioCurso;
	}

	public void setServicioCurso(ServicioCurso servicioCurso) {
		this.servicioCurso = servicioCurso;
	}

}
