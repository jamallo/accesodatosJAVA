package Tareas;

import service.Contador;

public class TareaVisita extends Thread {

	private Contador contador;
	
	
	public TareaVisita(Contador contador) {
		super();
		this.contador = contador;
	}


	@Override
	public void run() {
		//synchronized (contador) {
		contador.incrementar();
		//}
	}

	
	
}
