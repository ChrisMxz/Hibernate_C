package com.david.hibernate.convertidores;

import java.util.Optional;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import com.david.hibernate.entidades.Alumno;
import com.david.hibernate.servicios.ServicioAlumno;

@RequestScoped
@ManagedBean(name = "alumnoConverter")
public class AlumnoConverter implements Converter {
	@ManagedProperty(value = "#{crudAlumno}")
	private ServicioAlumno servicio;

	public Alumno getAsObject(FacesContext context, UIComponent component, String id) {
		if (id == null) {
			return null;
		}
		Optional<Alumno> alumnoOptional = Optional.empty();
		try {
			alumnoOptional = Optional.ofNullable(servicio.buscar(Integer.parseInt(id)));
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (alumnoOptional.isPresent()) {
			return alumnoOptional.get();
		}
		return null;

	}

	public String getAsString(FacesContext arg0, UIComponent arg1, Object alumno) {
		if (alumno == null) {
			return "0";
		}
		return (((Alumno) alumno)).getIdAlumno().toString();
	}

	public ServicioAlumno getServicio() {
		return servicio;
	}

	public void setServicio(ServicioAlumno servicio) {
		this.servicio = servicio;
	}

	

}
