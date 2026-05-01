package articulos;

import java.io.Serializable;

public class JuegoVenta extends Producto implements Serializable{
	
	private int stock;
	private Juego infoJuegoVenta;
	private final double impuestoJuego = 0.19;
	
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}

	public Juego getInfoJuegoVenta() {
		return infoJuegoVenta;
	}
	
	
	
	public double getImpuestoJuego() {
		return impuestoJuego;
	}
	public JuegoVenta(int precio, int stock, Juego infoJuegoVenta) {
		super(precio);
		this.stock = stock;
		this.infoJuegoVenta = infoJuegoVenta;
	}
	public boolean estaDisponibleJuegoVenta() {
		if (stock<1) {
			return false;
		}
		return true;
	}
	
	
	
	
	

}
