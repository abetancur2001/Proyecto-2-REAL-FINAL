package modelo;

import java.io.Serializable;
import java.time.LocalDate;

import articulos.Platillos;
import sujetos.Empleado;

public class SugerirPlatillo extends Solicitud implements Serializable{
	
	private Platillos platilloSugerido;

	public SugerirPlatillo(int idSolicitud, LocalDate fechaSolicitud, EstadoSolicitud estado, Empleado solicitante,
			Platillos platilloSugerido) {
		super(idSolicitud, fechaSolicitud, estado, solicitante);
		this.platilloSugerido = platilloSugerido;
	}

	public Platillos getPlatilloSugerido() {
		return platilloSugerido;
	}

	public void setPlatilloSugerido(Platillos platilloSugerido) {
		this.platilloSugerido = platilloSugerido;
	}
	
	

}
