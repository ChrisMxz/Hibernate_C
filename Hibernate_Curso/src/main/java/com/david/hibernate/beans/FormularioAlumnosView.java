package com.david.hibernate.beans;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;

import org.primefaces.PrimeFaces;

import com.david.hibernate.entidades.Alumno;
import com.david.hibernate.entidades.Contacto;
import com.david.hibernate.entidades.Domicilio;
import com.david.hibernate.servicios.ServicioAlumno;

@ManagedBean(name = "formularioAlumno")
@ViewScoped
public class FormularioAlumnosView implements Serializable {
	private static final long serialVersionUID = 1L;

	// variables

	private Alumno alumno;
	@ManagedProperty(value = "#{crudAlumno}")
	private ServicioAlumno servicioAlumno;
	
	public void nuevo() {
		this.alumno=new Alumno();
		Domicilio c= new Domicilio();
		Contacto cc= new Contacto();
		this.alumno.setDomicilio(c);
		this.alumno.setContacto(cc);
	}

	public void insertar() {
		System.out.println("--Alumno guardado");
		String msg = "Guaradado";

		servicioAlumno.guardar(this.alumno);

		if (this.alumno.getIdAlumno() != null)
			msg = "Actualizado";

		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(msg));
		PrimeFaces.current().executeScript("PF('dialogForm').hide()");
	}

	public void eliminar() {
		System.out.println("--Alumno Eliminado");
		servicioAlumno.eliminar(this.alumno);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Eliminado"));
		PrimeFaces.current().executeScript("PF('dialogForm').hide()");

	}

	// getters and setters
	public Alumno getAlumno() {
		return alumno;
	}

	public void setAlumno(Alumno alumno) {
		this.alumno = alumno;
	}

	public ServicioAlumno getServicioAlumno() {
		return servicioAlumno;
	}

	public void setServicioAlumno(ServicioAlumno servicioAlumno) {
		this.servicioAlumno = servicioAlumno;
	}

}
