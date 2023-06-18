package service;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

import model.Alumnos;
import model.Curso;

public class CursosService {
	
	String dir = "D:\\Curso Certificado\\JAVA SE 8\\EJERCICIOS\\Ficheros_datos\\cursos.json";

	private Stream<Curso> streamCursos() {
		Gson gson = new Gson();
		try (FileReader fr = new FileReader(dir)) {
			Curso[] cursos = gson.fromJson(fr, Curso[].class);
			return Stream.of(cursos);
		} catch (IOException ex) {
			ex.printStackTrace();
			return Stream.empty();
		}
	}
	//Recibe precio máximo y devuelve la lista de cursos cuyo precio sea inferior o igual a ese valor 
	//>ordenados de menor a mayor precio.
	
	public List<Curso> cursosPrecioMax(double preciomax) {
		return streamCursos()
				.filter(c -> c.getPrecio() <= preciomax)
//				.sorted((a,b) -> Double.compare(a.getPrecio(), b.getPrecio()))
				.sorted(Comparator.comparingDouble(c -> c.getPrecio()))
				.collect(Collectors.toList());
	}
	


	//a partir del nombre del curso, devuelve la lista de alumnos de dicho curso
	public List<Alumnos> alumnos(String nombre) {
		return streamCursos()
				.filter(c -> c.getCurso().equals(nombre)) //Stream <Curso>
				.findFirst() //Optional<Curso>
				.orElse(new Curso())//Curso
				.getAlumnos(); //List <Alumno	
				
//		return  cursos.filter(c -> c.getCurso().equals(nombre))
//				.filter(c -> c.getAlumnos()
//					.forEach(a -> return a.getNombre()).collect(Collectors.toList());
//				}
				
	}
	
	//duración media de todos los cursos
	public double duracionMedia () {
		return streamCursos()
				.collect(Collectors.averagingDouble(c -> c.getDuracion()));
		
	}
	
	public double edadMedia () {
		return streamCursos()
				.flatMap(c -> c.getAlumnos().stream()) //Stream<Alumnos>
				.collect(Collectors.averagingDouble(a -> a.getEdad()));
		
	}
	
	// lista de nombres de todos los alumnos mayores de edad
	public List<String> mayores () {
		return streamCursos()
				.flatMap(c -> c.getAlumnos().stream()) //Stream <Alumnos>
				.filter(a -> a.getEdad() >= 18) //Stream<Alumnos>
				.map(a -> a.getNombre())
				.collect(Collectors.toList());
	}
}
