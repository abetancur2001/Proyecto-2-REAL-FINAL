package sujetos;
import java.io.Serializable;
import java.util.ArrayList;

import articulos.Juego;


public abstract class Persona implements Serializable{
	private String nombre;
	private int edad;
	private int cedula;
	private ArrayList<Juego> juegosFav;

	
	public String getNombre() {
		return nombre;
	}
	public int getEdad() {
		return edad;
	}
	public int getCedula() {
		return cedula;
	}
	public ArrayList<Juego> getJuegosFav() {
		return juegosFav;
	}
	public Persona(String nombre, int edad, int cedula, ArrayList<Juego> juegosFav) {
		super();
		this.nombre = nombre;
		this.edad = edad;
		this.cedula = cedula;
		this.juegosFav = juegosFav;
	}

	public void agregarJuegoFav(Juego j){
		juegosFav.add(j);
	}
	
	public void eliminarJuegoFav(Juego j) {
		juegosFav.remove(j);
	}
	
	
	
	
}
