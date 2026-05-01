package articulos;

import java.io.Serializable;
import java.util.ArrayList;

import sujetos.Cocinero;

public class Bebida extends Platillos implements Serializable{
	private Boolean alcoholica;
	private Boolean esCaliente;
	
	public Boolean getAlcoholica() {
		return alcoholica;
	}
	public Boolean getEsCaliente() {
		return esCaliente;
	}
	
	
	
	public void setAlcoholica(Boolean alcoholica) {
		this.alcoholica = alcoholica;
	}
	public void setEsCaliente(Boolean esCaliente) {
		this.esCaliente = esCaliente;
	}
	public Bebida(int precio, String nombrePlatillo, ArrayList<String> alergenos, Cocinero preparadoPor, Boolean alcoholica, Boolean esCaliente) {
		super(precio, nombrePlatillo, alergenos);
		this.alcoholica = alcoholica;
		this.esCaliente = esCaliente;
	}
	
	

}
