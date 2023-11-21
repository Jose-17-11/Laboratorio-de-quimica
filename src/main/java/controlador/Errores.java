package controlador;

public class Errores {
	/************************************************************************
	 * Metodo que evalua si los datos ingresados son solo datos vacios o no *
	 ************************************************************************/
	public boolean datosVacios(String dato) {
		if(dato.equals("")) {
			return false;
		}
		return true;
	}
}
