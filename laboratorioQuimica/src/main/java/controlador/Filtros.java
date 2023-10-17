package controlador;

import conexion_jdbc.Peticiones;

public class Filtros {
	public String nombreMatricula(boolean option) {
		Peticiones nombreId = new Peticiones();
		if(option == true) {
			//Devuelve el nombre
			return nombreId.datos(option);
		}
		//Devuelve la matricula
		return nombreId.datos(option);
	}
}
