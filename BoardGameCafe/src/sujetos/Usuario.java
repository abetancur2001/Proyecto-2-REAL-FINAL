package sujetos;

import java.util.ArrayList;

import articulos.Juego;

public abstract class Usuario extends Persona{
	
	private int password;
	private String login;

	public Usuario(String nombre, int edad, int cedula, ArrayList<Juego> juegosFav, int password, String login) {
		super(nombre, edad, cedula, juegosFav);
		this.password = password;
		this.login = login;
	}

	public int getPassword() {
		return password;
	}

	public void setPassword(int password) {
		this.password = password;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}
	
	
	
	
	
	

}
