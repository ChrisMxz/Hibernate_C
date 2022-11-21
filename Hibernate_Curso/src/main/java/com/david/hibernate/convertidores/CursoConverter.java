package com.david.hibernate.convertidores;

import java.util.Optional;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import com.david.hibernate.entidades.Curso;
import com.david.hibernate.servicios.ServicioCurso;

@RequestScoped
@ManagedBean(name = "cursoConverter")
public class CursoConverter implements Converter {
	@ManagedProperty(value = "#{crudCurso}")
	private ServicioCurso servicio;

	public Curso getAsObject(FacesContext context, UIComponent component, String id) {
		if (id == null) {
			return null;
		}
		Optional<Curso> cursoOptional = Optional.empty();
		try {
			cursoOptional = Optional.ofNullable(servicio.buscar(Integer.parseInt(id)));
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (cursoOptional.isPresent()) {
			return cursoOptional.get();
		}
		return null;

	}

	public String getAsString(FacesContext arg0, UIComponent arg1, Object curso) {
		if (curso == null) {
			return "0";
		}
		return (((Curso) curso)).getIdCurso().toString();
	}

	public ServicioCurso getServicio() {
		return servicio;
	}

	public void setServicio(ServicioCurso servicio) {
		this.servicio = servicio;
	}



	

}
