package controlador;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

import modelo.PeticionesBD;

public class EliminarMaestros {

	public static List<String> obtenerMaestros(){
		List<String> matriculas = PeticionesBD.obtenerMaestrosDesdeBaseDeDatos();
		return matriculas;
		
	}
	
	public boolean eliminar(String matricula) throws SQLIntegrityConstraintViolationException {
		PeticionesBD borrar = new PeticionesBD();
		if(borrar.autenticacionMaestros(matricula)) {
			borrar.eliminarMaestro(matricula);	
			return true;
		}
		return false;
	}
}
