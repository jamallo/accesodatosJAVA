package principal;

import java.util.Scanner;

import service.Multiplicar;

public class Lanzadera {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Introduce tres números a multiplicar");
		Multiplicar m1 = new Multiplicar(sc.nextInt());
		Multiplicar m2 = new Multiplicar(sc.nextInt());
		Multiplicar m3 = new Multiplicar(sc.nextInt());
		new Thread(m1).start();
		new Thread(m2).start();
		new Thread(m3).start();
		for(int i = 1; i <= 10; i++) {
			System.out.println("haciendo otras cosas ...");
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
