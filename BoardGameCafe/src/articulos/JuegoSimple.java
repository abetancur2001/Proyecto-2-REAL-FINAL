package articulos;

import java.io.Serializable;

public class JuegoSimple extends Juego implements Serializable{

	public JuegoSimple(String nombre, int anio, String empresa, int cantidadJugadores, boolean esDificil,
			RestriccionEdad apto, TiposJuegos tipo, int numJugadores) {
		super(nombre, anio, empresa, cantidadJugadores, esDificil, apto, tipo, numJugadores);
	}

    
}