package JavaBeans;

public class Contacto {

	String nombre;
	int numero;
	String email;

	public Contacto(String nombre, int numero, String email) {
		this.nombre = nombre;
		this.numero = numero;
		this.email = email;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.nombre = numero;
	}
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
}
