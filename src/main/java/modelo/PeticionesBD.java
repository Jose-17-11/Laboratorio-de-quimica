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

	public List<String[]> accesos(int i) {
		Connection cn = null;
		Statement stm = null;
		ResultSet rs = null;

		try {
			cn = conexion.conectar();
			stm = cn.createStatement();
			if(i == 1) {
				rs = stm.executeQuery("SELECT * FROM `accesos` WHERE fecha BETWEEN DATE_FORMAT(CURDATE(), '%Y-%m-01') AND LAST_DAY(CURDATE())");				
			}else if(i == 2){
				rs = stm.executeQuery("SELECT * " +
                        "FROM accesos " +
                        "WHERE fecha BETWEEN " +
                        "DATE_ADD(CURDATE(), INTERVAL 0 - WEEKDAY(CURDATE()) DAY) " + 
                        "AND DATE_ADD(CURDATE(), INTERVAL 4 - WEEKDAY(CURDATE()) DAY)");				
			}else if(i == 3) {
				rs = stm.executeQuery("SELECT * " +
                        "FROM accesos " +
                        "WHERE DATE(fecha) = CURDATE()");				
			}else {
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

	/************************************************
	 * Metodo que verifica si el salon esta ocupado *
	 ************************************************/
	public boolean peticionSalones(String salon) throws SQLIntegrityConstraintViolationException {

		Connection cn = null;
		Statement stm = null;
		ResultSet rs = null;

		try {
			cn = conexion.conectar();
			stm = cn.createStatement();
			rs = stm.executeQuery("SELECT * FROM accesos WHERE fecha = CURDATE();");
			int i = 0;
			LocalDateTime locaDate = LocalDateTime.now();
			Time horaSQL = Time.valueOf(locaDate.toLocalTime());
			while (rs.next()) {
				Time hora = rs.getTime(2);
				LocalTime horaBD = hora.toLocalTime();
				LocalTime horaDB = horaSQL.toLocalTime();
				// Calcula la diferencia entre las horas
				Duration duration = Duration.between(horaBD, horaDB);

				long minutos = duration.toMinutes();

				String laboratorio = rs.getString(4);
				if (laboratorio.equals(salon) && minutos <= 50) {
					i++;
				}
			}
			if (i == 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			conexion.desconectar(cn, stm, rs);
		}
		return false;
	}

	/************************************************************
	 * Metodo que registra el acceso de maestros al laboratorio *
	 ************************************************************/
	public String accesoLaboratorio(String matricula, String salon, int grupo, String materia, String carrera)
			throws SQLIntegrityConstraintViolationException {

		Connection cn = null;
		Statement stm = null;
		ResultSet rs = null;

		try {
			cn = conexion.conectar();
			stm = cn.createStatement();

			String consulta = "INSERT INTO accesos (fecha, hora, maestro, salon, grupo, materia, carrera) VALUES (?, ?, ?, ?, ?, ?, ?)";
			// Crear un objeto PreparedStatement
			PreparedStatement pstmt = cn.prepareStatement(consulta);
			// En el objeto se ingresan los atributos del nuevo maestro
			LocalDateTime locaDate = LocalDateTime.now();
			Date fechaSQL = Date.valueOf(locaDate.toLocalDate());
			Time horaSQL = Time.valueOf(locaDate.toLocalTime());
			pstmt.setDate(1, fechaSQL);
			pstmt.setTime(2, horaSQL);
			pstmt.setString(3, matricula);
			pstmt.setString(4, salon);
			pstmt.setInt(5, grupo);
			pstmt.setString(6, materia);
			pstmt.setString(7, carrera);

			// Ejecutar la consulta
			int filasAfectadas = pstmt.executeUpdate();
			if (filasAfectadas > 0) {
				return "Maestro agregado con éxito.";
			} else {
				return "No se pudo agregar el profesor.";
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return "Ocurrió un error al registrar el profesor.";
		} finally {
			conexion.desconectar(cn, stm, rs);
		}
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
			String consulta = "INSERT INTO maestros (matricula, nombre, apellido) VALUES (?, ?, ?)";

			PreparedStatement pstmt = cn.prepareStatement(consulta);
			pstmt.setString(1, matricula);
			pstmt.setString(2, nombre);
			pstmt.setString(3, apellido);

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
	        rs = stm.executeQuery("SELECT * FROM `horarios` ORDER BY FIELD(dia_semana, 'Lunes', 'Martes', 'Miércoles', 'Jueves', 'Viernes'), hora_inicio");

	        List<String[]> listaDatos = new ArrayList<>();
	        while (rs.next()) {
	            String matriculaMaestro = rs.getString("matricula_maestro");
	            String diaSemana = rs.getString("dia_semana");
	            String horaInicio = rs.getString("hora_inicio");
	            String horaFin = rs.getString("hora_fin");
	            String salon = rs.getString("salon");

	            String[] tabla = {matriculaMaestro, diaSemana, horaInicio, horaFin, salon};

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

	public boolean asignarHorario(String matriculaMaestro, String diaSemana, String horaInicio, String horaFin, String salon) {
	    Connection cn = null;
	    PreparedStatement ps = null;

	    try {
	        cn = conexion.conectar();
	        String query = "INSERT INTO `horarios` (matricula_maestro, dia_semana, hora_inicio, hora_fin, salon) VALUES (?, ?, ?, ?, ?)";
	        ps = cn.prepareStatement(query);
	        ps.setString(1, matriculaMaestro);
	        ps.setString(2, diaSemana);
	        ps.setString(3, horaInicio);
	        ps.setString(4, horaFin);
	        ps.setString(5, salon);

	        int filasAfectadas = ps.executeUpdate();

	        // Si se insertó al menos una fila, se considera exitoso
	        return filasAfectadas > 0;
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        conexion.desconectar(cn, ps, null);
	    }
	    return false;
	}

}