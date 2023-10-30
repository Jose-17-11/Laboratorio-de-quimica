package modelo;

public class Profesor extends Usuario{
	
	protected String grupo, materia;
	
	public Profesor(String grupo, String materia) {
		this.grupo = grupo;
		this.materia = materia;
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
