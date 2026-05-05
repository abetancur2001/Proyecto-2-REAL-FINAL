package articulos;

import java.io.Serializable;
import java.util.ArrayList;

import sujetos.Cocinero;

public class Pasteleria extends Platillos implements Serializable{

	public Pasteleria(int precio, String nombrePlatillo, ArrayList<Alergenos> alergenos) {
		super(precio, nombrePlatillo, alergenos);
	}

	
	
}
