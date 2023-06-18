package servidor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServidorService {

	public static void main(String[] args) {
		int port = 8000;
		ExecutorService executor = Executors.newCachedThreadPool();
		try (ServerSocket servidor = new ServerSocket(port)) {
			while (true) {
				System.out.println("A la espera de llamadas ...");
				// Objeto socket conectado con el cliente que ha hecho la llamada
				Socket sc = servidor.accept();

				System.out.println("... llamada recibida ...");
				// operacion de lectura / escritura
				// enviamos un mensaje de saludo al cliente
				// lanzamos una instancia de un runnable, que se encargará de la gestión de la
				// comunicación con cada cliente
				executor.submit(new HiloCliente(sc));

			}

		} catch (IOException ex) {
			ex.printStackTrace();
		}

	}

}
