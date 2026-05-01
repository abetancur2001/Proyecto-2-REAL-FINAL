package modelo;

import java.io.Serializable;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import articulos.JuegoPrestamo;
import articulos.JuegoVenta;
import articulos.Platillos;
import sujetos.Cliente;
import sujetos.Cocinero;
import sujetos.Empleado;
import sujetos.Mesero;
import sujetos.Persona;
import sujetos.UsuarioComprador;
import modelo.Reserva;
import modelo.Venta;
import articulos.Item;
import articulos.Juego;
import modelo.Prestamo;
import articulos.RestriccionEdad;
import java.time.LocalDate;
import exceptions.CapacidadExcedidaException;
import exceptions.CambioTurnosException;
import exceptions.JuegoNoDisponibleException;
import exceptions.PrestamoNoPermitidoException;
import exceptions.ReservaNoExitosaException;
import exceptions.VentaNoPermitidaException;
import sujetos.Administrador;

public class Café implements Serializable{

	private int Capacidad;
	private ArrayList<Platillos> menú;
	private ArrayList<Mesa> mesas;
	private ArrayList<Prestamo> historialPrestamos;
	private ArrayList<Empleado> empleados;
	private ArrayList<JuegoVenta> inventarioJuegosVenta;
	private ArrayList<JuegoPrestamo> inventarioJuegosPrestamo;
	private HashMap<Integer, ArrayList<Venta>> historialComprasUsuario;
	private ArrayList<Reserva> reservas;
	private ArrayList<Solicitud> solicitudes;
	private ArrayList<Venta> historialVentas;
	private ArrayList<UsuarioComprador> usuarios;
	private int idReservas = 1;
	private int idSolicitud = 1;

	

	
	
	
	public Café(int capacidad, ArrayList<Platillos> menú, ArrayList<Mesa> mesas, ArrayList<Prestamo> historialPrestamos,
			ArrayList<Empleado> empleados, ArrayList<JuegoVenta> inventarioJuegosVenta,
			ArrayList<JuegoPrestamo> inventarioJuegosPrestamo,
			HashMap<Integer, ArrayList<Venta>> historialComprasUsuario, ArrayList<Reserva> reservas,
			ArrayList<Solicitud> solicitudes, ArrayList<Venta> historialVentas, ArrayList<UsuarioComprador> usuarios,
			int idReservas, int idSolicitud) {
		super();
		Capacidad = capacidad;
		this.menú = menú;
		this.mesas = mesas;
		this.historialPrestamos = historialPrestamos;
		this.empleados = empleados;
		this.inventarioJuegosVenta = inventarioJuegosVenta;
		this.inventarioJuegosPrestamo = inventarioJuegosPrestamo;
		this.historialComprasUsuario = historialComprasUsuario;
		this.reservas = reservas;
		this.solicitudes = solicitudes;
		this.historialVentas = historialVentas;
		this.usuarios = usuarios;
		this.idReservas = idReservas;
		this.idSolicitud = idSolicitud;
	}

	public int getCapacidad() {
		return Capacidad;
	}

	public void setCapacidad(int capacidad) {
		Capacidad = capacidad;
	}
	
	
	

	public ArrayList<UsuarioComprador> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(ArrayList<UsuarioComprador> usuarios) {
		this.usuarios = usuarios;
	}

	public ArrayList<Solicitud> getSolicitudes() {
		return solicitudes;
	}

	public void setSolicitudes(ArrayList<Solicitud> solicitudes) {
		this.solicitudes = solicitudes;
	}

	public int getIdSolicitud() {
		return idSolicitud;
	}

	public void setIdSolicitud(int idSolicitud) {
		this.idSolicitud = idSolicitud;
	}

	public int getIdReservas() {
		return idReservas;
	}

	public void setIdReservas(int idReservas) {
		this.idReservas = idReservas;
	}

	public ArrayList<Platillos> getMenú() {
		return menú;
	}

	public void setMenú(ArrayList<Platillos> menú) {
		this.menú = menú;
	}

