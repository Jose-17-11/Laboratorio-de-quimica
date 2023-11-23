package controlador.maestro;

import modelo.PeticionesBD;
import modelo.Profesor;

public class DatosMaestros {
	/***************************************************
	 * Metodos que extraen los datos del Profesor *
	 ***************************************************/
	public Profesor maestro(String usuario) {
		PeticionesBD consulta = new PeticionesBD();
		return consulta.datosM(usuario);
	}
}
