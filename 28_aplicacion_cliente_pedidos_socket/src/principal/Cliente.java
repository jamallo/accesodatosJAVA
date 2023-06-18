package principal;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import service.ClienteService;

public class Cliente {

	public static void main(String[] args) throws IOException {

			// lectura de mensaje enviado desde el servidor
		ClienteService media = new ClienteService();
		ExecutorService executor = Executors.newCachedThreadPool();
		for (int i = 1; i<=3 ; i++) {
			String nombre = "tienda" + i;
		CompletableFuture<Double> cfi = CompletableFuture.supplyAsync(() -> media.mediaUnidades(nombre),executor);
		cfi.whenCompleteAsync((r, ex) -> System.out.println("Unidades media" + r));
		}
		executor.shutdown();
		System.in.read();
		

		
	}

}
