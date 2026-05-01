package modelo;
import java.io.Serializable;
import java.time.LocalDate;

import modelo.Turno;
import sujetos.Empleado;

public class CambioTurno extends Solicitud implements Serializable{

	private Turno nuevoTurno;

	public CambioTurno(int idSolicitud, LocalDate fechaSolicitud, EstadoSolicitud estado, Empleado solicitante,
			Turno nuevoTurno) {
		super(idSolicitud, fechaSolicitud, estado, solicitante);
		this.nuevoTurno = nuevoTurno;
	}

	public Turno getNuevoTurno() {
		return nuevoTurno;
	}

	public void setNuevoTurno(Turno nuevoTurno) {
		this.nuevoTurno = nuevoTurno;
	}
	
	public void cambioTurnoEmp() {
		solicitante.setTurnoAsignado(nuevoTurno);
	}
	
	
	
	
}