	public ArrayList<Mesa> getMesas() {
		return mesas;
	}

	public void setMesas(ArrayList<Mesa> mesas) {
		this.mesas = mesas;
	}

	public ArrayList<Prestamo> getHistorialPrestamos() {
		return historialPrestamos;
	}

	public void setHistorialPrestamos(ArrayList<Prestamo> historialPrestamos) {
		this.historialPrestamos = historialPrestamos;
	}

	public ArrayList<Empleado> getEmpleados() {
		return empleados;
	}

	public void setEmpleados(ArrayList<Empleado> empleados) {
		this.empleados = empleados;
	}

	public ArrayList<JuegoVenta> getInventarioJuegosVenta() {
		return inventarioJuegosVenta;
	}

	public void setInventarioJuegosVenta(ArrayList<JuegoVenta> inventarioJuegosVenta) {
		this.inventarioJuegosVenta = inventarioJuegosVenta;
	}

	public ArrayList<JuegoPrestamo> getInventarioJuegosPrestamo() {
		return inventarioJuegosPrestamo;
	}

	public void setInventarioJuegosPrestamo(ArrayList<JuegoPrestamo> inventarioJuegosPrestamo) {
		this.inventarioJuegosPrestamo = inventarioJuegosPrestamo;
	}

	public HashMap<Integer, ArrayList<Venta>> getHistorialComprasUsuario() {
		return historialComprasUsuario;
	}

	public void setHistorialComprasUsuario(HashMap<Integer, ArrayList<Venta>> historialComprasUsuario) {
		this.historialComprasUsuario = historialComprasUsuario;
	}

	public ArrayList<Reserva> getReservas() {
		return reservas;
	}

	public void setReservas(ArrayList<Reserva> reservas) {
		this.reservas = reservas;
	}

	public ArrayList<Solicitud> getSolicitudesCompletadas() {
		return solicitudes;
	}

	public void setSolicitudesCompletadas(ArrayList<Solicitud> solicitudesCompletadas) {
		this.solicitudes = solicitudesCompletadas;
	}

	public ArrayList<Venta> getHistorialVentas() {
		return historialVentas;
	}

	public void setHistorialVentas(ArrayList<Venta> historialVentas) {
		this.historialVentas = historialVentas;
	}

	public boolean hayCapacidad(int personasNuevas) {
		int personasActuales = 0;
		for (Mesa mesa : mesas) {
			int totalPersonas = mesa.getNumPersonasSentadas();
			personasActuales += totalPersonas;
		}
		return personasActuales + personasNuevas <= Capacidad;
	}

	public boolean estaDisponiblePrestamo(Juego juegoSolicitado) {

		for (JuegoPrestamo jp : inventarioJuegosPrestamo) {

			if (jp.getInfoJuego().equals(juegoSolicitado) && jp.isDisponible()) {
				return true;
			}
		}

		return false;

	}

	public JuegoPrestamo getJuegoDisponiblePrestamo(Juego juegoSolicitado) {

		for (JuegoPrestamo jp : inventarioJuegosPrestamo) {

			if (jp.getInfoJuego().equals(juegoSolicitado) && jp.isDisponible()) {
				return jp;
			}
		}

		return null;

	}

	public JuegoVenta getJuegoDisponibleVenta(Juego juegoSolicitado) {

		JuegoVenta jvh = null;
		
		for (JuegoVenta jv : inventarioJuegosVenta) {
			
			if (jv.getInfoJuegoVenta().equals(juegoSolicitado)) {
				jvh = jv;
			}
		}
		
		if (jvh == null) {
			throw new VentaNoPermitidaException("El juego no tiene stock suficiente");

		}

		return jvh;

	}

