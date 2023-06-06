package presentacion;

import java.util.Scanner;

import model.Empleado;
import service.EmpleadosService;

public class main {

	public static void main(String[] args) {

		
		Scanner sc=new Scanner(System.in);
		int opcion;
		do {
			mostrarMenu();
			opcion=sc.nextInt();//lee la opción elegida
			switch(opcion) { //evaluamos
				case 1:
					pedirEmpleado();
					break;
				case 2:
					buscarEmpleadoPorDepartamento();
					break;
				case 3:
					buscarEmpleadoPorNombre();
					break;				
				case 4:
					System.out.println("---Adios---");
				default:
					System.out.println("Selecciona la opción correcta");
			}
		}while(opcion!=4);
	}
	
	static void mostrarMenu() {
		System.out.println("1.- Añadir empleado");
		System.out.println("2.- Buscar empleado por departamento");
		System.out.println("3.- Buscar empleado por nombre");
		System.out.println("4.- Salir");
		
	}
	
	static void pedirEmpleado() {
		Scanner sc = new Scanner(System.in);
		String nombre, email, departamento;
		double salario;
		System.out.println("Introduce el nombre del empleado: ");
		nombre = sc.nextLine();
		System.out.println("Introduce el email del empleado: ");
		email = sc.nextLine();
		System.out.println("Introduce el salario del empleado: ");
		salario = Double.parseDouble(sc.nextLine());
		System.out.println("Introduce el departamento del empleado");
		departamento = sc.nextLine();
		Empleado nuevo = new Empleado (nombre, email, salario, departamento);
		EmpleadosService empleadosService = new EmpleadosService();
		empleadosService.nuevoEmpleado(nuevo);
		System.out.println("Contacto añadido");
		
	}
	
	static void buscarEmpleadoPorDepartamento() {
		Scanner sc = new Scanner (System.in);
		String departamento;
		System.out.println("Introduce el departamento a buscar: ");
		departamento = sc.nextLine();
		EmpleadosService empleadosService = new EmpleadosService();
		empleadosService.porDepartamento(departamento);
		System.out.println();
	}
	
	static void buscarEmpleadoPorNombre() {
		Scanner sc = new Scanner (System.in);
		String nombre;
		System.out.println("Introduce el nombre a buscar: ");
		nombre = sc.nextLine();
		EmpleadosService empleadosService = new EmpleadosService();
		System.out.println(empleadosService.buscarEmpleado(nombre));
	}

}
