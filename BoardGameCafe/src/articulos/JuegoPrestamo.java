package articulos;

import java.io.Serializable;

public class JuegoPrestamo implements Serializable{
	private boolean disponible;
	private boolean desaparecido;
	private EstadoJuego estado;
	private Juego infoJuego;
	private int vecesPrestado;
	
	public boolean isDisponible() {
		return disponible;
	}
	public void setDisponible(boolean disponible) {
		this.disponible = disponible;
	}
	public boolean isDesaparecido() {
		return desaparecido;
	}
	public void setDesaparecido(boolean desaparecido) {
		this.desaparecido = desaparecido;
	}
	public EstadoJuego getEstado() {
		return estado;
	}
	public void setEstado(EstadoJuego estado) {
		this.estado = estado;
	}
	public Juego getInfoJuego() {
		return infoJuego;
	}
	
	
	
	
	public int getVecesPrestado() {
		return vecesPrestado;
	}
	public void setVecesPrestado(int vecesPrestado) {
		this.vecesPrestado = vecesPrestado;
	}
	public JuegoPrestamo(boolean disponible, boolean desaparecido, EstadoJuego estado, Juego infoJuego, int vecesPrestado) {
		super();
		this.disponible = disponible;
		this.desaparecido = desaparecido;
		this.estado = estado;
		this.infoJuego = infoJuego;
		this.vecesPrestado = vecesPrestado;
	}
	public boolean esAptoMenores (Juego infoJuego) {
		
		if (infoJuego.getApto() == RestriccionEdad.APTO_MENORES) {
			return true;
		}
		return false;
	}
	
	
	
	public boolean esAptoTodasEdades (Juego infoJuego) {
			
			if (infoJuego.getApto() == RestriccionEdad.TODAS_EDADES) {
				return true;
			}
			return false;
	}
	
	public boolean esAptoSoloAdultos (Juego infoJuego) {
			
			if (infoJuego.getApto() == RestriccionEdad.SOLO_ADULTOS) {
				return true;
			}
			return false;
		}

	


}
