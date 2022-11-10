package test;

import java.util.List;

import dao.AlumnoDAO;
import dao.AsignacionDAO;
import dao.ContactoDAO;
import dao.CursoDAO;
import dao.DomicilioDAO;

public class TestDAOS {
	public static void main(String[] args) {
		AlumnoDAO alumnoDao = new AlumnoDAO();
		System.out.println("Alumnos:");
		imprimir(alumnoDao.listar());

		System.out.println("Domicilios:");
		DomicilioDAO domicilioDao = new DomicilioDAO();
		imprimir(domicilioDao.listar());

		System.out.println("Contacto:");
		ContactoDAO contactoDao = new ContactoDAO();
		imprimir(contactoDao.listar());

		System.out.println("Cursos:");
		CursoDAO cursoDao = new CursoDAO();
		imprimir(cursoDao.listar());

		System.out.println("Asignaciones:");
		AsignacionDAO asignacionDao = new AsignacionDAO();
		imprimir(asignacionDao.listar());
	}
	
	private static void imprimir(List<?> coleccion) {
		for (Object o : coleccion) {
			System.out.println("valor = " + o);
		}
	}

	

}
