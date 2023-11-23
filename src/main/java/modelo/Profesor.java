package modelo;

public class Profesor extends Usuario{
	
	protected String grupo, materia, usuario, password;
	
	public Profesor(String matricula, String nombre, String apellidos) {
		super.matricula = matricula;
		super.nombre = nombre;
		super.apellidos = apellidos;
	}
	
	public Profesor(String usuario, String password) {
		this.usuario = usuario;
		this.password = password;
	}
	
	public String getUsuario() {
		return usuario;
	}
	
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getGrupo() {
		return grupo;
	}
	
	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}
	
	public String getMateria() {
		return materia;
	}
	
	public void setMateria(String materia) {
		this.materia = materia;
	}
}
