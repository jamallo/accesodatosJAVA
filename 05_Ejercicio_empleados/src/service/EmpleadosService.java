package service;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

import model.Empleado;

public class EmpleadosService {
	
	String dir = "D:\\Curso Certificado\\JAVA SE 8\\EJERCICIOS\\Ficheros_datos\\empleados.json";
	Gson gson = new Gson();
	
	private Stream <Empleado> streamEmpleados() {
		try (FileReader fr = new FileReader(dir)) {
			Empleado [] emps = gson.fromJson(fr, Empleado [].class);
			return Stream.of(emps);
		}catch (IOException ex) {
			ex.printStackTrace();
			return Stream.empty();
		}			
	}
	// Nuevo empleado
	
	public void nuevoEmpleado (Empleado emp) {
		List<Empleado> empleados = streamEmpleados().collect(Collectors.toList());
		empleados.add(emp);
		try(PrintStream out=new PrintStream(dir)) {        
            gson.toJson(empleados,out);
        }
        catch(IOException ex) {
            ex.printStackTrace();
        }
//		try (FileReader fr = new FileReader(dir); PrintStream out = new PrintStream(dir)) {
//		Empleado [] emps = gson.fromJson(fr, Empleado [].class);
//		List <Empleado> empleados = Arrays.asList(emps);
//		empleados.add(emp);
//		gson.toJson(empleados, out);
//		}catch (IOException ex) {
//			ex.printStackTrace();
//		}
		
//		Stream<Empleado> empleados = Stream.of(gson.fromJson(new FileReader(dir), Empleado[].class));
//		empleados.collect(Collectors.toList()).add(emp);
//		gson.toJson(empleados, new PrintStream(dir));
		
		
		
		
	}
	
	public List <Empleado> porDepartamento (String departamento) {
		return streamEmpleados()
				.filter(e -> e.getDepartamento().equals(departamento))
				.collect(Collectors.toList());
		
		
	}
	
	public Empleado buscarEmpleado (String nombre) {
		return streamEmpleados()
			.filter(e -> e.getDepartamento().equals(nombre))
			.findFirst()
			.orElse(null);
			
	}
	
	
	

}
