package controlador;

import java.sql.SQLIntegrityConstraintViolationException;

import modelo.PeticionesBD;

/*********************************************************************************
 * Metodo que genera la pedicion mediante jdbc para registrar acceso de maestros *
 *********************************************************************************/
public class RegistrarAccesoMaestro {
	public boolean registro(String matricula, String salon, String grupo, String materia) throws SQLIntegrityConstraintViolationException {
		PeticionesBD busqueda = new PeticionesBD();
		if(busqueda.autenticacionMaestros(matricula)) {
			busqueda.accesoLaboratorio(matricula, salon, grupo, materia);
			return true;
		}
		return false;
	}
}
