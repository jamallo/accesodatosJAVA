package tareas;


public class TareaAscendente extends Thread {

	@Override
	public void run() {
		for (int i = 1; i<= 100; i++) {
			System.out.println(i);	
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
