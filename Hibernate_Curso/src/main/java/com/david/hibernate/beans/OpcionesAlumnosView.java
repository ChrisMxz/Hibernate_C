package com.david.hibernate.beans;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;

@ManagedBean(name = "opcAlumnos")
@ViewScoped
public class OpcionesAlumnosView implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	
	public void agregarAlumno() {
		System.out.println("Nuevo alumno - presionado opc");
	}
	

}
