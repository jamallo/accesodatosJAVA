package util;

import java.time.LocalDate;
import java.util.List;

import com.google.gson.Gson;

import model.Pedido;

public class UtilidadesCliente {
	
	
	public static List<Pedido> pedidosFromJson(String json) {
//	Gson gson = new Gson();
//	//return gson.fromJson(json, new TypeToken<List<Pedido>>() {}.getType());
//	return List.of(gson.fromJson(json, Pedido[].class));
		Gson gson = new Gson()
	            .newBuilder()
	            .registerTypeAdapter(LocalDate.class, new LocalDateDesarializer())
	            .create();
	     //return gson.fromJson(json, new TypeToken<List<Pedido>>(){}.getType());
		 return List.of(gson.fromJson(json, Pedido[].class));
	}


}
