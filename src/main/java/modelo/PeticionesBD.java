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
				String correo = rs.getString(6);
				return new Administrador(matricula, nombre, correo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			conexion.desconectar(cn, stm, rs);
		}
		return null;
	}

	public List<String[]> accesos() {
		Connection cn = null;
		Statement stm = null;
		ResultSet rs = null;

		try {
			cn = conexion.conectar();
			stm = cn.createStatement();
			rs = stm.executeQuery("SELECT * FROM `accesos` WHERE fecha BETWEEN '2023-10-30' AND '2023-11-04'");

			List<String[]> listaDatos = new ArrayList<>();
			while (rs.next()) {
				String fecha = rs.getString(1);
				String hora = rs.getString(2);
				String maestro = rs.getString(3);
				String salon = rs.getString(4);
				String grupo = rs.getString(5);
				String materia = rs.getString(6);
				String carrera = rs.getString(7);

//				System.out.println(fecha + " " + hora + " " + maestro + " " + salon + " " + grupo + " " + materia + " " + carrera);
				String tabla[] = { fecha, hora, maestro, salon, grupo, materia, carrera };

				// Agregar el array tabla a la lista
				listaDatos.add(tabla);
//				if (i == 0) {
//					return nombre + " " + apellido;
//				} else if (i == 1) {
//					return matricula;
//				} else {
//					return correo;
//				}
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
		Statement stm = null;
		ResultSet rs = null;

		try {
			cn = conexion.conectar();
			stm = cn.createStatement();
			rs = stm.executeQuery("SELECT * FROM maestros");
			String consulta = "DELETE FROM maestros WHERE Matricula = ?";

			PreparedStatement pstmt = cn.prepareStatement(consulta);
			pstmt.setString(1, matricula);

			int filasAfectadas = pstmt.executeUpdate();
			if (filasAfectadas > 0) {
				return "Maestro eliminado con éxito.";
			} else {
				return "No se pudo encontrar al maestro con la matrícula proporcionada.";
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return "Error al eliminar al maestro.";
		} finally {
			conexion.desconectar(cn, stm, rs);
		}
	}

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

	public void PeticionesJdbc() {
//		cn es una variable para representar la conexion a una base de datos
		Connection cn = null;
//		La variable stagement sirve para poder enviar consultas a la base de datos para su ejecucion
		Statement stm = null;
//		Esta variable alamacena resultados de una consulta sql
		ResultSet rs = null;
		try {
//			cn representara la conexion con la base de datos ya establecida
			cn = conexion.conectar();
//			stm nos ayudara a crear consultas mediante cn a la base de datos
			stm = cn.createStatement();
//			rs toma el valor de toda la tabla usuario de la base de datos mediante stm
			rs = stm.executeQuery("SELECT * FROM maestros");
//			Si la tabla rs sigue teniendo mas filas sigue el ciclo, si no hay mas filas se rompe el ciclo 
//			List<Datos> listaDatos = new ArrayList<>();
			while (rs.next()) {
				// Valor de la columna 1
//				int matricula = rs.getInt(1);
//				//Valor de la columna 2
//				String grupo = rs.getString(2);
//				//Valor de la columna 3
//				String materia = rs.getString(3);
//				String hora = rs.getString(4);
//				Datos datos = new Datos(matricula, grupo, materia, hora);
//				listaDatos.add(datos);

			}
		} catch (SQLException e) {
			e.printStackTrace();

		}
		/*
		 * finally se ejecutara siempre aunque no surja alguna excepcion y sirve para
		 * realizar tareas de limpieza y garantizar que los recursos se cierren
		 * adecuadamente tambien sirver para posibles excepciones que surjan al cerrar
		 * los recursos
		 */
		finally {
			conexion.desconectar(cn, stm, rs);
		}
	}
	// Metodo de tipo lista que extrae los datos de una tabla y los agrega a una
	// lista para despues poder manipular los datos
//    public List<Datos> obtenerDatos() {
//        List<Datos> listaDatos = new ArrayList<>();
//        Connection cn = null;
//        Statement stm = null;
//        ResultSet rs = null;
//
//        try {
//            cn = conexion.conectar();
//            stm = cn.createStatement();
//            rs = stm.executeQuery("SELECT * FROM admin");
//
//            while (rs.next()) {
//                int matricula = rs.getInt("matricula");
//                String grupo = rs.getString("grupo");
//                String materia = rs.getString("materia");
//                String hora = rs.getString("hora");
//
//                Datos datos = new Datos(matricula, grupo, materia, hora);
//                listaDatos.add(datos);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally {
//            conexion.desconectar(cn, stm, rs);
//        }
//
//        return listaDatos;
//    }

}