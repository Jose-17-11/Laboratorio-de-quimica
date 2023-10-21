package controlador;

import java.sql.SQLIntegrityConstraintViolationException;

import modelo.PeticionesBD;

public class AgregarNuevoMaestro {

	/************************************************************************
	 * Metodo que agrega nuevo maestro para que tenga acceso al laboratorio *
	 ************************************************************************/
	public boolean registroNuevoMaestro(String matricula, String nombre, String apellido) throws SQLIntegrityConstraintViolationException {
		PeticionesBD nuevoMaestro = new PeticionesBD();
		if(!nuevoMaestro.autenticacionMaestros(matricula)) {
			nuevoMaestro.nuevoMaestro(matricula, nombre, apellido);
			return true;
		}
		return false;
	}
}
