package com.david.hibernate.beans;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.PrimeFaces;

import com.david.hibernate.entidades.Curso;
import com.david.hibernate.servicios.ServicioCurso;

@ManagedBean
@ViewScoped
public class CursoBean implements Serializable {

	private static final long serialVersionUID = 1L;
	// Variables
	private int filtro;
	private String textoBuscar;
	private Curso curso;
	private List<Curso> listaCursos;
	private ServicioCurso servicioCurso;

	@ManagedProperty(value = "#{asignacionBean}")
	private AsignacionBean asignacion;

	@PostConstruct
	public void inicia() {
		servicioCurso = new ServicioCurso();
		filtro=1;
		listar();
	}

	public void nuevo() {
		curso = new Curso();
	}

	public void listar() {
		listaCursos = servicioCurso.listar();
	}

	public void refrescar() {
		listar();
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Refrescado", null));
		textoBuscar=null;
		PrimeFaces.current().ajax().update(":cursos");
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
		String msg;
		if (curso.getAsignaciones().size() > 0) {
			msg = "Hay alumnos en este curso";
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "¡No se puede eliminar!", msg));
		} else {
			msg = "Curso " + curso.getNombre();
			servicioCurso.eliminar(curso);
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Eliminado", msg));
			listar();
		}
		PrimeFaces.current().ajax().update(":cursos:messages");
	}

	public void buscar() {
		listaCursos = servicioCurso.listarPor(textoBuscar, filtro);
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

	public AsignacionBean getAsignacion() {
		return asignacion;
	}

	public void setAsignacion(AsignacionBean asignacion) {
		this.asignacion = asignacion;
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
