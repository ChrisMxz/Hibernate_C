package com.david.hibernate.test;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.david.hibernate.dao.AlumnoDAO;
import com.david.hibernate.dao.AsignacionDAO;
import com.david.hibernate.dao.ContactoDAO;
import com.david.hibernate.dao.CursoDAO;
import com.david.hibernate.dao.DomicilioDAO;
import com.david.hibernate.entidades.Alumno;
import com.david.hibernate.entidades.Contacto;
import com.david.hibernate.entidades.Domicilio;

public class TestDAOS {
	public static void main(String[] args) {

		AlumnoDAO alumnoDao = new AlumnoDAO();
		DomicilioDAO domicilioDao = new DomicilioDAO();
		ContactoDAO contactoDao = new ContactoDAO();
		AsignacionDAO asignacionDao = new AsignacionDAO();
		CursoDAO cursoDao = new CursoDAO();

		int n = 9; // numero de opciones
		int opc = 0;
		boolean bandera = false;
		String menu[] = new String[n];
		Scanner s = new Scanner(System.in);

		menu[0] = "1) Listar alumnos";
		menu[1] = "2) Listar contactos";
		menu[2] = "3) Listar domicilios";
		menu[3] = "4) Listar cursos";
		menu[4] = "5) Listar asignaciones";
		menu[5] = "6) Agregar Alumno";
		menu[6] = "7) Editar Alumno";
		menu[7] = "8) Eliminar Alumno";
		menu[8] = "9) Salir";

		while (!bandera) {
			// imprime menu
			System.out.print("\n--Test Hibernate SGA--\n Menu\n");

			for (int i = 0; i < n; i++) {
				System.out.println(menu[i]);
			}

			System.out.print("\nElije una opcion: ");

			try {
				opc = s.nextInt();
				s.nextLine();
			} catch (InputMismatchException e) {

			}

			switch (opc) {
			case 1:
				System.out.println("--Alumnos--");
				imprimir(alumnoDao.listar());
				break;
			case 2:
				System.out.println("--Contactos--");
				imprimir(contactoDao.listar());
				break;
			case 3:
				System.out.println("--Domicilios--");
				imprimir(domicilioDao.listar());
				break;
			case 4:
				System.out.println("--Cursos--");
				imprimir(cursoDao.listar());
				break;
			case 5:
				System.out.println("--Asignaciones--");
				imprimir(asignacionDao.listar());
				break;
			case 6:
				System.out.println("--Agregando alumno--");
				insertarA(alumnoDao, domicilioDao, contactoDao, s);
				break;
			case 7:
				System.out.println("--Editando alumno--");
				editarA(alumnoDao, s);
				break;
			case 8:
				System.out.println("--Eliminar alumno--");
				eliminarrA(alumnoDao, s);
				break;
			case 9:
				bandera = true;
				s.close();
				System.out.println("Saliendo...");
				break;
			default:
				System.out.println("\n!Elije una opcion valida!\n");
				break;
			}

		}

		System.out.println("Listo");

	}

	private static void imprimir(List<?> coleccion) {
		for (Object o : coleccion) {
			System.out.println("valor = " + o);
		}
	}

	private static void insertarA(AlumnoDAO alumno, DomicilioDAO domicilio, ContactoDAO contacto, Scanner s) {
		Alumno a = new Alumno();
		Domicilio d = new Domicilio();
		Contacto c = new Contacto();

		System.out.print("\nIngresa el Nombre: ");
		a.setNombre(s.nextLine());
		System.out.print("Ingresa el Apellido: ");
		a.setApellido(s.nextLine());
		System.out.print("\n--Domicilio-----");
		System.out.print("Ingresa Pais: ");
		d.setPais(s.nextLine());
		System.out.print("Ingresa tu calle: ");
		d.setCalle(s.nextLine());
		System.out.print("Ingresa no Casa: ");
		d.setNoCalle(s.nextLine());
		System.out.print("\n--Contacto-----");
		System.out.print("Ingresa Telefono: ");
		c.setTelefono(s.nextLine());
		System.out.print("Ingresa un correo: ");
		c.setEmail(s.nextLine());

		// insertando
		domicilio.insertar(d);
		contacto.insertar(c);
		// datos insertados en bd (ya tiene un id )

		// agrendo la direccion y contacto al alumno
		a.setDomicilio(d);
		a.setContacto(c);

		// insertando
		alumno.insertar(a);

		System.out.println("\nInsertado:\n" + a);
	}

	private static void editarA(AlumnoDAO alumno, Scanner s) {
		Alumno a = null;
		Integer id;
		System.out.print("Ingresa el id del alumno: ");
		try {
			id = s.nextInt();
			s.nextLine();
			a=new Alumno();
			a.setIdAlumno(id);
			a = (Alumno) alumno.buscarPorId(a);

			if (a != null) {

				System.out.println(a);

				System.out.println("\n----Nuevos datos ----");

				System.out.print("Ingresa el Nombre: ");
				a.setNombre(s.nextLine());
				System.out.print("Ingresa el Apellido: ");
				a.setApellido(s.nextLine());
				System.out.println("\n--Domicilio-----");
				System.out.print("Ingresa Pais: ");
				a.getDomicilio().setPais(s.nextLine());
				System.out.print("Ingresa tu calle: ");
				a.getDomicilio().setCalle(s.nextLine());
				System.out.print("Ingresa no Casa: ");
				a.getDomicilio().setNoCalle(s.nextLine());
				System.out.println("\n--Contacto-----");
				System.out.print("Ingresa Telefono: ");
				a.getContacto().setTelefono(s.nextLine());
				System.out.print("Ingresa un correo: ");
				a.getContacto().setEmail(s.nextLine());

				alumno.actualizar(a);
				System.out.println("\nActualizado:\n" + a);

			} else {
				System.out.println("\n¡Alumno no encontrado!\n");
				editarA(alumno, s);

			}

		} catch (InputMismatchException e) {
			System.out.println("\nIngresa una id valido!\n");

		}

	}

	private static void eliminarrA(AlumnoDAO alumno, Scanner s) {
		Alumno a = null;
		Integer id;
		System.out.print("Ingresa el id del alumno: ");
		try {
			id = s.nextInt();
			s.nextLine();
			a=new Alumno();
			a.setIdAlumno(id);
			a = (Alumno) alumno.buscarPorId(a);

			if (a != null) {

				System.out.println(a);
				alumno.eliminar(a);
				System.out.println("\nEliminado\n");

			} else {
				System.out.println("\n¡Alumno no encontrado!\n");
				eliminarrA(alumno, s);

			}

		} catch (InputMismatchException e) {
			System.out.println("\nIngresa una id valido!\n");

		}

	}
}
