package principal;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import service.CovidService;

public class Lanzador {

	public static void main(String[] args) {
		

CovidService covidService = new CovidService();
		
		ExecutorService executor = Executors.newCachedThreadPool();

		CompletableFuture<Long> pf1 = CompletableFuture.supplyAsync(() -> covidService.casosTotales(), executor);
		pf1.whenComplete((r, ex) -> System.out.println("Los casos totales son: " + r));
		covidService.incidenciaPais().forEach((item, media) -> System.out.println("La media de " + item + " es: " + media));
		executor.shutdown();
	}

}
