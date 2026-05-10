package articulos;

import java.io.Serializable;
import java.util.ArrayList;

import sujetos.Cocinero;

public class Pasteleria extends Platillos implements Serializable{

	private static final long serialVersionUID = 1L;

	public Pasteleria(int precio, String nombrePlatillo, ArrayList<Alergenos> alergenos) {
		super(precio, nombrePlatillo, alergenos);
	}

	
	
}
