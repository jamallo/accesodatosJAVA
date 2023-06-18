package prueba;

public class Test {

	public static void main(String[] args) {
		//creamos coche con ruedas y peso
		Coche c1 = new CocheBuilder()
				.withRuedas(5)
				.withPeso(1200)
				.build();
		
		//creamos otro coche con todas las propiedades
		Coche c2 = new CocheBuilder()
				.withColor("verde")
				.withPeso(1500)
				.withPotencia(100)
				.withRuedas(2)
				.build();
	}

}
