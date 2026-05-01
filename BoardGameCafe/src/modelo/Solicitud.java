package modelo;

import java.io.Serializable;
import java.time.LocalDate;
import sujetos.Empleado;

public abstract class Solicitud implements Serializable{

	protected int idSolicitud;
	protected LocalDate fechaSolicitud;
	protected EstadoSolicitud estado;
	protected Empleado solicitante;
	
	public Solicitud(int idSolicitud, LocalDate fechaSolicitud, EstadoSolicitud estado, Empleado solicitante) {
		super();
		this.idSolicitud = idSolicitud;
		this.fechaSolicitud = fechaSolicitud;
		this.estado = estado;
		this.solicitante = solicitante;
	}

	public int getIdSolicitud() {
		return idSolicitud;
	}

	public void setIdSolicitud(int idSolicitud) {
		this.idSolicitud = idSolicitud;
	}

	public LocalDate getFechaSolicitud() {
		return fechaSolicitud;
	}

	public void setFechaSolicitud(LocalDate fechaSolicitud) {
		this.fechaSolicitud = fechaSolicitud;
	}

	public EstadoSolicitud getEstado() {
		return estado;
	}

	public void setEstado(EstadoSolicitud estado) {
		this.estado = estado;
	}

	public Empleado getSolicitante() {
		return solicitante;
	}

	public void setSolicitante(Empleado solicitante) {
		this.solicitante = solicitante;
	}
	
	
	
	
	
}
