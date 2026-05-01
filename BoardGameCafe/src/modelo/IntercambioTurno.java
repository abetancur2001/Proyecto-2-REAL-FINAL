package modelo;

import java.io.Serializable;
import java.time.LocalDate;
import sujetos.Empleado;

public class IntercambioTurno extends Solicitud implements Serializable{
	
	private Empleado empleadoIntercambio;

	public IntercambioTurno(int idSolicitud, LocalDate fechaSolicitud, EstadoSolicitud estado, Empleado solicitante,
			Empleado empleadoIntercambio) {
		super(idSolicitud, fechaSolicitud, estado, solicitante);
		this.empleadoIntercambio = empleadoIntercambio;
	}

	public Empleado getEmpleadoIntercambio() {
		return empleadoIntercambio;
	}

	public void setEmpleadoIntercambio(Empleado empleadoIntercambio) {
		this.empleadoIntercambio = empleadoIntercambio;
	}
	
	public void intercambiarTurnos() {
		solicitante.setTurnoAsignado(empleadoIntercambio.getTurnoAsignado());
		empleadoIntercambio.setTurnoAsignado(solicitante.getTurnoAsignado());
	}
	
	

}