	public boolean hayServicio(LocalTime hora, DayOfWeek dia) {
		
		int numMeseros = 0;
		int numCocineros = 0;
		
		for(Empleado emp : empleados) {
			if((hora.equals(emp.getTurnoAsignado().getHoraEntrada()) || hora.isAfter(emp.getTurnoAsignado().getHoraEntrada())) && (hora.equals(emp.getTurnoAsignado().getHoraSalida()) || hora.isBefore(emp.getTurnoAsignado().getHoraSalida()))) {
			for (DayOfWeek day :emp.getTurnoAsignado().getDias()) {
				if(day.equals(dia)) {
					if(emp instanceof Mesero) {
						numMeseros += 1;
					}
					else if(emp instanceof Cocinero) {
						numCocineros += 1;
					}
					
					if (numMeseros >= 2 && numCocineros >= 1) {
						return true;
					}
						
					}
				}
			}
			
		}
		
		return false;
		
	}
	
	public void validarPrestamo (JuegoPrestamo jp) {
		
		boolean hayMesero = false;
		
		if (jp.getInfoJuego().isEsDificil()) {
			for(Empleado emp : empleados) {
				if(emp instanceof Mesero) {
					Mesero m = (Mesero)emp;
					if(m.getListaJuegos().contains(jp.getInfoJuego())) {
						hayMesero = true;
					}
				}
			}
		}
		
		if (!hayMesero) {
			System.out.println("No hay mesero para explicar el juego dificil");
		}
		
	}

	public Prestamo crearPrestamo(LocalDate fechaInicioPrestamo, LocalDate fechaFinPrestamo, Juego juegoSolicitado,
			UsuarioComprador prestadoA, int idPrestamo, Mesa mesa) {

		if (!juegoSolicitado.esCompatibleMesa(mesa)) {
			throw new PrestamoNoPermitidoException("La mesa no cumple las condiciones del juego");
		}

		if ((prestadoA.getJuegosPrestados().size() >= 2)) {
			throw new PrestamoNoPermitidoException("El cliente ya tiene dos prestamos activos");
		}
		
		

		for (JuegoPrestamo jp : inventarioJuegosPrestamo) {
			if ((jp.getInfoJuego().getNombre().equals(juegoSolicitado.getNombre()) && (jp.isDisponible() && (!jp.isDesaparecido()) && (jp.getInfoJuego().getNumJugadores() <= mesa.getCapacidad() ) ))) {
				validarPrestamo(jp);
				
				if(prestadoA instanceof Empleado) {
					Empleado emp = (Empleado) prestadoA;
					Turno turno = emp.getTurnoAsignado();
					DayOfWeek diaPrestamo = fechaInicioPrestamo.getDayOfWeek();

					if( turno.getDias().contains(diaPrestamo) && (turno.getHoraEntrada().equals(turno.getHoraEntrada()) || turno.getHoraEntrada().isAfter(turno.getHoraEntrada())) && (turno.getHoraSalida().equals(turno.getHoraSalida()) || turno.getHoraSalida().isBefore(turno.getHoraSalida()))) {
					        throw new PrestamoNoPermitidoException("El empleado está en turno");
					    }

				}
				
				Prestamo prestamo = new Prestamo(fechaInicioPrestamo, fechaFinPrestamo, jp, prestadoA, idPrestamo);
				jp.setVecesPrestado(jp.getVecesPrestado() + 1);
				prestadoA.getJuegosPrestados().add(prestamo);
				jp.setDisponible(false);
				historialPrestamos.add(prestamo);
				return prestamo;
			}
		}
		throw new JuegoNoDisponibleException("No hay copias disponibles del juego");
	}

	public void quitarPrestamo(Prestamo prestamo) {
		prestamo.getJuegoAPrestar().setDisponible(true);
		prestamo.getPrestadoA().getJuegosPrestados().remove(prestamo);

	}

