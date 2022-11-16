package com.david.hibernate.beans;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;

@ManagedBean(name = "opcAlumnos")
@ViewScoped
public class OpcionesAlumnosView implements Serializable {

	private static final long serialVersionUID = 1L;
	@ManagedProperty(value = "#{tablaAlumnos}")
	private TablaAlumnosView tablaAlumnosView;

	public void refrescar() {
		tablaAlumnosView.listar();
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Refrescado"));
	}
	
	
	

	public TablaAlumnosView getTablaAlumnosView() {
		return tablaAlumnosView;
	}

	public void setTablaAlumnosView(TablaAlumnosView tablaAlumnosView) {
		this.tablaAlumnosView = tablaAlumnosView;
	}
	
	
	

}
