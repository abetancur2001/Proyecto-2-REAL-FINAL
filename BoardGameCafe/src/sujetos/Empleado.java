package sujetos;

import java.io.Serializable;
import java.util.ArrayList;

import articulos.Alergenos;
import modelo.Prestamo;
import modelo.Turno;

import articulos.Juego;
import articulos.JuegoPrestamo;
import modelo.Venta;

public abstract class Empleado extends UsuarioComprador implements Serializable{
	
	protected Turno turnoAsignado;

	

	

	public Empleado(String nombre, int edad, int cedula, ArrayList<Juego> juegosFav, int password, String login,
			ArrayList<Venta> comprasTotales, ArrayList<Prestamo> juegosPrestados, ArrayList<Alergenos> alergenos,
			double puntosFidelidad, double descuentosDisponibles, Turno turnoAsignado) {
		super(nombre, edad, cedula, juegosFav, password, login, comprasTotales, juegosPrestados, alergenos,
				puntosFidelidad, descuentosDisponibles);
		this.turnoAsignado = turnoAsignado;
	}

	public Turno getTurnoAsignado() {
		return turnoAsignado;
	}

	public void setTurnoAsignado(Turno turnoAsignado) {
		this.turnoAsignado = turnoAsignado;
	}

	
	public void cambioTurno(Turno tur) {
		turnoAsignado = tur;
	}

	
	
	

	
	
}
