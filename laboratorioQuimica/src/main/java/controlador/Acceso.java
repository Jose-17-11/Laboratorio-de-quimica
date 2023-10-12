package controlador;

import modelo.Administrador;

public class Acceso {
/*	Metodo intermediario que evalua que el usuario y contraseña ingresados por el usuario sean los correctos y de acceso al rol de 
 *  administrador
	*/
	public boolean acceso(String user, String password) {
		//Se crea el objeto acceder instanciando la clase Administrador
		Administrador acceder = new Administrador();
		//Se compara el usuario y contraseñas ingresadas por el usuario con las que estan almacenadas en la clase Administrador
		if(user.equalsIgnoreCase(acceder.getUsuario()) && password.equals(acceder.getPassword())) {
			return true;
		}
		return false;
	}
}
