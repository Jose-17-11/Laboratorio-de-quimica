package controlador.horarios;

import java.sql.SQLIntegrityConstraintViolationException;
import java.time.Duration;
import java.time.LocalTime;

import modelo.PeticionesBD;

public class AsignarHorario {
	
	public String asignar(String matriculaID, String day, String horaEntrada, String horaSalida, String lab) {
		
		PeticionesBD peticion = new PeticionesBD();
		boolean acceso = peticion.verificarDisponibilidadSalon(day, horaEntrada, horaSalida, lab);
		
		LocalTime horaE = LocalTime.parse(horaEntrada);
		LocalTime horaS = LocalTime.parse(horaSalida);
		// Calcula la diferencia entre las horas
		Duration x = Duration.between(horaE, horaS);
		long minutos = x.toMinutes();
		if(minutos < 0) {
			return "La hora de acceso no puede ser despues que la de salida";
		}else if(acceso == true) {
			String asignar = null;
			try {
				asignar = peticion.asignarHorario(matriculaID, day, horaEntrada, horaSalida, lab);
				return asignar;
			} catch (SQLIntegrityConstraintViolationException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}else {
			return "El laboratorio " + lab +" ya se encuentra asignado entre " + horaEntrada  + " - " + horaSalida + " el día " + day + ".";
		}
		
		return "";
	}
}
