package presentacion;

import service.CursosService;

public class Test {

	public static void main(String[] args) {
		CursosService cursosService = new CursosService();
		//Alumnos de Java:
		cursosService.alumnos("Java")
		.forEach(a -> System.out.println(a.getNombre()));
		
		//duración media:
			System.out.println(cursosService.duracionMedia());
		
		//media edad alumnos
			System.out.println(cursosService.edadMedia());
	}

}
