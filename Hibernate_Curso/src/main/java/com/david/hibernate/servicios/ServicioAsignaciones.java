package com.david.hibernate.servicios;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import com.david.hibernate.dao.AsignacionDAO;
import com.david.hibernate.entidades.Asignacion;

@ManagedBean(name = "crudAsignacion")
@RequestScoped
public class ServicioAsignaciones {
	private AsignacionDAO asignacionDao = new AsignacionDAO();

	public List<Asignacion> listar() {
		return asignacionDao.listar();
	}

	public void guardar(Asignacion asignacion) {
		if (asignacion != null && asignacion.getIdAsignacion() == null) {
			asignacionDao.insertar(asignacion);
		} else {
			asignacionDao.actualizar(asignacion);
		}
	}

	public void eliminar(Asignacion asignacion) {
		asignacionDao.eliminar(asignacion);
	}

	public Asignacion buscar(Asignacion asignacion) {
		return (Asignacion) asignacionDao.buscarPorId(asignacion);
	}

}
