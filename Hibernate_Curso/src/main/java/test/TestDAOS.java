package test;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import dao.AlumnoDAO;
import dao.AsignacionDAO;
import dao.ContactoDAO;
import dao.CursoDAO;
import dao.DomicilioDAO;
import entidades.Alumno;
import entidades.Contacto;
import entidades.Domicilio;

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
			for (int i = 0; i < n; i++) {
				System.out.println(menu[i]);
			}

			System.out.print("Elije una opcion: ");

			try {
				opc = s.nextInt();
			} catch (InputMismatchException e) {
				System.out.println("Ingresa una opcion valida!");

			}

			switch (opc) {
			case 1:
				System.out.println("--Alumnos:");
				imprimir(alumnoDao.listar());
				break;
			case 2:
				System.out.println("Contacto:");
				imprimir(contactoDao.listar());
				break;
			case 3:
				System.out.println("Domicilios:");
				imprimir(domicilioDao.listar());
				break;
			case 4:
				System.out.println("Cursos:");
				imprimir(cursoDao.listar());
				break;
			case 5:
				System.out.println("Asignaciones:");
				imprimir(asignacionDao.listar());
				break;
			case 6:
				System.out.println("Agregando alumno:");
				insertarA(alumnoDao, domicilioDao, contactoDao);
				break;
			case 7:
				break;
			case 8:
				break;
			case 9:
				bandera = true;
				s.close();
				System.out.println("Saliendo...");
				break;
			default:
				System.out.println("Elije una opcion valida!");
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

	private static void insertarA(AlumnoDAO alumno, DomicilioDAO domicilio, ContactoDAO contacto) {
		Alumno a = new Alumno();
		Domicilio d = new Domicilio();
		Contacto c = new Contacto();
		try (Scanner s = new Scanner(System.in)) {
			System.out.print("Ingresa el Nombre: ");
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
		}

		// insertando
		domicilio.insertar(d);
		contacto.insertar(c);
		// datos insertados en bd (ya tiene un id )

		// agrendo la direccion y contacto al alumno
		a.setDomicilio(d);
		a.setContacto(c);

		// insertando
		alumno.insertar(a);

		System.out.println(a);
	}
}
