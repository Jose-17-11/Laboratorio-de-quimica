package modelo;

public class Administrador extends Usuario{
//	Atributos del administrador que almacenan su usuario y contraseña
	protected String usuario = "root", password = "1234";
	//Metodos getter para que los metodos que esten en el controlador puedan comparar el usuario y la contraseña
	public String getUsuario() {
		return usuario;
	}
	
	public String getPassword() {
		return password;
	}
	
}
