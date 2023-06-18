package principal;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import model.Pedido;
import service.Calculos;

public class LanzadorCompletable {

	public static void main(String[] args) {
		Calculos service = new Calculos();
		ExecutorService executor = Executors.newCachedThreadPool();

		CompletableFuture<String> cf1 = CompletableFuture.supplyAsync(() -> service.productoMasVendido());
		CompletableFuture<Double> cf2 = CompletableFuture.supplyAsync(() -> service.mediaUnidades());
		
		cf1.whenCompleteAsync((r, ex) -> System.out.println("El producto más vendido es :" + r));
		cf2.whenComplete((r, ex) -> System.out.println("La media es: " + r));
		List <Pedido> pedidos = service.pedidos();
		pedidos.forEach(p -> System.out.println(p.getProducto() + " - " + p.getTienda()));

	}

}
