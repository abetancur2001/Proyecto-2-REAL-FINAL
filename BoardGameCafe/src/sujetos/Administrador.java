package sujetos;

import java.io.Serializable;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

import articulos.EstadoJuego;
import articulos.Item;
import articulos.Juego;
import articulos.JuegoPrestamo;
import articulos.JuegoVenta;
import articulos.Platillos;
import articulos.Producto;
import exceptions.CambioTurnosException;
import exceptions.JuegoNoEncontradoException;
import modelo.Café;
import modelo.CambioTurno;
import modelo.Solicitud;
import modelo.SugerirPlatillo;
import modelo.Turno;
import modelo.EstadoSolicitud;
import modelo.IntercambioTurno;
import modelo.Prestamo;
import modelo.*;

public class Administrador extends Usuario implements Serializable{
	

	public Administrador(String nombre, int edad, int cedula, ArrayList<Juego> juegosFav, int password, String login) {
		super(nombre, edad, cedula, juegosFav, password, login);
	}

	
	public void aprobarSolicitudCambioTurno (Solicitud sol, Café cafe) {
		if(sol instanceof CambioTurno) {
			CambioTurno ct = (CambioTurno)sol;
			if(cafe.validarCambioTurno(ct)) {
				ct.cambioTurnoEmp();
				ct.setEstado(EstadoSolicitud.APROBADA);
			}
			else {
				ct.setEstado(EstadoSolicitud.RECHAZADA);
			}
		}
		else if(sol instanceof IntercambioTurno) {
			IntercambioTurno it = (IntercambioTurno)sol;
			if(cafe.validarIntercambioTurnos(it)) {
				it.intercambiarTurnos();
				it.setEstado(EstadoSolicitud.APROBADA);
			}
			else {
				it.setEstado(EstadoSolicitud.RECHAZADA);
			}
		}
		else if(sol instanceof SugerirPlatillo) {
			SugerirPlatillo sp = (SugerirPlatillo) sol;
			cafe.añadirPlatillo(sp.getPlatilloSugerido());
			sp.setEstado(EstadoSolicitud.APROBADA);
			
		}
	}

	
	public void marcarJuegoDesparecido(Prestamo prestamo) {

		long dias = ChronoUnit.DAYS.between(prestamo.getFechaInicioPrestamo(), LocalDate.now());

		if (dias > 7) {
			prestamo.getJuegoAPrestar().setDesaparecido(true);
		}
	}
	
	public void cambiarTurno (Turno tur, Empleado empl, Café cafe) {
		Turno turno = null;
		
		for(DayOfWeek day : tur.getDias()) {
			int numMeseros = 0;
			int numCocineros = 0;
			
			for(Empleado emp : cafe.getEmpleados()) {
				if (emp.equals(empl)) {
					turno = tur;
				}
				else {
					turno = emp.getTurnoAsignado();
				}
				
				if (turno.getDias().contains(day) && tur.getHoraEntrada().isBefore(turno.getHoraSalida()) && tur.getHoraSalida().isAfter(turno.getHoraEntrada())) {
					    if(emp instanceof Mesero) {
					        numMeseros += 1;
					    }
					    else if(emp instanceof Cocinero) {
					        numCocineros += 1;
					    }
					}
			}
			
			if (numMeseros < 2 || numCocineros < 1) {
				throw new CambioTurnosException("No hay el personal necesarios para hacer el cambio");
			}
		}
		
		empl.cambioTurno(tur);;
	}
	
	
	public void obtenerVentasXCliente (int cedula, Café cafe) {
		System.out.println("Compras del Cliente con número de cedula: " + cedula);
		for(Venta v : cafe.getHistorialComprasUsuario().get(cedula)) {
			System.out.println(
					"Fecha Compra: " + v.getFechaVenta() + "\n"
					+ "Total: " + v.getTotal() + "\n"
					+ "Impuestos: " + v.getImpuestos() + "\n"
					+ "Subtotal: " + v.getSubtotal() + "\n"
					+ "Propinas: " + v.getPropina() + "\n\n"
					);
		}
	}
	



	
	

}
