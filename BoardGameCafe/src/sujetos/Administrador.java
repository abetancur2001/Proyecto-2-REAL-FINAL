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
import modelo.Venta;

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
			añadirPlatillo(sp.getPlatilloSugerido(), cafe);
			sp.setEstado(EstadoSolicitud.APROBADA);
			
		}
	}
	
	public void verHistorialVenta(Café cafe) {
		cafe.getHistorialVentas();
	}
	
	public void verHistorialPrestamo(Café cafe) {
		cafe.getHistorialPrestamos();
	}
	
	public void revisarJuegos(Café cafe){
		for (Prestamo p: cafe.getHistorialPrestamos()) {
			System.out.println(
					p.getJuegoAPrestar().getInfoJuego().getNombre() + "\n" 
					+ "Disponible: " + p.getJuegoAPrestar().isDisponible() + "\n"
					+ "Desaparecido: "+ p.getJuegoAPrestar().isDesaparecido() + "\n"
					+ "Veces prestado: "+ p.getJuegoAPrestar().getVecesPrestado() + "\n"
					+ "Fecha Inicial Prestamo: "+ p.getFechaInicioPrestamo() + "\n"
					+ "Prestado a: " + p.getPrestadoA().getNombre()
					);
		}
	}
	
	public void añadirPlatillo(Platillos p, Café cafe) {
		cafe.getMenú().add(p);
	}
	
	public void añadirJuegoPrestamo (JuegoPrestamo jp, Café cafe) {
		cafe.getInventarioJuegosPrestamo().add(jp);
	}
	
	public void añadirJuegoVenta (JuegoVenta jv, Café cafe) {
		cafe.getInventarioJuegosVenta().add(jv);
	}
	
	public void moverVentaAPrestamo (JuegoVenta jv, Café cafe) {
		
		boolean bandera = false;
		
		for (JuegoVenta jve : cafe.getInventarioJuegosVenta()) {
			if(jve.equals(jv)) {
				bandera = true;
			}
		}
		
		if(bandera) {
			cafe.getInventarioJuegosVenta().remove(jv);
			
			JuegoPrestamo jp = new JuegoPrestamo(true, false, EstadoJuego.NUEVO, jv.getInfoJuegoVenta(), 0);
			cafe.getInventarioJuegosPrestamo().add(jp);
		}
		else {
			throw new JuegoNoEncontradoException("Juego no encontrado en el inventario de juegos para la venta");
		}
		
	}
	
	public void repararJuegoPrestamo (JuegoPrestamo jp, Café cafe) {
		JuegoVenta juegoReemplazante = null;
		if(cafe.getInventarioJuegosPrestamo().contains(jp) && (jp.getEstado() == EstadoJuego.DANADO || jp.getEstado() == EstadoJuego.INCOMPLETO || jp.getEstado() == EstadoJuego.DESGASTADO)) {
			for(JuegoVenta jv : cafe.getInventarioJuegosVenta()) {
				if(jv.getInfoJuegoVenta().equals(jp.getInfoJuego())) {
					juegoReemplazante = jv;
					break;
				}
			}
			
			if (juegoReemplazante == null) {
				throw new JuegoNoEncontradoException("Juego reemplazante no encontrado");
			}
			
			cafe.getInventarioJuegosVenta().remove(juegoReemplazante);
			jp.setEstado(EstadoJuego.NUEVO);
			jp.setDisponible(true);
			jp.setDesaparecido(false);
			
		}
	}
	
	public void revisarVentasDia (Café cafe, LocalDate fecha) {
		
		double total = 0;
		double subTotalJuegos = 0;
		double impuestosJuegos = 0;
		double subTotalPlatillos = 0;
		double impuestoPlatillos = 0;
		double propinas = 0;
		
		for(Venta v : cafe.getHistorialVentas()) {
			if (v.getFechaVenta().equals(fecha)) {
				total += v.getTotal();
				propinas += v.getPropina();
				for (Item i : v.getItems()) {
					Producto p = i.getProductoAsociado();
					if( p instanceof JuegoVenta) {
						JuegoVenta jv = (JuegoVenta) p;
						subTotalJuegos += i.getCantidad() * i.getProductoAsociado().getPrecio();
						impuestosJuegos += (i.getCantidad() * i.getProductoAsociado().getPrecio()) * jv.getImpuestoJuego();
					}
					else if (p instanceof Platillos) {
						Platillos pla = (Platillos) p;
						subTotalPlatillos += i.getCantidad() * i.getProductoAsociado().getPrecio();
						impuestoPlatillos += (i.getCantidad() * i.getProductoAsociado().getPrecio()) * pla.getImpuestoPlatillo(); 
						
					}
				}
			}
		}
		
		System.out.println(
				"Dia: "+ fecha + "\n"
				+ "JUEGOS" + "\n"
				+ "Total Juegos: " + subTotalJuegos + "\n"
				+ "Impuestos Juegos: " + impuestosJuegos + "\n\n"
				+ "PLATILLOS" + "\n"
				+ "Total Platillos: " +subTotalPlatillos + "\n"
				+ "Impuestos Platillos: " + impuestoPlatillos + "\n\n"
				
				+ "Propinas: " + propinas + "\n"
				+ "Total: " + total + "\n"
				
				);
		
	}
	
	public void revisarVentasMes (Café cafe, LocalDate fecha) {
			
		double total = 0;
		double subTotalJuegos = 0;
		double impuestosJuegos = 0;
		double subTotalPlatillos = 0;
		double impuestoPlatillos = 0;
		double propinas = 0;
			
			
			for(Venta v : cafe.getHistorialVentas()) {
				if (v.getFechaVenta().getMonthValue() == fecha.getMonthValue() && v.getFechaVenta().getYear() == fecha.getYear()) {
					total += v.getTotal();
					propinas += v.getPropina();
					for (Item i : v.getItems()) {
						Producto p = i.getProductoAsociado();
						if( p instanceof JuegoVenta) {
							JuegoVenta jv = (JuegoVenta) p;
							subTotalJuegos += i.getCantidad() * i.getProductoAsociado().getPrecio();
							impuestosJuegos += (i.getCantidad() * i.getProductoAsociado().getPrecio()) * jv.getImpuestoJuego();
						}
						else if (p instanceof Platillos) {
							Platillos pla = (Platillos) p;
							subTotalPlatillos += i.getCantidad() * i.getProductoAsociado().getPrecio();
							impuestoPlatillos += (i.getCantidad() * i.getProductoAsociado().getPrecio()) * pla.getImpuestoPlatillo(); 
							
						}
					}
				}
			}
			
			System.out.println(
					"Mes: "+ fecha.getMonth() + "\n"
					+ "JUEGOS" + "\n"
					+ "Total Juegos: " + subTotalJuegos + "\n"
					+ "Impuestos Juegos: " + impuestosJuegos + "\n\n"
					+ "PLATILLOS" + "\n"
					+ "Total Platillos: " +subTotalPlatillos + "\n"
					+ "Impuestos Platillos: " + impuestoPlatillos + "\n\n"
					
					+ "Propinas: " + propinas + "\n"
					+ "Total: " + total + "\n"
					
					);
			
		}
	
	public void revisarVentasSemana (Café cafe, LocalDate fecha) {
			
			double total = 0;
			double subTotalJuegos = 0;
			double impuestosJuegos = 0;
			double subTotalPlatillos = 0;
			double impuestoPlatillos = 0;
			double propinas = 0;
			
			for(Venta v : cafe.getHistorialVentas()) {
				if (( v.getFechaVenta().equals(fecha) || v.getFechaVenta().isAfter(fecha)) && (v.getFechaVenta().isBefore(fecha.plusDays(6)) || v.getFechaVenta().equals(fecha.plusDays(6)))) {
					total += v.getTotal();
					propinas += v.getPropina();
					for (Item i : v.getItems()) {
						Producto p = i.getProductoAsociado();
						if( p instanceof JuegoVenta) {
							JuegoVenta jv = (JuegoVenta) p;
							subTotalJuegos += i.getCantidad() * i.getProductoAsociado().getPrecio();
							impuestosJuegos += (i.getCantidad() * i.getProductoAsociado().getPrecio()) * jv.getImpuestoJuego();
						}
						else if (p instanceof Platillos) {
							Platillos pla = (Platillos) p;
							subTotalPlatillos += i.getCantidad() * i.getProductoAsociado().getPrecio();
							impuestoPlatillos += (i.getCantidad() * i.getProductoAsociado().getPrecio()) * pla.getImpuestoPlatillo(); 
							
						}
					}
				}
			}
			
			System.out.println(
					"Semana: "+ fecha +" a " + fecha.plusDays(6) + "\n"
					+ "JUEGOS" + "\n"
					+ "Total Juegos: " + subTotalJuegos + "\n"
					+ "Impuestos Juegos: " + impuestosJuegos + "\n\n"
					+ "PLATILLOS" + "\n"
					+ "Total Platillos: " +subTotalPlatillos + "\n"
					+ "Impuestos Platillos: " + impuestoPlatillos + "\n\n"
					
					+ "Propinas: " + propinas + "\n"
					+ "Total: " + total + "\n"
					
					);
			
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
