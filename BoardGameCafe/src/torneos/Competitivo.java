package torneos;

import java.io.Serializable;
import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.HashMap;

import articulos.Juego;
import sujetos.UsuarioComprador;

public class Competitivo extends Torneo implements Serializable {
	
	private double tarifaEntrada;
	private static final long serialVersionUID = 1L;
	public double getTarifaEntrada() {
		return tarifaEntrada;
	}

	public void setTarifaEntrada(double tarficaEntrada) {
		this.tarifaEntrada = tarficaEntrada;
	}

	public Competitivo(int idTorneo, String nombre, DayOfWeek diaSemana, int numParticipantes, Juego juegoAsociado, HashMap<Integer, ArrayList<UsuarioComprador>> inscripciones, double tarifaEntrada) {
		super(idTorneo, nombre, diaSemana, numParticipantes, juegoAsociado, inscripciones);
		this.tarifaEntrada = tarifaEntrada;
	}
}
