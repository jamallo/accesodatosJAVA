package principal;

import service.Capsula;

public class Test {

	public static void main(String[] args) {

		Capsula<String> cp = new Capsula<>("Hello");
		Capsula<Integer> cp2 = new Capsula<>(200);
	}

}
