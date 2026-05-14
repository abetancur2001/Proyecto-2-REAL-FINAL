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

	private static final long serialVersionUID = 1L;
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
	private HashMap<String, Cliente> mapaClientes;
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

	public Café(int capacidad, ArrayList<Platillos> menú, ArrayList<Mesa> mesas, ArrayList<Prestamo> historialPrestamos, ArrayList<Empleado> empleados, ArrayList<JuegoVenta> inventarioJuegosVenta, ArrayList<JuegoPrestamo> inventarioJuegosPrestamo, HashMap<Integer, ArrayList<Venta>> historialComprasUsuario, ArrayList<Reserva> reservas, ArrayList<Solicitud> solicitudes, ArrayList<Venta> historialVentas, ArrayList<UsuarioComprador> usuarios, ArrayList<Cliente> clientes, ArrayList<Torneo> torneos, HashMap<String, Cliente> mapaClientes, HashMap<String, Empleado> mapaEmpleados, ArrayList<Juego> catalogoJuegos, Administrador admin, HashMap<Integer, ArrayList<Reserva>> mapaReservas, int idReservas, int idSolicitud, int idTorneos, int idMesa, int idPrestamo) {
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

	public HashMap<String, Cliente> getMapaClientes() {
		return mapaClientes;
	}

	public void setMapaClientes(HashMap<String, Cliente> mapaClientes) {
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

		if (!jp.getInfoJuego().isEsDificil()) {
			return;
		}

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
					"Año: "+ jv.getInfoJuegoVenta().getAnio() + "\n"+
					"Empresa: " + jv.getInfoJuegoVenta().getEmpresa() + "\n"+
					"Cantidad de Jugadores: " + jv.getInfoJuegoVenta().getCantidadJugadores() + "\n"+
					"¿Es Dificil?: "+ jv.getInfoJuegoVenta().isEsDificil() + "\n"+
					"Es Apto para: " + jv.getInfoJuegoVenta().getApto() + "\n"+
					"Tipo: " + jv.getInfoJuegoVenta().getTipo() + "\n"+
					"Precio: " + jv.getPrecio() + "\n" +
					"Stock: " + jv.getStock() + "\n"

			);
			i += 1;
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

			System.out.println("ADVERTANCIA: hay una persona alergica en la mesa a "+ ventaAux.hayAlergenoEnVenta(mesa));

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
	
	public Torneo crearTorneo(DayOfWeek diaSemana, String nombre, int numParticipantes, Juego juegoAsociado, double tarifaEntrada, boolean esCompetitivo) {
		
		Torneo torneo;
		int id = idTorneos;
		setIdTorneos(id + 1);
		
		if (numParticipantes > contarJugadoresJuegoPrestamo(juegoAsociado)) {
			throw new TorneosException("No hay la suficientes cantidad de copias del juego");
		}
		
		if (esCompetitivo) {
			
			torneo = new Competitivo(id, nombre, diaSemana, numParticipantes, juegoAsociado,new HashMap<>(), tarifaEntrada);
		}
		else {
			torneo = new Amistoso(id, nombre,  diaSemana, numParticipantes, juegoAsociado, new HashMap<>());
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

		if(historialPrestamos.isEmpty()){
			System.out.println("No hay juegos prestados");
			return;
		}

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
					"Año: "+ j.getAnio() + "\n"+
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
			return;
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
				Cliente c = mapaClientes.get(login);
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

	public void mostrarTorneos(){

		int i = 0;


		for(Torneo t : torneos){

			if(t instanceof Competitivo){

				Competitivo comp = (Competitivo) t;

				System.out.println(
						"Opcion: "+i+"\n"+
								"Nombre: " + t.getNombre() +"\n" +
								"Dia: " + t.getDiaSemana() +"\n" +
								"Numero de jugadores: " + t.getNumParticipantes() +"\n"+
								"Juego: " + t.getJuegoAsociado().getNombre() +"\n" +
								"Precio Entrada: " + ((Competitivo) t).getTarifaEntrada()
				);
			}

			System.out.println(
					"Opcion: "+i+"\n"+
					"Nombre: " + t.getNombre() +"\n" +
					 "Dia: " + t.getDiaSemana() +"\n" +
					"Numero de jugadores: " + t.getNumParticipantes() +"\n"+
					"Juego: " + t.getJuegoAsociado().getNombre() +"\n"

			);
			i += 1;
		}
	}

	public ArrayList<Torneo> mostrarTorneosCliente(UsuarioComprador uc){
		ArrayList<Torneo> tor = new ArrayList<>();

		for(Torneo t : getTorneos()){
			if(t.getInscripciones().containsKey(uc.getCedula())){
				tor.add(t);
			}
		}

		if (tor.isEmpty()) {
			throw new TorneosException("El cliente no tiene inscripciones en torneos");
		}

		int i = 0;
		for(Torneo t : tor){
			System.out.println(
					"Opcion: "+i+"\n"+
							"Nombre: " + t.getNombre() +"\n" +
							"Dia: " + t.getDiaSemana() +"\n" +
							"Numero de jugadores: " + t.getNumParticipantes() +"\n"+
							"Juego: " + t.getJuegoAsociado().getNombre()
			);
			i += 1;
		}

		return tor;

	}




	public void inicializarDatos() {
		if (!this.catalogoJuegos.isEmpty()) return;

		Juego juego1 = new Juego("Catan", 1995, "Kosmos", 4, false,
				RestriccionEdad.TODAS_EDADES, TiposJuegos.TABLERO);

		Juego juego2 = new Juego("Street Fighter", 2020, "Capcom", 4, false,
				RestriccionEdad.SOLO_ADULTOS, TiposJuegos.ACCION);

		Juego juego3 = new Juego("Mi Primer Juego", 2018, "Ravensburger", 2, false,
				RestriccionEdad.APTO_5ANIOS, TiposJuegos.TABLERO);

		Juego juego4 = new Juego("Twilight Imperium", 2017, "FFG", 6, true,
				RestriccionEdad.TODAS_EDADES, TiposJuegos.TABLERO);

		Juego juego5 = new Juego("Uno", 1971, "Mattel", 6, false,
				RestriccionEdad.TODAS_EDADES, TiposJuegos.CARTAS);

		this.catalogoJuegos.add(juego1);
		this.catalogoJuegos.add(juego2);
		this.catalogoJuegos.add(juego3);
		this.catalogoJuegos.add(juego4);
		this.catalogoJuegos.add(juego5);

		JuegoPrestamo prestamo1 = new JuegoPrestamo(true, false, EstadoJuego.NUEVO, juego1, 0);
		JuegoPrestamo prestamo2 = new JuegoPrestamo(true, false, EstadoJuego.DESGASTADO, juego2, 3);
		JuegoPrestamo prestamo3 = new JuegoPrestamo(false, false, EstadoJuego.DANADO, juego4, 10);

		this.inventarioJuegosPrestamo.add(prestamo1);
		this.inventarioJuegosPrestamo.add(prestamo2);
		this.inventarioJuegosPrestamo.add(prestamo3);

		JuegoVenta Jventa1 = new JuegoVenta(45000, 5, juego1);
		JuegoVenta Jventa2 = new JuegoVenta(30000, 8, juego5);
		JuegoVenta Jventa3 = new JuegoVenta(120000, 2, juego4);

		this.inventarioJuegosVenta.add(Jventa1);
		this.inventarioJuegosVenta.add(Jventa2);
		this.inventarioJuegosVenta.add(Jventa3);

		ArrayList<Alergenos> alergenosGluten = new ArrayList<>();
		alergenosGluten.add(Alergenos.GLUTEN);

		ArrayList<Alergenos> alergenosLacteos = new ArrayList<>();
		alergenosLacteos.add(Alergenos.LACTEOS);

		ArrayList<Alergenos> sinAlergenos = new ArrayList<>();

		Pasteleria croissant = new Pasteleria(8000, "Croissant", alergenosGluten);
		Pasteleria cheesecake = new Pasteleria(10000, "Cheesecake", alergenosLacteos);
		Bebida cafeCaliente = new Bebida(5000, "Café Americano", sinAlergenos, false, true);
		Bebida cervezaArtesanal = new Bebida(12000, "Cerveza Artesanal", sinAlergenos, true, false);

		this.menú.add(croissant);
		this.menú.add(cheesecake);
		this.menú.add(cafeCaliente);
		this.menú.add(cervezaArtesanal);

		ArrayList<DayOfWeek> diasTurno1 = new ArrayList<>();
		ArrayList<DayOfWeek> diasTurno2 = new ArrayList<>();
		ArrayList<DayOfWeek> diasTurno3 = new ArrayList<>();

		int a = 1;
		while (a <= 7) {
			diasTurno1.add(DayOfWeek.of(a));
			diasTurno2.add(DayOfWeek.of(a));
			diasTurno3.add(DayOfWeek.of(a));
			a += 1;
		}

		Turno turnoCompleto1 = new Turno(diasTurno1, LocalTime.of(8, 0), LocalTime.of(16, 0));
		Turno turnoCompleto2 = new Turno(diasTurno2, LocalTime.of(12, 0), LocalTime.of(20, 0));
		Turno turnoCocinero  = new Turno(diasTurno3, LocalTime.of(7, 0),  LocalTime.of(15, 0));

		Mesero mesero1 = new Mesero("Carlos López", 28, 1001, new ArrayList<>(), 1234, "carlos.lopez",
				new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), 0, 0,
				turnoCompleto1, new ArrayList<>());

		Mesero mesero2 = new Mesero("Ana Martínez", 25, 1002, new ArrayList<>(), 5678, "ana.martinez",
				new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), 0, 0,
				turnoCompleto2, new ArrayList<>());

		Cocinero cocinero1 = new Cocinero("Pedro Gómez", 35, 1003, new ArrayList<>(), 9012, "pedro.gomez",
				new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), 0, 0,
				turnoCocinero);

		this.empleados.add(mesero1);
		this.empleados.add(mesero2);
		this.empleados.add(cocinero1);
		this.mapaEmpleados.put(mesero1.getLogin(), mesero1);
		this.mapaEmpleados.put(mesero2.getLogin(), mesero2);
		this.mapaEmpleados.put(cocinero1.getLogin(), cocinero1);

		ArrayList<Juego> favsClienteA = new ArrayList<>();
		favsClienteA.add(juego1);

		ArrayList<Alergenos> alergenosClienteA = new ArrayList<>();
		alergenosClienteA.add(Alergenos.GLUTEN);

		Cliente clienteA = new Cliente("Sofía Ramírez", 22, 2001, favsClienteA, 1111, "sofia.ramirez",
				new ArrayList<>(), new ArrayList<>(), alergenosClienteA, 150.0, 0.0);

		Cliente clienteB = new Cliente("Tomás Herrera", 16, 2002, new ArrayList<>(), 2222, "tomas.herrera",
				new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), 9999.0, 50.0);

		this.clientes.add(clienteA);
		this.clientes.add(clienteB);
		this.usuarios.add(clienteA);
		this.usuarios.add(clienteB);
		this.mapaClientes.put(clienteA.getLogin(), clienteA);
		this.mapaClientes.put(clienteB.getLogin(), clienteB);

		Mesa mesa1 = new Mesa(false, 10, new ArrayList<>(), false, 2);
		Mesa mesa2 = new Mesa(false, 11, new ArrayList<>(), false, 4);
		Mesa mesa3 = new Mesa(false, 12, new ArrayList<>(), false, 10);

		this.mesas.add(mesa1);
		this.mesas.add(mesa2);
		this.mesas.add(mesa3);

		Amistoso torneoAmistoso = new Amistoso(this.idTorneos +=1 , "Torneo Amistoso Catan",
				DayOfWeek.MONDAY, 8, juego1, new HashMap<>());

		Competitivo torneoCompetitivo = new Competitivo(this.idTorneos += 1, "Torneo Competitivo Street Fighter",
				DayOfWeek.MONDAY, 16, juego2, new HashMap<>(), 20000.0);

		this.torneos.add(torneoAmistoso);
		this.torneos.add(torneoCompetitivo);


		Reserva reserva1 = new Reserva(this.idReservas++, LocalDate.now().plusDays(1),
				mesa1, clienteA, 2, LocalTime.of(14, 0));

		Reserva reserva2 = new Reserva(this.idReservas++, LocalDate.now().plusDays(2),
				mesa2, clienteB, 4, LocalTime.of(16, 0));

		Reserva reserva3 = new Reserva(this.idReservas++, LocalDate.now().plusDays(3),
				mesa3, clienteA, 8, LocalTime.of(18, 0));

		Reserva reservaHoy = new Reserva(this.idReservas++, LocalDate.now(),
				mesa1, clienteA, 2, LocalTime.of(13, 0));


		this.reservas.add(reserva1);
		this.reservas.add(reserva2);
		this.reservas.add(reserva3);
		this.reservas.add(reservaHoy);

		this.mapaReservas.computeIfAbsent(clienteA.getCedula(), k -> new ArrayList<>()).add(reserva1);
		this.mapaReservas.computeIfAbsent(clienteB.getCedula(), k -> new ArrayList<>()).add(reserva2);
		this.mapaReservas.computeIfAbsent(clienteA.getCedula(), k -> new ArrayList<>()).add(reserva3);
		this.mapaReservas.computeIfAbsent(clienteA.getCedula(), k -> new ArrayList<>()).add(reservaHoy);


		Item item1 = new Item(2, croissant);
		Item item2 = new Item(1, cafeCaliente);
		Item item3 = new Item(1, cervezaArtesanal);
		Item item4 = new Item(1, cheesecake);

		ArrayList<Item> itemsVenta1 = new ArrayList<>();
		itemsVenta1.add(item1);
		itemsVenta1.add(item2);

		ArrayList<Item> itemsVenta2 = new ArrayList<>();
		itemsVenta2.add(item3);
		itemsVenta2.add(item4);

		ArrayList<Item> itemsVenta3 = new ArrayList<>();
		itemsVenta3.add(new Item(1, croissant));
		itemsVenta3.add(new Item(2, cervezaArtesanal));

		Venta venta1 = new Venta(itemsVenta1, clienteA, LocalDate.now().minusDays(3));
		Venta venta2 = new Venta(itemsVenta2, clienteB, LocalDate.now().minusDays(1));
		Venta venta3 = new Venta(itemsVenta3, clienteA, LocalDate.now());

		venta1.calculoCuenta(false, false);
		venta2.calculoCuenta(false, false);
		venta3.calculoCuenta(false, false);

		this.historialVentas.add(venta1);
		this.historialVentas.add(venta2);
		this.historialVentas.add(venta3);

		this.historialComprasUsuario.computeIfAbsent(clienteA.getCedula(), k -> new ArrayList<>()).add(venta1);
		this.historialComprasUsuario.computeIfAbsent(clienteA.getCedula(), k -> new ArrayList<>()).add(venta3);
		this.historialComprasUsuario.computeIfAbsent(clienteB.getCedula(), k -> new ArrayList<>()).add(venta2);

//		Prestamo prestamo_1 = new Prestamo(
//				LocalDate.now().minusDays(2),
//				LocalDate.now().plusDays(5),
//				prestamo1, clienteA, this.idPrestamo++
//		);
//		prestamo1.setDisponible(false);
//		clienteA.getJuegosPrestados().add(prestamo_1);
//		this.historialPrestamos.add(prestamo_1);
//
//		Prestamo prestamo_2 = new Prestamo(
//				LocalDate.now().minusDays(1),
//				LocalDate.now().plusDays(6),
//				prestamo2, clienteB, this.idPrestamo++
//		);
//		prestamo2.setDisponible(false);
//		clienteB.getJuegosPrestados().add(prestamo_2);
//		this.historialPrestamos.add(prestamo_2);

	}

	}



