package modelo;

import java.util.ArrayList;

import articulos.Bebida;
import articulos.Item;
import articulos.JuegoVenta;
import articulos.Platillos;
import articulos.Producto;
import articulos.TiposJuegos;
import sujetos.Empleado;
import sujetos.UsuarioComprador;

import java.io.Serializable;
import java.time.LocalDate;

public class Venta implements Serializable{

	private ArrayList<Item> items;
	private UsuarioComprador comprador;
	private LocalDate fechaVenta;
	private double total;
	private double impuestos;
	private double subtotal; 
	private double propina;
	

	public ArrayList<Item> getItems() {
		return items;
	}

	public void setItems(ArrayList<Item> items) {
		this.items = items;
	}

	public UsuarioComprador getComprador() {
		return comprador;
	}

	public void setComprador(UsuarioComprador comprador) {
		this.comprador = comprador;
	}

	public LocalDate getFechaVenta() {
		return fechaVenta;
	}

	public void setFechaVenta(LocalDate fechaVenta) {
		this.fechaVenta = fechaVenta;
	}
	
	
	

	public double getPropina() {
		return propina;
	}

	public void setPropina(double propina) {
		this.propina = propina;
	}

	public double getImpuestos() {
		return impuestos;
	}

	public void setImpuestos(double impuestos) {
		this.impuestos = impuestos;
	}

	public double getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public Venta(ArrayList<Item> items, UsuarioComprador comprador, LocalDate fechaVenta) {
		super();
		this.items = items;
		this.comprador = comprador;
		this.fechaVenta = fechaVenta;
	}

	public boolean hayAlchoholicas() {
		for (Item i : items) {
			Producto p = i.getProductoAsociado();

			if (p instanceof Bebida) {
				Bebida b = (Bebida) p;

				if (b.getAlcoholica()) {
					return true;
				}
			}
		}

		return false;
	}

	public boolean hayAlergenoEnVenta(Mesa mesa) {
		for (UsuarioComprador us : mesa.getPersonasSentadas()) {
			for (String a : us.getAlergenos()) {
				for (Item i : items) {
					Producto p = i.getProductoAsociado();
					if (p instanceof Platillos) {
						Platillos platillo = (Platillos) p;
						if (platillo.getAlergenos().contains(a)) {
							return true;
						}

					}

				}
			}
		}

		return false;
	}

	public boolean hayCalienteVsAccion(Mesa mesa) {

		for (UsuarioComprador us : mesa.getPersonasSentadas()) {
			for (Prestamo jp : us.getJuegosPrestados()) {
				if (jp.getJuegoAPrestar().getInfoJuego().getTipo() == TiposJuegos.ACCION) {
					for (Item i : items) {
						Producto p = i.getProductoAsociado();
						if (p instanceof Bebida) {
							Bebida b = (Bebida) p;
							if (b.getEsCaliente()) {
								return true;
							}
						}
					}

				}

			}

		}
		return false;
	}

	public double calculoCuenta(boolean usarPuntos, boolean usaCodigo) {

		double total = 0;
		double precioP = 0;
		double precioImp = 0;
		double subTotal = 0;
		double precioPu = 0;
		double propina = 0;

		for (Item i : items) {
			Producto p = i.getProductoAsociado();
			precioP = i.getCantidad() * p.getPrecio();

			if (p instanceof Platillos) {
				Platillos pla = (Platillos) p;
				precioImp = (precioP * pla.getImpuestoPlatillo()) + precioP;
			}

			else if (p instanceof JuegoVenta) {
				JuegoVenta jv = (JuegoVenta) p;
				precioImp = (precioP * jv.getImpuestoJuego() + precioP);
			}
			
			subTotal += precioP;
			total += precioImp;

		}
		
		propina = subTotal * 0.10;
		
		if (comprador instanceof Empleado) {
			total = total * 0.8;
		}
		else if(usaCodigo) {
			total = total * 0.9;
		}

		if (usarPuntos && (comprador.getPuntosFidelidad() <= total)) {
			precioPu = (total - comprador.getPuntosFidelidad()) + propina;
		} else {
			precioPu = total + propina;
		}
		
		sumarPuntos(total);
		
		setTotal(precioPu);
		setSubtotal(subTotal);
		setImpuestos(total - subtotal);
		setPropina(propina);
		
		return precioPu;
	}
	
	public void sumarPuntos(double valor) {
		double puntos = valor * 0.01;
		comprador.setPuntosFidelidad(comprador.getPuntosFidelidad() + puntos);
	}
	
	public void agregarItem (Item i) {
		items.add(i);
	}
	


}


