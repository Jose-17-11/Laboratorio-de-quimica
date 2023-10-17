package controlador;

import conexion_jdbc.Peticiones;

public class Acceso {
	/*
	 * Metodo intermediario que evalua que el usuario y contraseña ingresados por el
	 * usuario sean los correctos y permita el acceso al administrador
	 */
	public boolean acceso(String user, String password) {
		// Se crea el objeto acceder instanciando la clase Peticiones
		Peticiones acceder = new Peticiones();
		/*
		 * Se instancia el metodo booleano verificarUsuario pasandole los datos ingresados por el usuario en el login 
		 * mediante una peticion a la base de datos
		 */
		if (acceder.verificarUsuario(user, password)) {
			return true;
		}
		return false;
	}
}
