package articulos;
import java.io.Serializable;

import modelo.Mesa;

public abstract class Juego implements Serializable{

	protected String nombre;
	protected int anio;
	protected String empresa;
	protected int cantidadJugadores;
	protected boolean esDificil;
	protected RestriccionEdad apto;
	protected TiposJuegos tipo;
	protected int numJugadores;
	
	public String getNombre() {
		return nombre;
	}
	public int getAnio() {
		return anio;
	}
	public String getEmpresa() {
		return empresa;
	}
	public int getCantidadJugadores() {
		return cantidadJugadores;
	}
	public boolean isEsDificil() {
		return esDificil;
	}
	public RestriccionEdad getApto() {
		return apto;
	}
	
	
	
	
	public int getNumJugadores() {
		return numJugadores;
	}
	public void setNumJugadores(int numJugadores) {
		this.numJugadores = numJugadores;
	}
	public TiposJuegos getTipo() {
		return tipo;
	}
	public void setTipo(TiposJuegos tipo) {
		this.tipo = tipo;
	}
	
	
	
	public Juego(String nombre, int anio, String empresa, int cantidadJugadores, boolean esDificil,
			RestriccionEdad apto, TiposJuegos tipo, int numJugadores) {
		super();
		this.nombre = nombre;
		this.anio = anio;
		this.empresa = empresa;
		this.cantidadJugadores = cantidadJugadores;
		this.esDificil = esDificil;
		this.apto = apto;
		this.tipo = tipo;
		this.numJugadores = numJugadores;
	}
	public boolean esCompatibleMesa(Mesa mesa) {

	    if (getApto() == RestriccionEdad.APTO_5ANIOS) {
	        return true;
	    }

	    if (getApto() == RestriccionEdad.APTO_MENORES) {
	        return !mesa.hayPequeños();
	    }

	    if (getApto() == RestriccionEdad.SOLO_ADULTOS) {
	        return !mesa.hayMenores() && !mesa.hayPequeños();
	    }

	    return false;
	}
	
	
}
