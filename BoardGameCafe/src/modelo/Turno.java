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
	
	public Turno(ArrayList<DayOfWeek> dias, LocalTime horaEntrada, LocalTime horaSalida) {
		super();
		this.dias = dias;
		this.horaEntrada = horaEntrada;
		this.horaSalida = horaSalida;
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


	
	
	
	

}
