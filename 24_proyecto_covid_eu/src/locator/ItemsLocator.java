package locator;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.Arrays;
import java.util.stream.Stream;

import com.google.gson.Gson;

import model.Item;

public class ItemsLocator {
	final static String DIR = "https://opendata.ecdc.europa.eu/covid19/nationalcasedeath/json/";
	
	public static Stream<Item> covidFromJson() {
		Gson gson = new Gson();
		try {
			HttpRequest request = HttpRequest.newBuilder().uri(URI.create(DIR)).GET().build();
			HttpClient cliente = HttpClient.newBuilder().build();
			HttpResponse<String> respuesta = cliente.send(request, BodyHandlers.ofString());
			return Arrays.stream(gson.fromJson(respuesta.body(), Item[].class));
		} catch (Exception ex) {
			ex.printStackTrace();
			return Stream.empty();
		}
	}
}
