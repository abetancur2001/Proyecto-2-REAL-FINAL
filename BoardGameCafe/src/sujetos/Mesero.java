package sujetos;

import java.io.Serializable;
import java.util.ArrayList;

import articulos.Juego;
import articulos.JuegoPrestamo;
import modelo.Prestamo;
import modelo.Turno;
import modelo.Venta;

public class Mesero extends Empleado implements Serializable{
	private ArrayList<Juego> listaJuegos;

	public Mesero(String nombre, int edad, int cedula, ArrayList<Juego> juegosFav, int password, String login,
			ArrayList<Venta> comprasTotales, ArrayList<Prestamo> juegosPrestados, ArrayList<String> alergenos,
			int puntosFidelidad, Turno turnoAsignado, ArrayList<Juego> listaJuegos) {
		super(nombre, edad, cedula, juegosFav, password, login, comprasTotales, juegosPrestados, alergenos,
				puntosFidelidad, turnoAsignado);
		this.listaJuegos = listaJuegos;
	}

	public ArrayList<Juego> getListaJuegos() {
		return listaJuegos;
	}

}
