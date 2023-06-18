package servidor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.List;

import model.Pedido;
import util.Utilidades;

public class HiloCliente implements Runnable {
	private Socket sc;
	public HiloCliente(Socket sc) {
		this.sc = sc;
	}

	@Override
	public void run() {
		//gestionamos la comunicación con el cliente
		try (PrintStream out = new PrintStream(sc.getOutputStream());
			BufferedReader bf = new BufferedReader(new InputStreamReader(sc.getInputStream()))) {
//			PedidosService pedidosService = new PedidosService();					
			List <Pedido> pedidos = PedidosService.filtroTiendaDB(bf.readLine());
			out.println((Utilidades.pedidosToJson(pedidos)));
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		
	}

}
