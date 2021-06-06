package models;

public class User {
	private String nome;
	private int matricula;
	private String curso;
	public User(String nome, Integer matricula, String curso) {
		this.nome = nome;
		this.matricula = matricula;
		this.curso = curso;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getMatricula() {
		return matricula;
	}
	public void setMatricula(int matricula) {
		this.matricula = matricula;
	}
	public String getCurso() {
		return curso;
	}
	public void setCurso(String curso) {
		this.curso = curso;
	}
	
}
