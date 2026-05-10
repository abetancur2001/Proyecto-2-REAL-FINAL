package articulos;

import java.io.Serializable;

public abstract class Producto implements Serializable{
	private static final long serialVersionUID = 1L;
	protected int precio;

	public Producto(int precio) {
		super();
		this.precio = precio;
	}

	public int getPrecio() {
		return precio;
	}

	public void setPrecio(int precio) {
		this.precio = precio;
	}
	
	

}
