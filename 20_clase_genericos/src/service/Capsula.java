package service;


public class Capsula <T>{

	private T valor;

	public Capsula(T valor) {
		super();
		this.valor = valor;
	}

	public T getValor() {
		return valor;
	}

	public void setValor(T valor) {
		this.valor = valor;
	}
	
}