package articulos;
import java.io.Serializable;
import java.util.ArrayList;

import sujetos.Cocinero;

public abstract class Platillos extends Producto implements Serializable{
	protected int precio;
	protected String nombrePlatillo;
	protected ArrayList<String> Alergenos;
	protected final double impuestoPlatillo= 0.08;
	
	
	public int getPrecio() {
		return precio;
	}
	public void setPrecio(int precio) {
		this.precio = precio;
	}
	public String getNombrePlatillo() {
		return nombrePlatillo;
	}
	public void setNombrePlatillo(String nombrePlatillo) {
		this.nombrePlatillo = nombrePlatillo;
	}
	public ArrayList<String> getAlergenos() {
		return Alergenos;
	}
	public void setAlergenos(ArrayList<String> alergenos) {
		Alergenos = alergenos;
	}

	
	
	public double getImpuestoPlatillo() {
		return impuestoPlatillo;
	}
	public Platillos(int precio, String nombrePlatillo, ArrayList<String> alergenos) {
		super(precio);
		this.precio = precio;
		this.nombrePlatillo = nombrePlatillo;
		Alergenos = alergenos;
	}
	
	
	
	

}
