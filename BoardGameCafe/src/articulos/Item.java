package articulos;

import java.io.Serializable;

public class Item implements Serializable{

	private int cantidad;
	private Producto productoAsociado;
	public Item(int cantidad, Producto productoAsociado) {
		super();
		this.cantidad = cantidad;
		this.productoAsociado = productoAsociado;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public Producto getProductoAsociado() {
		return productoAsociado;
	}
	public void setProductoAsociado(Producto productoAsociado) {
		this.productoAsociado = productoAsociado;
	}
	
	
	
	
	
}
