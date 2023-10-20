package conexion_jdbc;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.sql.Time;
import java.time.LocalDateTime;
import java.util.Scanner;

public class Peticiones {
	// Se crea la variable de tipo conexion
	private Conexion conexion;

	// El constructor de la clase crea el objeto conexion
	public Peticiones() {
		conexion = new Conexion();
	}

	/*
	 * Este metodo booleano se encarga de hacer la peticion a la data base y con un
	 * if verificar si los datos que ingreso el usuario son correctos o no
	 */
	public boolean verificarUsuario(String username, String password) {
		Connection cn = null;
		Statement stm = null;
		ResultSet rs = null;

		try {
			cn = conexion.conectar();
			stm = cn.createStatement();
			rs = stm.executeQuery("SELECT * FROM admin");

			while (rs.next()) {
				String usuario = rs.getString(2);
				String pass = rs.getString(3);
				if (usuario.equalsIgnoreCase(username) && pass.equals(pass))
					return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			conexion.desconectar(cn, stm, rs);
		}
		return false;
	}

	// Comparacion de datos del login del administrador
	public String datos(boolean option) {
		Connection cn = null;
		Statement stm = null;
		ResultSet rs = null;

		try {
			cn = conexion.conectar();
			stm = cn.createStatement();
			rs = stm.executeQuery("SELECT * FROM admin");

			while (rs.next()) {
				String nombre = rs.getString(4);
				String matricula = rs.getString(1);
				if (option == true) {
					return nombre;
				} else {
					return matricula;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			conexion.desconectar(cn, stm, rs);
		}
		return null;
	}

	public String accesoLaboratorio(String matricula, String salon, String grupo, String materia) throws SQLIntegrityConstraintViolationException {
		/*************************** Fecha y Hora **********************************/
		

		/********************************************************************/

		Connection cn = null;
		Statement stm = null;
		ResultSet rs = null;

		try {
			cn = conexion.conectar();
			stm = cn.createStatement();
			rs = stm.executeQuery("SELECT * FROM accesos");
	        String consulta = "INSERT INTO accesos (fecha, hora, maestro, salon, grupo, materia) VALUES (?, ?, ?, ?, ?, ?)";
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
			pstmt.setString(5, grupo);
			pstmt.setString(6, materia);

			// Ejecutar la consulta
			int filasAfectadas = pstmt.executeUpdate();
			if (filasAfectadas > 0) {
				return ("Maestro agregado con éxito.");
			} else {
				return ("No se pudo agregar el profesor.");
			}

		} catch (SQLIntegrityConstraintViolationException e) {
	        // Maneja la excepción de duplicación de clave primaria aquí
	        return "El maestro que intenta ingresar ya se encuentra registrado.";
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        conexion.desconectar(cn, stm, rs);
	    }
		return "";
	}

	// En este metodo se agregan maestros nuevo para darles acceso a los
	// laboratorios de quimica
	public String nuevoMaestro(String matricula, String nombre, String apellido)
			throws SQLIntegrityConstraintViolationException {
		Connection cn = null;
		Statement stm = null;
		ResultSet rs = null;

		try {
			cn = conexion.conectar();
			stm = cn.createStatement();
			rs = stm.executeQuery("SELECT * FROM maestros");
			String consulta = "INSERT INTO maestros (Matricula, nombre, apellido) VALUES (?, ?, ?)";

			// Crear un objeto PreparedStatement
			PreparedStatement pstmt = cn.prepareStatement(consulta);
			// En el objeto se ingresan los atributos del nuevo maestro
			pstmt.setString(1, matricula);
			pstmt.setString(2, nombre);
			pstmt.setString(3, apellido);

			// Ejecutar la consulta
			int filasAfectadas = pstmt.executeUpdate();
			if (filasAfectadas > 0) {
				return ("Maestro agregado con éxito.");
			} else {
				return ("No se pudo agregar el profesor.");
			}

		} catch (SQLIntegrityConstraintViolationException e) {
			conexion.desconectar(cn, stm, rs);
			// Maneja la excepción de duplicación de clave primaria aquí
			return ("El maestro que intenta ingresar ya se encuentra registrado.");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			conexion.desconectar(cn, stm, rs);
		}
		return "";
	}

	// Metodo que elimina maestros de la base de datos si no estan en la base de
	// datos manda el mensaje
	public String eliminarMaestro(String matricula) throws SQLIntegrityConstraintViolationException {
		Connection cn = null;
		Statement stm = null;
		ResultSet rs = null;

		try {
			cn = conexion.conectar();
			stm = cn.createStatement();
			rs = stm.executeQuery("SELECT * FROM maestros");
			String consulta = "DELETE FROM maestros WHERE Matricula = ?";

			// Crear un objeto PreparedStatement
			PreparedStatement pstmt = cn.prepareStatement(consulta);
			// En el objeto se ingresan los atributos del nuevo maestro
			pstmt.setString(1, matricula);

			// Ejecutar la consulta
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

	public void PeticionesJdbc() {
		Scanner sc = new Scanner(System.in);
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