	public Venta crearVenta(ArrayList<Item> items, UsuarioComprador comprador, LocalDate fechaVenta, Mesa mesa,
			boolean usarPuntos, boolean usaCodigo) {

		Venta ventaAux = new Venta(items, comprador, fechaVenta);

		if (ventaAux.hayAlchoholicas()) {

		    if (comprador.getEdad() < 18) {
		        throw new VentaNoPermitidaException(
		            "Venta no permitida, el comprador es menor de edad"
		        );
		    }

		    if (mesa.hayMenores()) {
		        throw new VentaNoPermitidaException(
		            "Venta no permitida, hay menores en la mesa"
		        );
		    }
		}

		if (ventaAux.hayCalienteVsAccion(mesa)) {
			throw new VentaNoPermitidaException(
					"Venta no permitida, se esta tratando de comprar una bebida caliente y un juego de acción");
		}

		if (ventaAux.hayAlergenoEnVenta(mesa)) {
			System.out.println("Advertencia: hay una persona en la mesa que es alérgica a un platillo pedido");

		}

		ventaAux.setTotal(ventaAux.calculoCuenta(usarPuntos, usaCodigo));
		historialVentas.add(ventaAux);
		comprador.agregarCompra(ventaAux);
		
		if(historialComprasUsuario.containsKey(comprador.getCedula())) {
			historialComprasUsuario.get(comprador.getCedula()).add(ventaAux);
		}
		else{
			ArrayList<Venta> list = new ArrayList<>();
			list.add(ventaAux);
			historialComprasUsuario.put(comprador.getCedula(), list);
		}
		
		return ventaAux;
	}
	
	public Mesa retornarMesaDisponible (int personas) {
		
		Mesa mejorMesa = null;
		
		for(Mesa mesa : mesas) {
			if (mesa.getCapacidad() >= (personas) && !mesa.isOcupada()) {
				if(mejorMesa == null) {
					mejorMesa = mesa;
				}
				else if (mesa.getCapacidad()<mejorMesa.getCapacidad()) {
					mejorMesa = mesa;
				}
			}
		}
		return mejorMesa;
	}
	
	public Reserva crearReserva(LocalDate fechaReserva,
			Cliente reservadoPor, int numPersonas, LocalTime horaReserva) {
		
		Reserva res = null;
		
		DayOfWeek diaReserva = fechaReserva.getDayOfWeek();
		
		if(!hayCapacidad(numPersonas)) {
			throw new ReservaNoExitosaException("Reserva no Exitosa, el café está al máximo de su capacidad");
		}
		
		if(!hayServicio(horaReserva, diaReserva)) {
			throw new ReservaNoExitosaException("Reserva no Exitosa, no hay servicio en la fecha y hora selecionada");
		}
		Mesa mesaDis = retornarMesaDisponible(numPersonas);
		if (mesaDis == null) {
			throw new ReservaNoExitosaException("Reserva no Exitosa, no hay mesa disponible");
		}
		
		int id = idReservas;
		setIdReservas(idReservas + 1);
		mesaDis.setOcupada(true);
		res = new Reserva(id, fechaReserva, mesaDis, reservadoPor, numPersonas,horaReserva);
		reservas.add(res);
		
		return res;
	}
	
	public boolean validarCambioTurno(CambioTurno sol) {
		Turno turno = null;
		LocalTime hora = sol.getNuevoTurno().getHoraEntrada();
		
		for(DayOfWeek day : sol.getNuevoTurno().getDias()) {
			int numMeseros = 0;
			int numCocineros = 0;
			
			for(Empleado emp : empleados) {
				if (emp == sol.getSolicitante()) {
					turno = sol.getNuevoTurno();
				}
				else {
					turno = emp.getTurnoAsignado();
				}
				if (((hora.equals(turno.getHoraEntrada()) || hora.isAfter(turno.getHoraEntrada())) && (hora.equals(turno.getHoraSalida()) || hora.isBefore(turno.getHoraSalida()))) && turno.getDias().contains(day)) {
					if(emp instanceof Mesero) {
						numMeseros += 1;
					}
					else if(emp instanceof Cocinero) {
						numCocineros += 1;
					}
				}
			}
			
			if (numMeseros < 2 || numCocineros < 1) {
				return false;
			}
		}
		
		return true;
	}

