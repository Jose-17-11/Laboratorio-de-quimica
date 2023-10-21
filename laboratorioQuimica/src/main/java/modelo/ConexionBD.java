package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConexionBD {
//	Se crean las constantes que almacenaran el controlador, url, usuario y contraseña
	private static final String CONTROLADOR = "com.mysql.cj.jdbc.Driver";
	private static final String URL = "jdbc:mysql://localhost:3306/Laboratorio_quimica";
	private static final String USUARIO = "root";
	private static final String CLAVE = "1q2w3e4r5t0p9o8i7u6y";
	
	static {
		try {
//			Se ingresa el controlador
			Class.forName(CONTROLADOR);
		}catch(ClassNotFoundException e) {
//			Si esta mal la conexion con el controlador arrojara la siguiente exception
			System.out.println("Error al cargar el controlador");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
//	Metodo que realiza la conexion a la base de datos por lo que el metodo sera de tipo "Connection"
	public Connection conectar() {
		Connection conexion = null;

/*		Se encierra en el try para controlar dos excepciones una en caso de que no este correcta la conexion con el controlador y 
		otra en el caso de que no este correcta la conexion con la base de datos	*/
		try {
//			Se ingresa a la conexion los datos de acceso a la base de datos
			conexion = DriverManager.getConnection(URL, USUARIO, CLAVE);
//			Si la conexion fue exitosa arrojara el siguiente texto
			System.out.println("Conecion OK");
			
		}  catch(SQLException e){
//			Si esta mal la conexion con la base de datos arrojara la siguiente exception
			System.out.println("Error en la conexion");
			e.printStackTrace();
		}
//		Retorna la coneccion que se realiza con la base de datos
		return conexion;
	}
	
	//Metodo que se llamara al final de cada peticion para cerrar todas las peticiones y queden nulos los valores
	public void desconectar(Connection cn, Statement stm, ResultSet rs) {
		try {
//			Se verifica que los objetos rs, stm y cn sean diferentes de null
			if(rs != null) {
//				.close 		Se utiliza para cerrar los objetos
				rs.close();
			}
			if(stm != null) {
				stm.close();
			}
			if(cn != null) {
				cn.close();
			}
		}catch(Exception e2) {
//			Si sruge algun problema al cerrar los objetos se captura la excepcion en e2.printStackTrace
			e2.printStackTrace();
		}
	}
	
}

