package torneos;

import java.io.Serializable;
import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.HashMap;

import articulos.Juego;
import sujetos.UsuarioComprador;

public class Amistoso extends Torneo implements Serializable {

	private static final long serialVersionUID = 1L;

	public Amistoso(int idTorneo, String nombre, DayOfWeek diaSemana, int numParticipantes, Juego juegoAsociado, HashMap<Integer, ArrayList<UsuarioComprador>> inscripciones) {
		super(idTorneo, nombre, diaSemana, numParticipantes, juegoAsociado, inscripciones);
	}
}
