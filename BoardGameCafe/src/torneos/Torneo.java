package torneos;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.HashMap;

import sujetos.Empleado;
import sujetos.UsuarioComprador;
import articulos.*;
import exceptions.*;

public abstract class Torneo {

	private int idTorneo;
	private DayOfWeek diaSemana;
	private int numParticipantes;
	private Juego juegoAsociado;
	private HashMap<Integer, ArrayList<UsuarioComprador>> inscripciones;

	public int getIdTorneo() {
		return idTorneo;
	}

	public void setIdTorneo(int idTorneo) {
		this.idTorneo = idTorneo;
	}

	public DayOfWeek getDiaSemana() {
		return diaSemana;
	}

	public void setDiaSemana(DayOfWeek diaSemana) {
		this.diaSemana = diaSemana;
	}

	public int getNumParticipantes() {
		return numParticipantes;
	}

	public void setNumParticipantes(int numParticipantes) {
		this.numParticipantes = numParticipantes;
	}

	public Juego getJuegoAsociado() {
		return juegoAsociado;
	}

	public void setJuegoAsociado(Juego juegoAsociado) {
		this.juegoAsociado = juegoAsociado;
	}

	public HashMap<Integer, ArrayList<UsuarioComprador>> getInscripciones() {
		return inscripciones;
	}

	public void setInscripciones(HashMap<Integer, ArrayList<UsuarioComprador>> inscripciones) {
		this.inscripciones = inscripciones;
	}

	public Torneo(int idTorneo, DayOfWeek diaSemana, int numParticipantes, Juego juegoAsociado) {
		super();
		this.idTorneo = idTorneo;
		this.diaSemana = diaSemana;
		this.numParticipantes = numParticipantes;
		this.juegoAsociado = juegoAsociado;
		this.inscripciones = new HashMap<>();
	}

	public int getNumTotalParticipantesActuales() {
		int total = 0;

		for (ArrayList<UsuarioComprador> list : inscripciones.values()) {
			total += list.size();
		}

		return total;
	}

	public int getNumFansActuales() {
		int total = 0;
		for (ArrayList<UsuarioComprador> list : inscripciones.values()) {
			for (UsuarioComprador uc : list) {
				for (Juego j : uc.getJuegosFav()) {
					if (j.equals(juegoAsociado) ) {
						total += 1; 
					}
				}
			}
		}

		return total;

	}

	public int getNumFans(ArrayList<UsuarioComprador> participantes) {

		int total = 0;

		for (UsuarioComprador uc : participantes) {
			for (Juego j : uc.getJuegosFav()) {
				if (j.equals(juegoAsociado)) {
					total += 1;
				}
			}
		}

		return total;
	}

	public boolean esFan(UsuarioComprador uc) {
		for (Juego j : uc.getJuegosFav()) {
			if (j.equals(juegoAsociado)) {
				return true;
			}
		}
		return false;
	}

	public int getNumNormales() {
		return getNumTotalParticipantesActuales() - getNumFansActuales();
	}

	public void validarEmpleados(ArrayList<UsuarioComprador> participantes) {
		for (UsuarioComprador uc : participantes) {
			if (uc instanceof Empleado) {
				Empleado emp = (Empleado) uc;
				if (emp.getTurnoAsignado().getDias().contains(diaSemana)) {
					throw new TorneosException("El empleado tiene turno el dia del torneo");
				}
			}
		}
	}

	public void inscribir(UsuarioComprador participante, ArrayList<UsuarioComprador> participantes) {

		ArrayList<UsuarioComprador> lista = inscripciones.get(participante.getCedula());
		int numFan = (int) Math.ceil(numParticipantes * 0.2);
		int numNormal = numParticipantes - numFan;
		
		

		if (lista == null) {
			if (participantes.size() > 3) {
				throw new TorneosException("La cantidad de participantes a inscribir es mayor a 3");
			}

			lista = new ArrayList<>();
			inscripciones.put(participante.getCedula(), lista);
		}
		
		validarEmpleados(participantes);

		if (getNumTotalParticipantesActuales() + participantes.size() > numParticipantes) {
			throw new TorneosException("La cantidad de usuarios es mayor a la permitida");
		}

		int tam = lista.size() + participantes.size();
		if (tam > 3) {
			throw new TorneosException(
					"La cantidad de usuarios que se quieren inscribir es mayor a la cantidad ya registrada");
		}

		int fansTemp = 0;
		int normalTemp = 0;

		for (UsuarioComprador uc : participantes) {

			if (esFan(uc)) {

				if (getNumFansActuales() + fansTemp < numFan) {
					fansTemp++;
				} else if (getNumNormales() + normalTemp < numNormal) {
					normalTemp++;
				} else {
					throw new TorneosException("No hay cupos para fans");
				}

			} else {

				if (getNumNormales() + normalTemp < numNormal) {
					normalTemp++;
				} else {
					throw new TorneosException("No hay cupo normal");
				}
			}
		}

		lista.addAll(participantes);

	}

	public void desinscribir(UsuarioComprador participante) {
		if (!(inscripciones.containsKey(participante.getCedula()))) {
			throw new TorneosException("El usuario no está inscrito");
		}

		inscripciones.remove(participante.getCedula());
	}
	
	public void darPremioDescuento(ArrayList<UsuarioComprador> participantes) {
		
		for (UsuarioComprador uc : participantes) {
			uc.setDescuentosDisponibles(0.15);
		}
		
	}
	
	public void darPremioDinero(ArrayList<UsuarioComprador> participantes) {
		
		double din = 0.0;
		
		if (this instanceof Competitivo) {
			Competitivo com = (Competitivo) this;
			din = getNumTotalParticipantesActuales() * com.getTarifaEntrada();
		}
		
		for (UsuarioComprador uc : participantes) {
			uc.setPuntosFidelidad(uc.getPuntosFidelidad() + din);
		}
		
	}



}
