package br.com.mildevs.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Professor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cod_funcionario")
	private int codFuncionario;
	
	@Column(nullable = false)
	private String nome;

	@Column(nullable = false)
	private String telefone;
	
	@Column(name = "nivel_graduacao", nullable = false)
	private String nivelGraduacao = "MESTRADO";
	
	@Column(nullable = false)
	private double salario;
	
	@Column(nullable = false)
	private String disciplina;

	@OneToMany(mappedBy = "professor", cascade = CascadeType.ALL)
	private List<Turma> turmas;
	
	
	public List<Turma> getTurmas() {
		return turmas;
	}

	public void setTurmas(List<Turma> turmas) {
		this.turmas = turmas;
	}

	public int getCodFuncionario() {
		return codFuncionario;
	}

	public void setCodFuncionario(int codFuncionario) {
		this.codFuncionario = codFuncionario;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getNivelGraduacao() {
		return nivelGraduacao;
	}

	public void setNivelGraduacao(String nivelGraduacao) {
		this.nivelGraduacao = nivelGraduacao;
	}

	public double getSalario() {
		return salario;
	}

	public void setSalario(double salario) {
		this.salario = salario;
	}

	public String getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(String disciplina) {
		this.disciplina = disciplina;
	}

	@Override
	public String toString() {
		return "Professor [codFuncionario=" + codFuncionario + ", nome=" + nome + ", telefone=" + telefone
				+ ", nivelGraduacao=" + nivelGraduacao + ", salario=" + salario + ", disciplina=" + disciplina;
	}
}
