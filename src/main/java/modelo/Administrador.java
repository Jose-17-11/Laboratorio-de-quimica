package modelo;

public class Administrador extends Usuario {
//	Atributos del administrador que almacenan su usuario y contraseña
	protected String usuario, password;
	// Metodos getter para que los metodos que esten en el controlador puedan
	// comparar el usuario y la contraseña

	public Administrador(String usuario, String password) {
		this.usuario = usuario;
		this.password = password;
	}

	public String getUsuario() {
		return usuario;
	}

	public String getPassword() {
		return password;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
