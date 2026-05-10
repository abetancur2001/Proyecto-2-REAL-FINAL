package articulos;
import java.io.Serializable;
import java.util.ArrayList;

public abstract class Platillos extends Producto implements Serializable{
	private static final long serialVersionUID = 1L;
	protected int precio;
	protected String nombrePlatillo;
	protected ArrayList<Alergenos> Alergenos;
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
	public ArrayList<Alergenos> getAlergenos() {
		return Alergenos;
	}
	public void setAlergenos(ArrayList<Alergenos> alergenos) {
		Alergenos = alergenos;
	}

	
	
	public double getImpuestoPlatillo() {
		return impuestoPlatillo;
	}
	public Platillos(int precio, String nombrePlatillo, ArrayList<Alergenos> alergenos) {
		super(precio);
		this.precio = precio;
		this.nombrePlatillo = nombrePlatillo;
		Alergenos = alergenos;
	}

	@Override
	public String toString() {
		return nombrePlatillo;
	}
	
	

}
