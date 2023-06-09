package service;


public class Multiplicar implements Runnable {
	int numero;
	public Multiplicar (int numero) {
		this.numero = numero;
	}
	@Override
	public void run() {
		for (int i = 1; i <= 10; i++) {
			
			int multiplicar =  i * numero; 
			System.out.println(numero + "X" + i + "=" + multiplicar );
			
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	

}
