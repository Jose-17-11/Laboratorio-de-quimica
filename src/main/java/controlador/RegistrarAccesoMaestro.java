package controlador;

import java.sql.SQLIntegrityConstraintViolationException;

import modelo.PeticionesBD;

/*********************************************************************************
 * Metodo que genera la pedicion mediante jdbc para registrar acceso de maestros
 * *
 *********************************************************************************/
public class RegistrarAccesoMaestro {
	/* Metodo que registra el acceso del maestro y sus datos a un laboratorio*/
	public void registro(String matricula, String salon, int grupo, String materia, String carrera)
			throws SQLIntegrityConstraintViolationException {
		PeticionesBD acceso = new PeticionesBD();
		acceso.accesoLaboratorio(matricula, salon, grupo, materia, carrera);

	}
	/* Metodo que busca si el maestro tiene el permiso de acceso al laboratorio*/
	public boolean buscarMaestro(String matricula) {
		PeticionesBD busqueda = new PeticionesBD();
		if (busqueda.autenticacionMaestros(matricula)) {
			return true;
		}
		return false;
	}
	
	/* Metodo que busca si el salon al que se accedera esta libre o no*/
	public boolean buscarSalon(String salon, int i) throws SQLIntegrityConstraintViolationException {
		PeticionesBD busqueda = new PeticionesBD();
		boolean active = busqueda.peticionSalones(salon);
		if (active || i == 1) {
			return true;
		} else {
		return false;
		}
	}
}
