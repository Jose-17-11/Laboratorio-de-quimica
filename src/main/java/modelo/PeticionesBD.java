package modelo;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.sql.Time;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PeticionesBD {

	private static ConexionBD conexion;

	// Conexion a la base de datos
	public PeticionesBD() {
		conexion = new ConexionBD();
	}

	/******************************
	 * Metodo que valida el login *
	 ******************************/
	public boolean autenticacion(String username, String password) {
		Connection cn = null;
		Statement stm = null;
		ResultSet rs = null;

		try {
			cn = conexion.conectar();
			stm = cn.createStatement();
			rs = stm.executeQuery("SELECT * FROM admin");

			while (rs.next()) {
				String user = rs.getString(2);
				String pass = rs.getString(3);
				if (user.equalsIgnoreCase(username) && pass.equals(password))
					return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			conexion.desconectar(cn, stm, rs);
		}
		return false;
	}

	public boolean autenticacionMaestro(String username, String password) {
		Connection cn = null;
		Statement stm = null;
		ResultSet rs = null;

		try {
			cn = conexion.conectar();
			stm = cn.createStatement();
			rs = stm.executeQuery("SELECT * FROM maestros");

			while (rs.next()) {
				String user = rs.getString(1);
				String pass = rs.getString(4);
				if (user.equalsIgnoreCase(username) && pass.equals(password))
					return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			conexion.desconectar(cn, stm, rs);
		}
		return false;
	}
	
	/*************************************************
	 * Metodo que extrae los datos del administrador *
	 *************************************************/
	public Administrador datos() {
		Connection cn = null;
		Statement stm = null;
		ResultSet rs = null;

		try {
			cn = conexion.conectar();
			stm = cn.createStatement();
			rs = stm.executeQuery("SELECT * FROM admin");

			if (rs.next()) {
				int matricula = rs.getInt(1);
				String nombre = rs.getString(4);
				String apellido = rs.getString(5);
				String correo = rs.getString(6);
				return new Administrador(matricula, nombre, apellido, correo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			conexion.desconectar(cn, stm, rs);
		}
		return null;
	}
//	Obtiene todos los datos del maestro que a ingresado
	public Profesor datosM(String matricula) {
		Connection cn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		cn = conexion.conectar();
		try {
			// Utilizar una consulta parametrizada
			String consulta = "SELECT * FROM maestros WHERE matricula = ?";
			pstmt = cn.prepareStatement(consulta);

			// Establecer el valor para el marcador de posición
			pstmt.setString(1, matricula);

			// Ejecutar la consulta
			rs = pstmt.executeQuery();

			while (rs.next()) {
				String matriculaID = rs.getString(1);
				String nombre = rs.getString(2);
				String apellidos = rs.getString(3);
				return new Profesor(matriculaID, nombre, apellidos);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// Cerrar recursos
			conexion.desconectar(cn, pstmt, rs);
		}
		return null;
	}
	
	// Metodo que verifica si el maestro que intente acceder al laboratorio esta
	// registrado en el horario
	public String peticionCredenciales(String matricula) {
		Connection cn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String password = null;
		cn = conexion.conectar();
		try {
			// Utilizar una consulta parametrizada
			String consulta = "SELECT * FROM maestros WHERE matricula = ?";
			pstmt = cn.prepareStatement(consulta);

			// Establecer el valor para el marcador de posición
			pstmt.setString(1, matricula);

			// Ejecutar la consulta
			rs = pstmt.executeQuery();

			while (rs.next()) {
				password = rs.getString(4);
				return password;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// Cerrar recursos
			conexion.desconectar(cn, pstmt, rs);
		}
		return "";

	}

	// Metodo que verifica si el maestro que intente acceder al laboratorio esta
	// registrado en el horario
	public boolean verificarAccesoMaestro(String matricula, String dia, String salon) {
		Connection cn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Time entrada = null;
		Time salida = null;
		boolean decision = false;
		try {
			cn = conexion.conectar();

			// Utilizar una consulta parametrizada
			String consulta = "SELECT * FROM horarios WHERE matricula_maestro = ? AND dia_semana = ?";
			pstmt = cn.prepareStatement(consulta);

			// Establecer el valor para el marcador de posición
			pstmt.setString(1, matricula);
			pstmt.setString(2, dia);

			// Ejecutar la consulta
			rs = pstmt.executeQuery();

			while (rs.next()) {
				entrada = rs.getTime(4);
				salida = rs.getTime(5);
				String lab = rs.getString(6);

				LocalTime horaE = entrada.toLocalTime();
				LocalTime horaS = salida.toLocalTime();
				LocalTime horaActual = LocalTime.now();

				// Calcula la diferencia entre las horas
				Duration x = Duration.between(horaE, horaS);
				Duration y = Duration.between(horaActual, horaS);
				System.out.println("Hora de entrada: " + horaE);
				System.out.println("Hora de salida: " + horaS);
				System.out.println("Hora actual: " + horaActual);
				long minutosX = x.toMinutes();
				long h = y.toMinutes();
				System.out.println(minutosX);
				System.out.println(h);
				System.out.println(salon);
				System.out.println(lab);
				if (h < minutosX && h > 0 && salon.equalsIgnoreCase(lab)) {
					decision = true;
					break;
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// Cerrar recursos
			conexion.desconectar(cn, pstmt, rs);
		}

		return decision;
	}

	/*********************************************************
	 * Metodo que agrega un nuevo maestro a la base de datos *
	 *********************************************************/
	public String accesoLaboratorio(String maestro, String salon, int grupo, String materia, String carrera)
			throws SQLIntegrityConstraintViolationException {
		Connection cn = null;
		Statement stm = null;
		ResultSet rs = null;
		try {
			cn = conexion.conectar();
			stm = cn.createStatement();
			rs = stm.executeQuery("SELECT * FROM accesos");
			String consulta = "INSERT INTO accesos (fecha, hora, maestro, salon, grupo, materia, carrera) VALUES (?, ?, ?, ?, ?, ?, ?)";
			LocalDateTime locaDate = LocalDateTime.now();
			Date fechaSQL = Date.valueOf(locaDate.toLocalDate());
			Time horaSQL = Time.valueOf(locaDate.toLocalTime());

			PreparedStatement pstmt = cn.prepareStatement(consulta);
			pstmt.setDate(1, fechaSQL);
			pstmt.setTime(2, horaSQL);
			pstmt.setString(3, maestro);
			pstmt.setString(4, salon);
			pstmt.setInt(5, grupo);
			pstmt.setString(6, materia);
			pstmt.setString(7, carrera);

			int filasAfectadas = pstmt.executeUpdate();
			if (filasAfectadas > 0) {
				return ("Maestro agregado con éxito.");
			} else {
				return ("No se pudo agregar el profesor.");
			}

		} catch (SQLIntegrityConstraintViolationException e) {
			conexion.desconectar(cn, stm, rs);
			return ("El maestro que intenta ingresar ya se encuentra registrado.");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			conexion.desconectar(cn, stm, rs);
		}
		return "";
	}

//	Se obtienen los accesos a los laboratorios
	public List<String[]> accesos(int i) {
		Connection cn = null;
		Statement stm = null;
		ResultSet rs = null;

		try {
			cn = conexion.conectar();
			stm = cn.createStatement();
			if (i == 1) {
				rs = stm.executeQuery(
						"SELECT * FROM `accesos` WHERE fecha BETWEEN DATE_FORMAT(CURDATE(), '%Y-%m-01') AND LAST_DAY(CURDATE())");
			} else if (i == 2) {
				rs = stm.executeQuery("SELECT * " + "FROM accesos " + "WHERE fecha BETWEEN "
						+ "DATE_ADD(CURDATE(), INTERVAL 0 - WEEKDAY(CURDATE()) DAY) "
						+ "AND DATE_ADD(CURDATE(), INTERVAL 4 - WEEKDAY(CURDATE()) DAY)");
			} else if (i == 3) {
				rs = stm.executeQuery("SELECT * " + "FROM accesos " + "WHERE DATE(fecha) = CURDATE()");
			} else {
				rs = stm.executeQuery("SELECT * FROM accesos");
			}

			List<String[]> listaDatos = new ArrayList<>();
			while (rs.next()) {
				String fecha = rs.getString(1);
				String hora = rs.getString(2);
				String maestro = rs.getString(3);
				String salon = rs.getString(4);
				String grupo = rs.getString(5);
				String materia = rs.getString(6);
				String carrera = rs.getString(7);

				String tabla[] = { fecha, hora, maestro, salon, grupo, materia, carrera };

				// Agregar el array tabla a la lista
				listaDatos.add(tabla);
			}
			return listaDatos;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			conexion.desconectar(cn, stm, rs);
		}
		return null;
	}

	/**********************************************************************************
	 * Metodo que busca si un maestro esta en la base de datos en base a su
	 * matricula *
	 **********************************************************************************/
	public boolean autenticacionMaestros(String matricula) {
		Connection cn = null;
		Statement stm = null;
		ResultSet rs = null;

		try {
			cn = conexion.conectar();
			stm = cn.createStatement();
			rs = stm.executeQuery("SELECT * FROM maestros");

			while (rs.next()) {
				String user = rs.getString(1);
				if (user.equalsIgnoreCase(matricula))
					return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			conexion.desconectar(cn, stm, rs);
		}
		return false;
	}

	/*********************************************************
	 * Metodo que agrega un nuevo maestro a la base de datos *
	 *********************************************************/
	public String nuevoMaestro(String matricula, String nombre, String apellido)
			throws SQLIntegrityConstraintViolationException {
		Connection cn = null;
		Statement stm = null;
		ResultSet rs = null;
		try {
			cn = conexion.conectar();
			stm = cn.createStatement();
			rs = stm.executeQuery("SELECT * FROM maestros");
			String consulta = "INSERT INTO maestros (matricula, nombre, apellido, password) VALUES (?, ?, ?,?)";

			Random random = new Random();
			int numero = random.nextInt(100); // Número aleatorio entre 0 y 99
			String password = matricula + numero;

			PreparedStatement pstmt = cn.prepareStatement(consulta);
			pstmt.setString(1, matricula);
			pstmt.setString(2, nombre);
			pstmt.setString(3, apellido);
			pstmt.setString(4, password);

			int filasAfectadas = pstmt.executeUpdate();
			if (filasAfectadas > 0) {
				return ("Maestro agregado con éxito.");
			} else {
				return ("No se pudo agregar el profesor.");
			}

		} catch (SQLIntegrityConstraintViolationException e) {
			conexion.desconectar(cn, stm, rs);
			return ("El maestro que intenta ingresar ya se encuentra registrado.");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			conexion.desconectar(cn, stm, rs);
		}
		return "";
	}

	/*****************************************************
	 * Metodo que elimina un maestro de la base de datos *
	 *****************************************************/
	public String eliminarMaestro(String matricula) throws SQLIntegrityConstraintViolationException {
		Connection cn = null;
		PreparedStatement pstmtEliminarMaestro = null;
		PreparedStatement pstmtEliminarHorarios = null;

		try {
			cn = conexion.conectar();
			cn.setAutoCommit(false); // Desactivar la confirmación automática

			// Eliminar horarios asociados al maestro
			String consultaEliminarHorarios = "DELETE FROM horarios WHERE matricula_maestro = ?";
			pstmtEliminarHorarios = cn.prepareStatement(consultaEliminarHorarios);
			pstmtEliminarHorarios.setString(1, matricula);
			pstmtEliminarHorarios.executeUpdate();

			// Eliminar al maestro
			String consultaEliminarMaestro = "DELETE FROM maestros WHERE matricula = ?";
			pstmtEliminarMaestro = cn.prepareStatement(consultaEliminarMaestro);
			pstmtEliminarMaestro.setString(1, matricula);
			int filasAfectadas = pstmtEliminarMaestro.executeUpdate();

			// Confirmar la transacción
			cn.commit();

			if (filasAfectadas > 0) {
				return "Maestro y sus horarios eliminados con éxito.";
			} else {
				return "No se pudo encontrar al maestro con la matrícula proporcionada.";
			}

		} catch (SQLException e) {
			try {
				if (cn != null) {
					cn.rollback(); // Deshacer la transacción en caso de error
				}
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
			e.printStackTrace();
			return "Error al eliminar al maestro.";
		} finally {
			try {
				if (cn != null) {
					cn.setAutoCommit(true); // Restaurar la confirmación automática
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			conexion.desconectar(cn, null, null); // Cerrar las conexiones
		}
	}

	/***********************************************************************
	 * Metodo que extrae todas las matriculas de los maestros en una lista *
	 ***********************************************************************/
	public static List<String> obtenerMaestrosDesdeBaseDeDatos() {
		List<String> maestros = new ArrayList<>();
		Connection cn = null;
		Statement stm = null;
		ResultSet rs = null;

		try {
			cn = conexion.conectar();
			stm = cn.createStatement();
			rs = stm.executeQuery("SELECT * FROM maestros");

			while (rs.next()) {
				String matricula = rs.getString(1);
				maestros.add(matricula);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			conexion.desconectar(cn, stm, rs);
		}

		return maestros;
	}

	public List<String[]> obtenerHorarios() {
		Connection cn = null;
		Statement stm = null;
		ResultSet rs = null;

		try {
			cn = conexion.conectar();
			stm = cn.createStatement();
			rs = stm.executeQuery(
					"SELECT * FROM `horarios` ORDER BY FIELD(dia_semana, 'Lunes', 'Martes', 'Miércoles', 'Jueves', 'Viernes'), hora_inicio");

			List<String[]> listaDatos = new ArrayList<>();
			while (rs.next()) {
				String matriculaMaestro = rs.getString("matricula_maestro");
				String diaSemana = rs.getString("dia_semana");
				String horaInicio = rs.getString("hora_inicio");
				String horaFin = rs.getString("hora_fin");
				String salon = rs.getString("salon");

				String[] tabla = { matriculaMaestro, diaSemana, horaInicio, horaFin, salon };

				// Agregar el array tabla a la lista
				listaDatos.add(tabla);
			}
			return listaDatos;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			conexion.desconectar(cn, stm, rs);
		}
		return null;
	}

	public String asignarHorario(String matricula, String diaSemana, String horaInicio, String horaFin, String salon)
			throws SQLIntegrityConstraintViolationException {
		Connection cn = null;
		Statement stm = null;
		ResultSet rs = null;

		try {
			cn = conexion.conectar();
			stm = cn.createStatement();
			rs = stm.executeQuery("SELECT * FROM horarios");
			String consulta = "INSERT INTO horarios (matricula_maestro, dia_semana, hora_inicio, hora_fin, salon) VALUES (?, ?, ?, ?, ?)";

			PreparedStatement pstmt = cn.prepareStatement(consulta);
			pstmt.setString(1, matricula);
			pstmt.setString(2, diaSemana);
			pstmt.setString(3, horaInicio);
			pstmt.setString(4, horaFin);
			pstmt.setString(5, salon);

			int filasAfectadas = pstmt.executeUpdate();
			if (filasAfectadas > 0) {
				return ("Laboratorio " + salon + " asignado de " + horaInicio + " a " + horaFin + " con éxito.");
			} else {
				return ("No se pudo asignar el salon.");
			}

		} catch (SQLIntegrityConstraintViolationException e) {
			conexion.desconectar(cn, stm, rs);
			return ("El maestro que intenta ingresar ya se encuentra registrado.");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			conexion.desconectar(cn, stm, rs);
		}
		return "";
	}

	/*******************************************************************************************
	 * Metodo que verifica si un salon ya esta registrado en un dia y rango de hora
	 * especifico *
	 *******************************************************************************************/
	public boolean verificarDisponibilidadSalon(String day, String horaEntrada, String horaSalida, String lab) {
		Connection cn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Time entrada = null;
		Time salida = null;
		boolean decision = true;
		try {
			cn = conexion.conectar();

			// Utilizar una consulta parametrizada
			String consulta = "SELECT * FROM horarios WHERE dia_semana = ? AND salon = ?";
			pstmt = cn.prepareStatement(consulta);

			// Establecer el valor para el marcador de posición
			pstmt.setString(1, day);
			pstmt.setString(2, lab);

			// Ejecutar la consulta
			rs = pstmt.executeQuery();

			while (rs.next()) {
				String dia = rs.getString(3);
				entrada = rs.getTime(4);
				salida = rs.getTime(5);
				String salon = rs.getString(6);

				LocalTime horaE = entrada.toLocalTime();
				LocalTime horaS = salida.toLocalTime();

				LocalTime horaEParametro = LocalTime.parse(horaEntrada);
				LocalTime horaSParametro = LocalTime.parse(horaSalida);
				// Calcula la diferencia entre las horas
				Duration x = Duration.between(horaE, horaS);
				Duration y = Duration.between(horaEParametro, horaS);
				Duration z = Duration.between(horaSParametro, horaS);
				long minutosX = x.toMinutes();
				long minutosY = y.toMinutes();
				long minutosZ = z.toMinutes();
				if (minutosY < 0 && minutosZ > minutosX) {
					decision = false;
					break;
				} else if (minutosY > 0 && minutosZ < minutosX) {
					decision = false;
					break;
				} else if ((minutosY > minutosX || minutosY <= 0) && (minutosZ >= minutosX || minutosZ < 0)) {
					decision = true;
				} else {
					decision = false;
					break;
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// Cerrar recursos
			conexion.desconectar(cn, pstmt, rs);
		}

		return decision;
	}

}