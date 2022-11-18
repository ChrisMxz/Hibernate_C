package com.david.hibernate.controles;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.view.ViewScoped;

import com.david.hibernate.beans.FormularioCursosBean;
import com.david.hibernate.beans.TablaCursosBean;
import com.david.hibernate.entidades.Curso;
import com.david.hibernate.servicios.ServicioCurso;

@ManagedBean(name = "controlCurso")
@ViewScoped
public class CursoControlView implements Serializable {

	private static final long serialVersionUID = 1L;

	@ManagedProperty(value = "#{tablaCursos}")
	private TablaCursosBean tablaCursos;
	@ManagedProperty(value = "#{formularioCurso}")
	private FormularioCursosBean formularioCurso;

	@ManagedProperty(value = "#{crudCurso}")
	private ServicioCurso servicioCurso;

	@PostConstruct
	public void inicia() {
		
	}
	
	public void nuevo() {
		System.out.println("Nuevo curso");
		Curso curso=new Curso();
		formularioCurso.setCurso(curso);
	}

	public void guardar() {
		System.out.println("Agrega: "+formularioCurso.getCurso());
		servicioCurso.guardar(formularioCurso.getCurso());
		formularioCurso.insertar(); //muestra los mensajes en la pantalla
		tablaCursos.listar();
	}

	public void editar() {
		System.out.println("Edita: "+tablaCursos.getCursoSelec());
		formularioCurso.setCurso(tablaCursos.getCursoSelec());
	}

	public void eliminar() {
		System.out.println("Elimina: "+tablaCursos.getCursoSelec());
		servicioCurso.eliminar(tablaCursos.getCursoSelec());
		tablaCursos.eliminar();

	}

	public TablaCursosBean getTablaCursos() {
		return tablaCursos;
	}

	public void setTablaCursos(TablaCursosBean tablaCursos) {
		this.tablaCursos = tablaCursos;
	}

	public FormularioCursosBean getFormularioCurso() {
		return formularioCurso;
	}

	public void setFormularioCurso(FormularioCursosBean formularioCurso) {
		this.formularioCurso = formularioCurso;
	}

	public ServicioCurso getServicioCurso() {
		return servicioCurso;
	}

	public void setServicioCurso(ServicioCurso servicioCurso) {
		this.servicioCurso = servicioCurso;
	}
	
	

}
