package modelo;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

import sujetos.Cliente;

public class Reserva implements Serializable{

	private int idReserva;
	private LocalDate fechaReserva;
	private Mesa mesaReserva;
	private Cliente reservadoPor;
	private int numPersonas;
	private LocalTime horaReserva;
	

	public int getIdReserva() {
		return idReserva;
	}

	public void setIdReserva(int idReserva) {
		this.idReserva = idReserva;
	}

	public LocalDate getFechaReserva() {
		return fechaReserva;
	}

	public void setFechaReserva(LocalDate fechaReserva) {
		this.fechaReserva = fechaReserva;
	}

	public Mesa getMesaReserva() {
		return mesaReserva;
	}

	public void setMesaReserva(Mesa mesaReserva) {
		this.mesaReserva = mesaReserva;
	}

	public Cliente getReservadoPor() {
		return reservadoPor;
	}

	public void setReservadoPor(Cliente reservadoPor) {
		this.reservadoPor = reservadoPor;
	}

	public int getNumPersonas() {
		return numPersonas;
	}

	public void setNumPersonas(int numPersonas) {
		this.numPersonas = numPersonas;
	}

	public LocalTime getHoraReserva() {
		return horaReserva;
	}

	public void setHoraReserva(LocalTime horaReserva) {
		this.horaReserva = horaReserva;
	}

	public Reserva(int idReserva, LocalDate fechaReserva, Mesa mesaReserva, Cliente reservadoPor,
			int numPersonas, LocalTime horaReserva) {
		super();
		this.idReserva = idReserva;
		this.fechaReserva = fechaReserva;
		this.mesaReserva = mesaReserva;
		this.reservadoPor = reservadoPor;
		this.numPersonas = numPersonas;
		this.horaReserva = horaReserva;
	}
	
	
	
	
	
	
	
	
	
}
