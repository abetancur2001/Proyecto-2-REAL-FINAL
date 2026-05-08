package modelo;

import java.io.Serializable;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;

import articulos.*;
import exceptions.*;
import sujetos.Cliente;
import sujetos.Cocinero;
import sujetos.Empleado;
import sujetos.Mesero;
import sujetos.UsuarioComprador;

import sujetos.Administrador;
import torneos.*;

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
	private ArrayList<Cliente> clientes;
	private ArrayList<Torneo> torneos;
	private HashMap<String, UsuarioComprador> mapaClientes;
	private HashMap<String, Empleado> mapaEmpleados;
	private ArrayList<Juego> catalogoJuegos;
	private Administrador admin;
	private HashMap<Integer, ArrayList<Reserva>> mapaReservas;
	private int idReservas = 1;
	private int idSolicitud = 1;
	private int idTorneos = 1;
	private int idMesa = 9;
	private int idPrestamo = 0;

	public int getIdPrestamo() {
		setIdPrestamo(idPrestamo + 1);
		return idPrestamo;
	}

	public void setIdPrestamo(int idPrestamo) {
		this.idPrestamo = idPrestamo;
	}

	public Café(int capacidad, ArrayList<Platillos> menú, ArrayList<Mesa> mesas, ArrayList<Prestamo> historialPrestamos, ArrayList<Empleado> empleados, ArrayList<JuegoVenta> inventarioJuegosVenta, ArrayList<JuegoPrestamo> inventarioJuegosPrestamo, HashMap<Integer, ArrayList<Venta>> historialComprasUsuario, ArrayList<Reserva> reservas, ArrayList<Solicitud> solicitudes, ArrayList<Venta> historialVentas, ArrayList<UsuarioComprador> usuarios, ArrayList<Cliente> clientes, ArrayList<Torneo> torneos, HashMap<String, UsuarioComprador> mapaClientes, HashMap<String, Empleado> mapaEmpleados, ArrayList<Juego> catalogoJuegos, Administrador admin, HashMap<Integer, ArrayList<Reserva>> mapaReservas, int idReservas, int idSolicitud, int idTorneos, int idMesa, int idPrestamo) {
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
		this.clientes = clientes;
		this.torneos = torneos;
		this.mapaClientes = mapaClientes;
		this.mapaEmpleados = mapaEmpleados;
		this.catalogoJuegos = catalogoJuegos;
		this.admin = admin;
		this.mapaReservas = mapaReservas;
		this.idReservas = idReservas;
		this.idSolicitud = idSolicitud;
		this.idTorneos = idTorneos;
		this.idMesa = idMesa;
		this.idPrestamo = idPrestamo;
	}

	public int getCapacidad() {
		return Capacidad;
	}

	public void setCapacidad(int capacidad) {
		Capacidad = capacidad;
	}

	public ArrayList<Cliente> getClientes() {
		return clientes;
	}

	public int getIdMesa() {

		setIdMesa(idMesa + 1);
		return idMesa;
	}



	public HashMap<Integer, ArrayList<Reserva>> getMapaReservas() {
		return mapaReservas;
	}

	public void setMapaReservas(HashMap<Integer, ArrayList<Reserva>> mapaReservas) {
		this.mapaReservas = mapaReservas;
	}

	public void setIdMesa(int idMesa) {
		this.idMesa = idMesa;
	}

	public void setClientes(ArrayList<Cliente> clientes) {
		this.clientes = clientes;
	}

	public ArrayList<Juego> getCatalogoJuegos() {
		return catalogoJuegos;
	}

	public void setCatalogoJuegos(ArrayList<Juego> catalogoJuegos) {
		this.catalogoJuegos = catalogoJuegos;
	}

	public HashMap<String, UsuarioComprador> getMapaClientes() {
		return mapaClientes;
	}

	public void setMapaClientes(HashMap<String, UsuarioComprador> mapaClientes) {
		this.mapaClientes = mapaClientes;
	}

	public HashMap<String, Empleado> getMapaEmpleados() {
		return mapaEmpleados;
	}

	public void setMapaEmpleados(HashMap<String, Empleado> mapaEmpleados) {
		this.mapaEmpleados = mapaEmpleados;
	}

	public Administrador getAdmin() {
		return admin;
	}

	public void setAdmin(Administrador admin) {
		this.admin = admin;
	}

	public int getIdTorneos() {
		return idTorneos;
	}

	public void setIdTorneos(int idTorneos) {
		this.idTorneos = idTorneos;
	}

	public ArrayList<Torneo> getTorneos() {
		return torneos;
	}

	public void setTorneos(ArrayList<Torneo> torneos) {
		this.torneos = torneos;
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

	public void mostrarJuegosVenta(){
		int i = 0;
		for(JuegoVenta jv : inventarioJuegosVenta) {
			System.out.println("Opcion: "+ i + "\n"+
					"Nombre: "+ jv.getInfoJuegoVenta().getNombre() + "\n"+
					"Año: "+ jv.getInfoJuegoVenta().getNombre() + "\n"+
					"Empresa: " + jv.getInfoJuegoVenta().getEmpresa() + "\n"+
					"Cantidad de Jugadores: " + jv.getInfoJuegoVenta().getCantidadJugadores() + "\n"+
					"¿Es Dificil?: "+ jv.getInfoJuegoVenta().isEsDificil() + "\n"+
					"Es Apto para: " + jv.getInfoJuegoVenta().getApto() + "\n"+
					"Tipo: " + jv.getInfoJuegoVenta().getTipo() + "\n"
			);

		}
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

		if (ventaAux.hayAlergenoEnVenta(mesa) != null) {

			throw new VentaNoPermitidaException("ADVERTANCIA: hay una persona alergica en la mesa a "+ ventaAux.hayAlergenoEnVenta(mesa));

		}

		if (usaCodigo && comprador.getDescuentosDisponibles() <= 0){
			throw new VentaNoPermitidaException("No hay descuentos disponibles");
		}
		else if(usaCodigo && comprador.getDescuentosDisponibles() > 0){
			comprador.setDescuentosDisponibles(comprador.getDescuentosDisponibles() - 1);
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

		if (!mapaReservas.containsKey(reservadoPor.getCedula())) {
			mapaReservas.put(reservadoPor.getCedula(), new ArrayList<>());
		}
		mapaReservas.get(reservadoPor.getCedula()).add(res);
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
			throw new SolicitudException("Intercambio de turno no exitoso, los empleados no tienen turno asignado");
		}
		
		if (it.getEmpleadoIntercambio().equals(it.getSolicitante())) {
			throw new SolicitudException("Intercambio de turno no exitoso, no se puede intercambiar el turno de un empleado consigo mismo");
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
	
	public int contarJugadoresJuegoPrestamo(Juego j) {
		int total = 0;
		
		for (JuegoPrestamo jp : inventarioJuegosPrestamo) {
			if (jp.getInfoJuego().equals(j) && jp.isDisponible() && !jp.isDesaparecido() ) {
				total += jp.getInfoJuego().getCantidadJugadores();
			}
		}
		
		return total;
	}
	
	public Torneo crearTorneo(DayOfWeek diaSemana, int numParticipantes, Juego juegoAsociado, double tarifaEntrada, boolean esCompetitivo) {
		
		Torneo torneo;
		int id = idTorneos;
		setIdTorneos(id + 1);
		
		if (numParticipantes > contarJugadoresJuegoPrestamo(juegoAsociado)) {
			throw new TorneosException("No hay la suficientes cantidad de copias del juego");
		}
		
		if (esCompetitivo) {
			
			torneo = new Competitivo(id, diaSemana, numParticipantes, juegoAsociado, tarifaEntrada);
		}
		else {
			torneo = new Amistoso(id, diaSemana, numParticipantes, juegoAsociado);
		}
		
		torneos.add(torneo);
		return torneo;
		
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

	public void revisarJuegos(){
		for (Prestamo p:getHistorialPrestamos()) {
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

	public void añadirPlatillo(Platillos p) {
		getMenú().add(p);
	}

	public void añadirJuegoPrestamo (JuegoPrestamo jp) {
		getInventarioJuegosPrestamo().add(jp);
	}

	public void añadirJuegoVenta (JuegoVenta jv) {
		getInventarioJuegosVenta().add(jv);
	}

	public void añadirJuego(Juego j){getCatalogoJuegos().add(j); }



	public void mostrarInventarioJuegosPrestamo(){

		int i = 0;

		if(getInventarioJuegosPrestamo().isEmpty()){
			throw new MostrarException("No hay Juegos en el inventario de Prestamo");
		}

		for(JuegoPrestamo j : inventarioJuegosPrestamo){
			System.out.println("Opcion: "+ i + "\n"+
					"Nombre: "+ j.getInfoJuego().getNombre() + "\n"+
					"Año: "+ j.getInfoJuego().getNombre() + "\n"+
					"Empresa: " + j.getInfoJuego().getEmpresa() + "\n"+
					"Cantidad de Jugadores: " + j.getInfoJuego().getCantidadJugadores() + "\n"+
					"¿Es Dificil?: "+ j.getInfoJuego().isEsDificil() + "\n"+
					"Es Apto para: " + j.getInfoJuego().getApto() + "\n"+
					"Tipo: " + j.getInfoJuego().getTipo() + "\n"+
					"¿Está Disponible?: " + j.isDisponible() +"\n"+
					"¿Está Desaprecido?: " + j.isDesaparecido() +"\n"+
					"Estado: "+ j.getEstado() +"\n"+
					"Veces Prestado: " + j.getVecesPrestado()+"\n"
			);

			i += 1;
		}

	}

	public void mostrarInventarioJuegosVenta(){
		int i =0;
		if (getInventarioJuegosVenta().isEmpty()){
			throw new MostrarException("No hay Juegos en el inventario de Venta");
		}

		for(JuegoVenta j : inventarioJuegosVenta){
			System.out.println("Opcion: "+ i + "\n"+
					"Nombre: "+ j.getInfoJuegoVenta().getNombre() + "\n"+
					"Año: "+ j.getInfoJuegoVenta().getNombre() + "\n"+
					"Empresa: " + j.getInfoJuegoVenta().getEmpresa() + "\n"+
					"Cantidad de Jugadores: " + j.getInfoJuegoVenta().getCantidadJugadores() + "\n"+
					"¿Es Dificil?: "+ j.getInfoJuegoVenta().isEsDificil() + "\n"+
					"Es Apto para: " + j.getInfoJuegoVenta().getApto() + "\n"+
					"Tipo: " + j.getInfoJuegoVenta().getTipo() + "\n"+
					"Precio: " + j.getPrecio() +"\n"+
					"Stock: " + j.getStock() +"\n"
			);

			i += 1;
		}
	}

	public void mostrarCatalogoJuegos(){
		int i = 0;

		if(getCatalogoJuegos().isEmpty()){
			throw new MostrarException("No hay Juegos en el catalogo");
		}

		for(Juego j : catalogoJuegos){
			System.out.println("Opcion: "+ i + "\n"+
					"Nombre: "+ j.getNombre() + "\n"+
					"Año: "+ j.getNombre() + "\n"+
					"Empresa: " + j.getEmpresa() + "\n"+
					"Cantidad de Jugadores: " + j.getCantidadJugadores() + "\n"+
					"¿Es Dificil?: "+ j.isEsDificil() + "\n"+
					"Es Apto para: " + j.getApto() + "\n"+
					"Tipo: " + j.getTipo() + "\n"
			);

			i += 1;
		}
	}

	public void moverVentaAPrestamo (JuegoVenta jv) {

		boolean bandera = false;

		for (JuegoVenta jve :getInventarioJuegosVenta()) {
			if(jve.equals(jv)) {
				bandera = true;
			}
		}

		if(bandera) {

			if(jv.getStock() == 0){
				getInventarioJuegosVenta().remove(jv);
			}
			else if(jv.getStock() != 0){
				jv.setStock(jv.getStock()-1);
			}

			JuegoPrestamo jp = new JuegoPrestamo(true, false, EstadoJuego.NUEVO, jv.getInfoJuegoVenta(), 0);
			getInventarioJuegosPrestamo().add(jp);
		}
		else {
			throw new JuegoNoEncontradoException("Juego no encontrado en el inventario de juegos para la venta");
		}

	}

	public void repararJuegoPrestamo (JuegoPrestamo jp) {
		JuegoVenta juegoReemplazante = null;
		if(getInventarioJuegosPrestamo().contains(jp) && (jp.getEstado() == EstadoJuego.DANADO || jp.getEstado() == EstadoJuego.INCOMPLETO || jp.getEstado() == EstadoJuego.DESGASTADO)) {
			for(JuegoVenta jv :getInventarioJuegosVenta()) {
				if(jv.getInfoJuegoVenta().equals(jp.getInfoJuego())) {
					juegoReemplazante = jv;
					break;
				}
			}

			if (juegoReemplazante == null) {
				throw new JuegoNoEncontradoException("Juego reemplazante no encontrado");
			}

			if(juegoReemplazante.getStock() == 0){
				getInventarioJuegosVenta().remove(juegoReemplazante);
			}
			else if(juegoReemplazante.getStock() != 0){
				juegoReemplazante.setStock(juegoReemplazante.getStock() - 1);
			}

			jp.setEstado(EstadoJuego.NUEVO);
			jp.setDisponible(true);
			jp.setDesaparecido(false);

		}
	}

	public void revisarVentasDia (LocalDate fecha) {

		double total = 0;
		double subTotalJuegos = 0;
		double impuestosJuegos = 0;
		double subTotalPlatillos = 0;
		double impuestoPlatillos = 0;
		double propinas = 0;

		for(Venta v : getHistorialVentas()) {
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

	public void revisarVentasMes (LocalDate fecha) {

		double total = 0;
		double subTotalJuegos = 0;
		double impuestosJuegos = 0;
		double subTotalPlatillos = 0;
		double impuestoPlatillos = 0;
		double propinas = 0;


		for(Venta v : getHistorialVentas()) {
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

	public void revisarVentasSemana (LocalDate fecha) {

		double total = 0;
		double subTotalJuegos = 0;
		double impuestosJuegos = 0;
		double subTotalPlatillos = 0;
		double impuestoPlatillos = 0;
		double propinas = 0;

		for(Venta v : getHistorialVentas()) {
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

	public void mostrarComprasXUsuario(int cedula){

		mostrarVentas(getHistorialComprasUsuario().get(cedula));

	}

		public void addEmpleado(Empleado emp) {

			if(empleados.contains(emp)) {
				throw new AddSujetoException("El empleado ya existe");
			}
			else{
				empleados.add(emp);
			}

			if (mapaEmpleados.containsKey(emp.getLogin())) {
				throw new AddSujetoException("El empleado ya existe");
			}
			else{
				mapaEmpleados.put(emp.getLogin(), emp);
			}

		}

		public void addCliente(Cliente c) {

			if (clientes.contains(c)) {
				throw new AddSujetoException("El cliente ya existe");
			} else {
				clientes.add(c);
			}

			if (mapaClientes.containsKey(c.getLogin())) {
				throw new AddSujetoException("El cliente ya existe");
			} else {
				mapaClientes.put(c.getLogin(), c);
			}

		}

		public String mostrarItems(ArrayList<Item> it){
			String ans = "";
			for(Item i: it){
				ans += "- "+ "Producto Asociado: "+ i.getProductoAsociado() +"\n"+
						"Cantidad: " + i.getCantidad() +"\n";
			}

			return ans;
		}

		public void mostrarVentas(ArrayList<Venta> venta){

		if(venta == null || venta.isEmpty()){
			System.out.println("No hay ventas para la cedula ingresada");

		}

			for(Venta v : venta){
				System.out.println(
						"Comprador: " + v.getComprador().getNombre() +"\n"+
						"Fecha: " + v.getFechaVenta()+"\n"+
						"Items: " + mostrarItems(v.getItems()) +"\n"+
						"Total: " + v.getTotal() +"\n"+
						"Impuestos: " + v.getImpuestos() +"\n"+
						"Subtotal: " + v.getSubtotal()+"\n"+
						"Propina: " + v.getPropina() +"\n"
				);
			}

		}

		public int getCapacidadMesas(){

			int total = 0;

			for(Mesa m : mesas){
				total += m.getCapacidad();
			}

			return total;
		}

		public Mesa addMesa(int cap){

			if (getCapacidadMesas() == getCapacidad()){
				throw new CapacidadExcedidaException("No es posible añadir una mesa, no hay capacidad");
			}


			Mesa m = new Mesa(false,getIdMesa(), new ArrayList<Cliente>(),false, cap);


			mesas.add(m);

			return m;
		}

		public Empleado autenticarEmpleado(String login, int pass){

			if (mapaEmpleados.containsKey(login)){
				Empleado e = (Empleado) mapaEmpleados.get(login);
				if (e.getPassword() == pass){
					return e;
				}
			}

			return null;
		}

		public Cliente autenticarCliente(String login, int pass){

			if (mapaClientes.containsKey(login)){
				Cliente c = (Cliente) mapaClientes.get(login);
				if (c.getPassword() == pass){
					return c;
				}
			}

			return null;

		}

		public Administrador autenticarAdmin(String login, int pass){

			if (admin.getLogin().equals(login) && admin.getPassword() == pass ){
				return admin;
			}

			return null;
		}

	public void cambiarTurno (Turno tur, Empleado empl) {
		Turno turno = null;

		for(DayOfWeek day : tur.getDias()) {
			int numMeseros = 0;
			int numCocineros = 0;

			for(Empleado emp : getEmpleados()) {
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
				throw new SolicitudException("No hay el personal necesarios para hacer el cambio");
			}
		}

		empl.cambioTurno(tur);
	}

	public void aprobarSolcitudesPendientes (){
		if(solicitudes.isEmpty()){
			throw new SolicitudException("No hay solicitudes pendientes");
		}

		for(Solicitud s : solicitudes){
			if(s.getEstado().equals(EstadoSolicitud.PENDIENTE)){
				aprobarSolicitud(s);
			}
		}

	}

	public void mostrarSolicitudes(){
		for(Solicitud s : solicitudes){
			if(s.getEstado().equals(EstadoSolicitud.PENDIENTE)){
				if(s instanceof CambioTurno || s instanceof IntercambioTurno || s instanceof SugerirPlatillo){
					System.out.println(
							"ID: "+s.getIdSolicitud() +"\n"+
							"Fecha: "+s.getFechaSolicitud()+"\n"+
							"Estado: "+s.getEstado()+"\n"+
							"Solicitante: "+s.getSolicitante().getNombre()+"\n"
					);
				}
			}
		}
	}

	public void aprobarSolicitud (Solicitud sol) {
		if(sol instanceof CambioTurno) {
			CambioTurno ct = (CambioTurno)sol;
			if(validarCambioTurno(ct)) {
				ct.cambioTurnoEmp();
				ct.setEstado(EstadoSolicitud.APROBADA);
			}
			else {
				ct.setEstado(EstadoSolicitud.RECHAZADA);
			}
		}
		else if(sol instanceof IntercambioTurno) {
			IntercambioTurno it = (IntercambioTurno)sol;
			if(validarIntercambioTurnos(it)) {
				it.intercambiarTurnos();
				it.setEstado(EstadoSolicitud.APROBADA);
			}
			else {
				it.setEstado(EstadoSolicitud.RECHAZADA);
			}
		}
		else if(sol instanceof SugerirPlatillo) {
			SugerirPlatillo sp = (SugerirPlatillo) sol;
			añadirPlatillo(sp.getPlatilloSugerido());
			sp.setEstado(EstadoSolicitud.APROBADA);
		}
	}

	public String tipoEmpleado(Empleado emp){

		String total = "";

		if(emp instanceof Mesero){
			total += "Mesero";
		}
		else if(emp instanceof Cocinero){
			total += "Cocinero";
		}

		return total;
	}

	public String mostrarDias(Empleado emp){

		String total = "";

		for(DayOfWeek dia : emp.getTurnoAsignado().getDias()){
			total += " "+dia;
		}

		return total;
	}

	public void mostrarEmpleados(){
		int i = 0;

		for(Empleado emp : empleados){
			System.out.println(
					"Opcion: "+i+"\n"+
					"Nombre: "+emp.getNombre()+"\n"+
					"Rol: "+ tipoEmpleado(emp) +"\n"+
					"Dias: "+ mostrarDias(emp) +"\n"+
					"Hora Entrada: "+ emp.getTurnoAsignado().getHoraEntrada() +"\n"+
					"Hora Salida: "+ emp.getTurnoAsignado().getHoraSalida() +"\n"
			);

		}
	}

	public  void revisarPrestamos(){
		for(Prestamo p : historialPrestamos){
			if(marcarJuegoDesparecido(p)){
				System.out.println("Se marco el juego como desaparecido al juego: "+"\n"+
				"Nombre: "+p.getJuegoAPrestar().getInfoJuego().getNombre()+"\n"+
				"Fecha de inicio: "+p.getFechaInicioPrestamo()+"\n"+
				"Fecha de fin: "+p.getFechaFinPrestamo()+"\n"
				);
			}
		}
	}

	public boolean marcarJuegoDesparecido(Prestamo prestamo) {

		long dias = ChronoUnit.DAYS.between(prestamo.getFechaInicioPrestamo(), LocalDate.now());

		if (dias > 7) {
			prestamo.getJuegoAPrestar().setDesaparecido(true);
			return true;
		}
		return false;
	}

	public void mostrarMenu(){

		int i = 0;

		for(Platillos p : menú){

			String tipo = null;

			if(p instanceof Bebida){
				tipo = "Bebida";
			}
			else if(p instanceof Pasteleria){
				tipo = "Pasteleria";
			}

			System.out.println(
					"Opcion: "+i+"\n"+
					"Nombre: "+p.getNombrePlatillo()+"\n"+
					"Tipo: "+tipo+"\n"+
					"Precio: "+p.getPrecio()+"\n"
			);

			i+=1;


		}

	}




		public void inicializarDatos(){
			if(admin == null){
				admin = new Administrador("Andres", 34, 123456, new ArrayList<Juego>(), 4545, "adminPro" );
			}

			if (mesas.size() == 0 ){
				Mesa mesa1 = new Mesa(false, 1, new ArrayList<Cliente>(), false, 4);
				Mesa mesa2 = new Mesa(false, 2, new ArrayList<Cliente>(), false, 4);
				mesas.add(mesa1);
				mesas.add(mesa2);

			}
		}

	}



