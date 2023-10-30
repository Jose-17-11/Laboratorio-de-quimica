package controlador;

public class Errores {
	public boolean datosVacios(String dato) {
		if(dato.equals("")) {
			return false;
		}
		return true;
	}
}
