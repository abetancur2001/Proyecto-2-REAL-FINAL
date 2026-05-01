package modelo;
import sujetos.Persona;
import sujetos.UsuarioComprador;

import java.io.Serializable;
import java.time.LocalDate;

import articulos.Juego;
import articulos.JuegoPrestamo;

public class Prestamo implements Serializable{
	
	private LocalDate fechaInicioPrestamo;
	private LocalDate fechaFinPrestamo;
	private JuegoPrestamo juegoAPrestar;
	private UsuarioComprador prestadoA;
	private int idPrestamo;
	
	public LocalDate getFechaInicioPrestamo() {
		return fechaInicioPrestamo;
	}
	public void setFechaInicioPrestamo(LocalDate fechaInicioPrestamo) {
		this.fechaInicioPrestamo = fechaInicioPrestamo;
	}
	public LocalDate getFechaFinPrestamo() {
		return fechaFinPrestamo;
	}
	public void setFechaFinPrestamo(LocalDate fechaFinPrestamo) {
		this.fechaFinPrestamo = fechaFinPrestamo;
	}
	public JuegoPrestamo getJuegoAPrestar() {
		return juegoAPrestar;
	}
	public UsuarioComprador getPrestadoA() {
		return prestadoA;
	}
	public int getIdPrestamo() {
		return idPrestamo;
	}
	
	public Prestamo(LocalDate fechaInicioPrestamo, LocalDate fechaFinPrestamo, JuegoPrestamo juegoSolicitado, UsuarioComprador prestadoA,
			int idPrestamo) {
		super();
		this.fechaInicioPrestamo = fechaInicioPrestamo;
		this.fechaFinPrestamo = fechaFinPrestamo;
		this.juegoAPrestar = juegoSolicitado;
		this.prestadoA = prestadoA;
		this.idPrestamo = idPrestamo;
	}
	
	
	
	
	
	

}
