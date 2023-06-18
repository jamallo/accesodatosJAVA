package principal;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import model.Pais;
import service.PaisService;

public class Lanzadera {

	public static void main(String[] args) {
		
		PaisService paisService = new PaisService();
		
		ExecutorService executor = Executors.newCachedThreadPool();

		CompletableFuture<String> pf1 = CompletableFuture.supplyAsync(() -> paisService.masPoblado());
		pf1.whenComplete((r, ex) -> System.out.println("El país más poblado es: " + r));
		//System.out.println(paisService.continentes());
		paisService.continentes().forEach(System.out::println);
		

	}

}
