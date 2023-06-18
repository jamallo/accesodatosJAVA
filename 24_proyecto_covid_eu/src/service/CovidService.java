package service;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


import locator.ItemsLocator;
import model.Item;
import util.Utilidades;


public class CovidService {

	Utilidades util = new Utilidades();
	
//	public List<Item> covidFromJson() {
//		Gson gson = new Gson();
//		try {
//			HttpRequest request = HttpRequest.newBuilder().uri(URI.create(DIR)).GET().build();
//			HttpClient cliente = HttpClient.newBuilder().build();
//			HttpResponse<String> respuesta = cliente.send(request, BodyHandlers.ofString());
//			return List.of(gson.fromJson(respuesta.body(), Item[].class));
//		} catch (Exception ex) {
//			ex.printStackTrace();
//			return List.of();
//		}
//	}
	
	
	public LocalDate filtroFecha () {
		return Utilidades.convertirTextoFecha(ItemsLocator.covidFromJson() //Stream <Item>
				.max(Comparator.comparing(it -> Utilidades.convertirTextoFecha(it.getFechaMuestra())))
				.orElse(new Item())
				.getFechaMuestra());
				
	}
	
	public long casosTotales () {
		LocalDate f = filtroFecha();
		return ItemsLocator.covidFromJson()
			.filter(it -> it.getIndicador()
					.equals("cases") 
					&& 
					Utilidades.convertirTextoFecha(it.getFechaMuestra())
					.equals(f))
			.collect(Collectors.summingLong(it -> it.getCasosAcumulados()));
			
			
	}
	
	public double incidenciaMedia (String pais) {
		return ItemsLocator.covidFromJson()
				.filter(it -> it.getPais().equals(pais) && it.getIndicador().equals("cases"))//Stream<Pais>
				.collect(Collectors.averagingDouble(it ->it.getIncidencia()));
				
	}
	
	public List <String> paises() {
		return ItemsLocator.covidFromJson()
				.map(p -> p.getPais())
				.collect(Collectors.toList());
				
	}
	
	
	
	public Map<String, Double> incidenciaPais () {
		return ItemsLocator.covidFromJson()
				.filter(it -> it.getIndicador().equals("cases"))
				.collect(Collectors.groupingBy(p -> p.getPais(), Collectors.averagingDouble(p -> p.getIncidencia())));
				
	}
}
