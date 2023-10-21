package controlador;

import modelo.PeticionesBD;

public class AccesoAdmin {
	/*************************
	 * Controlador del login *
	 *************************/
	public boolean acceso(String user, String password) {
		PeticionesBD acceso = new PeticionesBD();
		return acceso.autenticacion(user, password);
	}
}
