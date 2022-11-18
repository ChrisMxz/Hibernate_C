package com.david.hibernate.beans;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.primefaces.PrimeFaces;

import com.david.hibernate.entidades.Curso;

@ManagedBean(name = "formularioCurso")
public class FormularioCursosBean implements Serializable {
	private static final long serialVersionUID = 1L;

	// variables
	private Curso curso;

	@PostConstruct
	public void inicia() {
		curso=new Curso();
	}

	@PreDestroy
	public void termina() {
		curso = null;
	}

	public void insertar() {
		String msg = "Guardado";
		
		if (this.curso.getIdCurso() != null)
			msg = "Actualizado";

		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(msg));
		PrimeFaces.current().ajax().update(":formulario-cursos:msg","cursos");
		PrimeFaces.current().executeScript("PF('dialogForm').hide()");
	}

	// getters and setters

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

}
