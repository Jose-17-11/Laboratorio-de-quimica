package controlador;

import modelo.Administrador;
import modelo.PeticionesBD;

public class DatosAdmin {
	/***************************************************
	 * Metodos que extraen los datos del administrador *
	 ***************************************************/
	public Administrador admin() {
		PeticionesBD consulta = new PeticionesBD();
		return consulta.datos();
	}
}
