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

import com.david.hibernate.entidades.Curso;
import com.david.hibernate.servicios.ServicioCurso;

@ManagedBean(name = "tablaCursos")
@ViewScoped
public class TablaCursosView implements Serializable {

	private static final long serialVersionUID = 1L;
	private List<Curso> cursos;
	@ManagedProperty(value = "#{Curso}")
	private Curso cursoSelec;
	@ManagedProperty(value = "#{crudCurso}")
	private ServicioCurso servicioCursos;

	@PostConstruct
	public void inicia() {
		listar();
	}

	@PreDestroy
	public void termina() {
		cursos = null;
		cursoSelec = null;
		servicioCursos = null;
	}

	public void btnEliminar(ActionEvent event) {
		cursoSelec = (Curso) event.getComponent().getAttributes().get("curso");
		servicioCursos.eliminar(cursoSelec);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Eliminado"));
		PrimeFaces.current().ajax().update(":cursos:messages");
		listar();
	}

	public void listar() {
		cursos = servicioCursos.listar();
		PrimeFaces.current().ajax().update(":cursos:dt-cursos");
	}

	public void refrescar() {
		listar();
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Refrescado"));
		PrimeFaces.current().ajax().update(":cursos:messages");
	}

//getters and setters	
	public List<Curso> getCursos() {
		return cursos;
	}

	public void setCursos(List<Curso> cursos) {
		this.cursos = cursos;
	}

	public Curso getCursoSelec() {
		return cursoSelec;
	}

	public void setCursoSelec(Curso cursoSelec) {
		this.cursoSelec = cursoSelec;
	}

	public ServicioCurso getServicioCursos() {
		return servicioCursos;
	}

	public void setServicioCursos(ServicioCurso servicioCursos) {
		this.servicioCursos = servicioCursos;
	}

}
