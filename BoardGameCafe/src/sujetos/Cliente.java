package sujetos;

import java.io.Serializable;
import java.util.ArrayList;

import articulos.Juego;
import articulos.JuegoPrestamo;
import modelo.Prestamo;
import modelo.Venta;

public class Cliente extends UsuarioComprador implements Serializable{

	public Cliente(String nombre, int edad, int cedula, ArrayList<Juego> juegosFav, int password, String login,
			ArrayList<Venta> comprasTotales, ArrayList<Prestamo> juegosPrestados, ArrayList<String> alergenos,
			int puntosFidelidad) {
		super(nombre, edad, cedula, juegosFav, password, login, comprasTotales, juegosPrestados, alergenos,
				puntosFidelidad);
	}


	




	

	
}
