package model;

import com.google.gson.annotations.SerializedName;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Pais {
	@SerializedName("alpha3Code")
	String codigoPais;
	@SerializedName("name")
	String nombre;
	@SerializedName("region")
	String continente;
	@SerializedName("population")
	int poblacion;
	@SerializedName("flag")
	String bandera;

}
