package tarea;

import java.util.List;

import model.Pedido;
import service.NuevosPedidos;
import service.PedidoService;

public class Tarea extends Thread {
	
	private String ruta;
	private String tienda;
	
	

	public Tarea(String ruta, String tienda) {
		super();
		this.ruta = ruta;
		this.tienda = tienda;
	}



	@Override
	public void run() {
		PedidoService fservice = new PedidoService();
		NuevosPedidos bDservice = new NuevosPedidos(ruta, tienda);
		List<Pedido> pedidos = fservice.guardarPedido();
		bDservice.pedidosTienda();
		
	
	

}
