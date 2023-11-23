package controlador;

import java.sql.SQLIntegrityConstraintViolationException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Locale;

import modelo.PeticionesBD;

/*********************************************************************************
 * Metodo que genera la pedicion mediante jdbc para registrar acceso de maestros
 * *
 *********************************************************************************/
public class RegistrarAccesoMaestro {
	/* Metodo que registra el acceso del maestro y sus datos a un laboratorio*/
	public void registro(String maestro, String salon, int grupo, String materia, String carrera)
			throws SQLIntegrityConstraintViolationException {
		PeticionesBD acceso = new PeticionesBD();
		acceso.accesoLaboratorio(maestro, salon, grupo, materia, carrera);
	}
	
//	Verifica si el maestro tiene laboratorio asignado en la hora actual
	public boolean acceso(String matricula, String salon) {
		String dia = obtenerDia();
		PeticionesBD permiso = new PeticionesBD();
		boolean decision = permiso.verificarAccesoMaestro(matricula, dia, salon);
		if(decision) {
			return true;
		}
		return false;
	}
	
//	Se obtiene el dia de la semana que sea hoy
	public String obtenerDia() {
		// Obtener la fecha actual
		LocalDate fechaActual = LocalDate.now();

		// Obtener el día de la semana actual
		DayOfWeek diaDeLaSemana = fechaActual.getDayOfWeek();

		// Imprimir el nombre del día de la semana actual en español
		String nombreDia = diaDeLaSemana.getDisplayName(java.time.format.TextStyle.FULL, // Estilo completo (puedes
																							// cambiarlo según tus
																							// preferencias)
				new Locale("es", "ES") // Locale para español de España
		);

		nombreDia = nombreDia.toUpperCase();

		return nombreDia;
	}
}
