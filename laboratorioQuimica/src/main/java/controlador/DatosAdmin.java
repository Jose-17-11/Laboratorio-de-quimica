package controlador;

import modelo.PeticionesBD;

public class DatosAdmin {
	/***************************************************
	 * Metodos que extraen los datos del administrador *
	 ***************************************************/
	public String nombre() {
		PeticionesBD nombre = new PeticionesBD();
		return nombre.datos(0);
	}

	public String matricula() {
		PeticionesBD nombre = new PeticionesBD();
		return nombre.datos(1);
	}
	
	public String correo() {
		PeticionesBD nombre = new PeticionesBD();
		return nombre.datos(2);
	}
}
