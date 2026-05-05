package sujetos;

import java.io.Serializable;
import java.util.ArrayList;

import articulos.Alergenos;
import articulos.Juego;
import articulos.JuegoPrestamo;
import modelo.Turno;
import modelo.Venta;
import modelo.Prestamo;


public class Cocinero extends Empleado implements Serializable{

	public Cocinero(String nombre, int edad, int cedula, ArrayList<Juego> juegosFav, int password, String login,
			ArrayList<Venta> comprasTotales, ArrayList<Prestamo> juegosPrestados, ArrayList<Alergenos> alergenos,
			double puntosFidelidad, double descuentosDisponibles, Turno turnoAsignado) {
		super(nombre, edad, cedula, juegosFav, password, login, comprasTotales, juegosPrestados, alergenos,
				puntosFidelidad, descuentosDisponibles, turnoAsignado);
	}

	

	

	

	

	


	

}
