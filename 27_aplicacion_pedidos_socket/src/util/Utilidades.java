package util;






import java.time.LocalDate;
import java.util.List;

import com.google.gson.Gson;
import model.Pedido;

public class Utilidades {
//	final static String DIR = "D:\\Curso Certificado\\JAVA SE 8\\EJERCICIOS\\Ficheros_datos\\tiendas.json";
	
	
	
//	public static String fechaATexto (LocalDate fecha) {
//		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//		return fecha.format(formatter);
//	}
	
	public static String pedidosToJson(List<Pedido> pedidos) {
//		Gson gson = new Gson();  
//		return gson.toJson(pedidos);
		Gson gson = new Gson()
				.newBuilder()
				.registerTypeAdapter(LocalDate.class, new LocalDateSerializer())
				.create();
		return gson.toJson(pedidos);
        
	}
}


