package modelo;
import sujetos.Cliente;

import java.io.Serializable;
import java.util.ArrayList;

public class Mesa implements Serializable{
	private Boolean tieneBebidadCalientes;
	private int idMesa;
	private ArrayList<Cliente> personasSentadas;
	private boolean ocupada = false;
	private int capacidad;
	
	public Boolean getTieneBebidadCalientes() {
		return tieneBebidadCalientes;
	}

	public int getIdMesa() {
		return idMesa;
	}
	
	public ArrayList<Cliente> getPersonasSentadas() {
		return personasSentadas;
	}
	public void setPersonasSentadas(ArrayList<Cliente> personasSentadas) {
		this.personasSentadas = personasSentadas;
	}
	
	public int getNumPersonasSentadas () {
		return this.personasSentadas.size();
	}
	
	public boolean hayMenores() {
		for (Cliente c: this.personasSentadas) {
			if (c.getEdad() < 18) {
				return true;
				}
			}
		return false;
		}
	
	public boolean hayPequeños() {
		for (Cliente c: this.personasSentadas) {
			if (c.getEdad() <= 5) {
				return true;
				}
			}
		return false;
	}
	
	public boolean hayMayores() {
		for (Cliente c: this.personasSentadas) {
			if (c.getEdad() > 18) {
				return true;
				}
			}
		return false;
	}

	public boolean isOcupada() {
		return ocupada;
	}

	public void setOcupada(boolean ocupada) {
		this.ocupada = ocupada;
	}
	
	

	public int getCapacidad() {
		return capacidad;
	}

	public void setCapacidad(int capacidad) {
		this.capacidad = capacidad;
	}

	public Mesa(Boolean tieneBebidadCalientes, int idMesa, ArrayList<Cliente> personasSentadas, boolean ocupada,
			int capacidad) {
		super();
		this.tieneBebidadCalientes = tieneBebidadCalientes;
		this.idMesa = idMesa;
		this.personasSentadas = personasSentadas;
		this.ocupada = ocupada;
		this.capacidad = capacidad;
	}

	
	
	
	
	

}
