package sujetos;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

import articulos.Juego;
import modelo.Café;
import modelo.Prestamo;
import modelo.*;

public class Administrador extends Usuario implements Serializable{
	

	public Administrador(String nombre, int edad, int cedula, ArrayList<Juego> juegosFav, int password, String login) {
		super(nombre, edad, cedula, juegosFav, password, login);
	}

	

	
	
	public void obtenerVentasXCliente (int cedula, Café cafe) {
		System.out.println("Compras del Cliente con número de cedula: " + cedula);
		for(Venta v : cafe.getHistorialComprasUsuario().get(cedula)) {
			System.out.println(
					"Fecha Compra: " + v.getFechaVenta() + "\n"
					+ "Total: " + v.getTotal() + "\n"
					+ "Impuestos: " + v.getImpuestos() + "\n"
					+ "Subtotal: " + v.getSubtotal() + "\n"
					+ "Propinas: " + v.getPropina() + "\n\n"
					);
		}
	}
	



	
	

}
