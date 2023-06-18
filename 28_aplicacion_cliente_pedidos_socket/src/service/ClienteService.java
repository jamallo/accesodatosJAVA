package service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.List;
import java.util.stream.Collectors;

import model.Pedido;
import util.UtilidadesCliente;

public class ClienteService {
	private String dir = "localhost";
	private int port = 8080;
	
	public Double mediaUnidades (String tienda) {
		try (Socket sc = new Socket(dir, port); 
				PrintStream out = new PrintStream(sc.getOutputStream()) ;
				BufferedReader bf = new BufferedReader(new InputStreamReader(sc.getInputStream()))) {
			out.println("tienda1");
			String jsonPedidos = bf.readLine();
			List <Pedido> pedidos = UtilidadesCliente.pedidosFromJson(jsonPedidos);
					return pedidos.stream()
							.collect(Collectors.averagingDouble(p->p.getUnidades()));

			// lectura de mensaje enviado desde el servidor
			
		} catch (IOException ex) {
			ex.printStackTrace();
			return 0.0;
		}
	}

}
