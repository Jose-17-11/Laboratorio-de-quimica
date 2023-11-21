package controlador;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

import modelo.PeticionesBD;

public class EliminarMaestros {

	/* Metodo que extrae todas las matriculas de los maestros de la base de datos*/
	public static List<String> obtenerMaestros(){
		List<String> matriculas = PeticionesBD.obtenerMaestrosDesdeBaseDeDatos();
		return matriculas;
	}
	
	/* Metodo que elimina el maestro seleccionado, siempre que ya este registrado en la BD*/
	public boolean eliminar(String matricula) throws SQLIntegrityConstraintViolationException {
		PeticionesBD borrar = new PeticionesBD();
		if(borrar.autenticacionMaestros(matricula)) {
			borrar.eliminarMaestro(matricula);	
			return true;
		}
		return false;
	}
}
