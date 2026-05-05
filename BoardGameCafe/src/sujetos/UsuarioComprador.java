package sujetos;

import java.io.Serializable;
import java.util.ArrayList;

import articulos.Alergenos;
import articulos.Juego;
import modelo.Prestamo;
import modelo.Venta;
import articulos.JuegoPrestamo;

public abstract class UsuarioComprador extends Usuario implements Serializable{

	protected ArrayList<Venta> comprasTotales;
	protected ArrayList<Prestamo> juegosPrestados;
	protected ArrayList<Alergenos> alergenos;
	private double puntosFidelidad;
	private double descuentosDisponibles;


	

	public UsuarioComprador(String nombre, int edad, int cedula, ArrayList<Juego> juegosFav, int password, String login,
			ArrayList<Venta> comprasTotales, ArrayList<Prestamo> juegosPrestados, ArrayList<Alergenos> alergenos,
			double puntosFidelidad, double descuentosDisponibles) {
		super(nombre, edad, cedula, juegosFav, password, login);
		this.comprasTotales = comprasTotales;
		this.juegosPrestados = juegosPrestados;
		this.alergenos = alergenos;
		this.puntosFidelidad = puntosFidelidad;
		this.descuentosDisponibles = descuentosDisponibles;
	}

	public ArrayList<Venta> getComprasTotales() {
		return comprasTotales;
	}

	public void setComprasTotales(ArrayList<Venta> comprasTotales) {
		this.comprasTotales = comprasTotales;
	}

	public ArrayList<Prestamo> getJuegosPrestados() {
		return juegosPrestados;
	}

	public void setJuegosPrestados(ArrayList<Prestamo> juegosPrestados) {
		this.juegosPrestados = juegosPrestados;
	}

	public ArrayList<Alergenos> getAlergenos() {
		return alergenos;
	}

	public void setAlergenos(ArrayList<Alergenos> alergenos) {
		this.alergenos = alergenos;
	}

	public double getPuntosFidelidad() {
		return puntosFidelidad;
	}

	public void setPuntosFidelidad(double puntosFidelidad) {
		this.puntosFidelidad = puntosFidelidad;
	}
	
	public void agregarCompra(Venta v) {
		comprasTotales.add(v);
	}

	public double getDescuentosDisponibles() {
		return descuentosDisponibles;
	}

	public void setDescuentosDisponibles(double descuentosDisponibles) {
		this.descuentosDisponibles = descuentosDisponibles;
	}
	
	
	
	
	
	
	
	

}
