package controlador;

import java.sql.SQLIntegrityConstraintViolationException;

import modelo.PeticionesBD;

/*********************************************************************************
 * Metodo que genera la pedicion mediante jdbc para registrar acceso de maestros
 * *
 *********************************************************************************/
public class RegistrarAccesoMaestro {
	public void registro(String matricula, String salon, int grupo, String materia)
			throws SQLIntegrityConstraintViolationException {
		PeticionesBD acceso = new PeticionesBD();
		acceso.accesoLaboratorio(matricula, salon, grupo, materia);

	}

	public boolean buscarMaestro(String matricula) {
		PeticionesBD busqueda = new PeticionesBD();
		if (busqueda.autenticacionMaestros(matricula)) {
			return true;
		}
		return false;
	}

	public boolean buscarSalon(String salon) throws SQLIntegrityConstraintViolationException {
		PeticionesBD busqueda = new PeticionesBD();
		if (busqueda.peticionSalones(salon)) {
			return true;
		}
		return false;
	}
}