	public boolean validarIntercambioTurnos(IntercambioTurno it) {
		
		
		if (it.getSolicitante().getTurnoAsignado() == null || it.getEmpleadoIntercambio().getTurnoAsignado() == null) {
			throw new CambioTurnosException("Intercambio de turno no exitoso, los empleados no tienen turno asignado");
		}
		
		if (it.getEmpleadoIntercambio().equals(it.getSolicitante())) {
			throw new CambioTurnosException("Intercambio de turno no exitoso, no se puede intercambiar el turno de un empleado consigo mismo");
		}
			
			Turno turno = null;
			LocalTime hora = it.getEmpleadoIntercambio().getTurnoAsignado().getHoraEntrada();
			LocalTime hora2 = it.getSolicitante().getTurnoAsignado().getHoraEntrada();			
			
			for(DayOfWeek day : it.getEmpleadoIntercambio().getTurnoAsignado().getDias()) {
				int numMeseros = 0;
				int numCocineros = 0;
				
				for(Empleado emp : empleados) {
					if (emp == it.getSolicitante()) {
					    turno = it.getEmpleadoIntercambio().getTurnoAsignado();
					} else if (emp == it.getEmpleadoIntercambio()) {
					    turno = it.getSolicitante().getTurnoAsignado();
					} else {
					    turno = emp.getTurnoAsignado();
					}
					if (((hora.equals(turno.getHoraEntrada()) || hora.isAfter(turno.getHoraEntrada())) && (hora.equals(turno.getHoraSalida()) || hora.isBefore(turno.getHoraSalida()))) && turno.getDias().contains(day)) {
						if(emp instanceof Mesero) {
							numMeseros += 1;
						}
						else if(emp instanceof Cocinero) {
							numCocineros += 1;
						}
					}
				}
				
				if (numMeseros < 2 || numCocineros < 1) {
					return false;
				}
			}

			for(DayOfWeek day : it.getSolicitante().getTurnoAsignado().getDias()) {
				int numMeseros = 0;
				int numCocineros = 0;
				
				for(Empleado emp : empleados) {
					if (emp == it.getSolicitante()) {
					    turno = it.getEmpleadoIntercambio().getTurnoAsignado();
					} else if (emp == it.getEmpleadoIntercambio()) {
					    turno = it.getSolicitante().getTurnoAsignado();
					} else {
					    turno = emp.getTurnoAsignado();
					}
					if (((hora2.equals(turno.getHoraEntrada()) || hora2.isAfter(turno.getHoraEntrada())) && (hora2.equals(turno.getHoraSalida()) || hora2.isBefore(turno.getHoraSalida()))) && turno.getDias().contains(day)) {
						if(emp instanceof Mesero) {
							numMeseros += 1;
						}
						else if(emp instanceof Cocinero) {
							numCocineros += 1;
						}
					}
				}
				
				if (numMeseros < 2 || numCocineros < 1) {
					return false;
				}
			}
			
			return true;
			
		}
		
	
		public SugerirPlatillo crearSugerenciaPlatillo(Empleado sug, Platillos platillo){
			int id = idSolicitud; 
			LocalDate fecha = LocalDate.now();
			SugerirPlatillo sp = new SugerirPlatillo(id, fecha , EstadoSolicitud.PENDIENTE,sug ,platillo);
			this.setIdSolicitud(getIdSolicitud() + 1);
			solicitudes.add(sp);
			return sp;
		}
		
		public CambioTurno crearCambioTurno(Empleado emp, Turno tur) {
			
			int id = idSolicitud;
			LocalDate fecha = LocalDate.now();
			CambioTurno ct = new CambioTurno(id, fecha, EstadoSolicitud.PENDIENTE, emp, tur);
			this.setIdSolicitud(getIdSolicitud() + 1);
			solicitudes.add(ct);
			return ct;
			
		}
		
		public IntercambioTurno crearIntercambioTurno(Empleado empS, Empleado empI) {
			
			int id = idSolicitud;
			LocalDate fecha = LocalDate.now();
			IntercambioTurno it = new IntercambioTurno(id, fecha, EstadoSolicitud.PENDIENTE, empS, empI);
			this.setIdSolicitud(getIdSolicitud() + 1);
			solicitudes.add(it);
			return it;
			
		}

	}



