package service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.Comparator;
import java.util.List;

import com.google.gson.Gson;

import model.Pais;

public class PaisService {
	
	private final String DIR = "https://restcountries.com/v2/all";

	
	public List<Pais> paisesFromJson() {
		Gson gson = new Gson();
		try {
			HttpRequest request = HttpRequest.newBuilder().uri(URI.create(DIR)).GET().build();
			HttpClient cliente = HttpClient.newBuilder().build();
			HttpResponse<String> respuesta = cliente.send(request, BodyHandlers.ofString());
			return List.of(gson.fromJson(respuesta.body(), Pais[].class));
		} catch (Exception ex) {
			ex.printStackTrace();
			return List.of();
		}
	}
	
	public String masPoblado () {
		return paisesFromJson().stream()
			//.max(Comparator.comparingLong(p -> p.getPoblacion()))
			//.max(Comparator.comparingLong(Pais::getPoblacion))
			.max((p1, p2) -> Long.compare(p1.getPoblacion(), p2.getPoblacion()))	
			.orElse(new Pais())
			.getNombre();
			
	}
	
	public List<String> continentes () {
		return paisesFromJson ().stream() 
				//.map(p -> p.getContinente())
				.map(Pais::getContinente)
				.distinct()
				.toList();
	}

}
