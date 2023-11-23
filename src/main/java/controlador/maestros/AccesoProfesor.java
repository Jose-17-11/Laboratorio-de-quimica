package controlador.maestros;

import modelo.PeticionesBD;

public class AccesoProfesor {
	public boolean acceso(String user, String password) {
		PeticionesBD acceso = new PeticionesBD();
		return acceso.autenticacionMaestro(user, password);
	}
}
