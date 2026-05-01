package modelo;

import java.io.Serializable;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import sujetos.Empleado;

public class Turno implements Serializable{
	
	private ArrayList<DayOfWeek> dias;
	private LocalTime horaEntrada;
	private LocalTime horaSalida;
	private Empleado solicitante;
	
	public Turno(ArrayList<DayOfWeek> dias, LocalTime horaEntrada, LocalTime horaSalida, Empleado solicitante) {
		super();
		this.dias = dias;
		this.horaEntrada = horaEntrada;
		this.horaSalida = horaSalida;
		this.solicitante = solicitante;
	}

	public ArrayList<DayOfWeek> getDias() {
		return dias;
	}

	public void setDias(ArrayList<DayOfWeek> dias) {
		this.dias = dias;
	}

	public LocalTime getHoraEntrada() {
		return horaEntrada;
	}

	public void setHoraEntrada(LocalTime horaEntrada) {
		this.horaEntrada = horaEntrada;
	}

	public LocalTime getHoraSalida() {
		return horaSalida;
	}

	public void setHoraSalida(LocalTime horaSalida) {
		this.horaSalida = horaSalida;
	}

	public Empleado getSolicitante() {
		return solicitante;
	}

	public void setSolicitante(Empleado solicitante) {
		this.solicitante = solicitante;
	}
	
	
	
	
	

}
