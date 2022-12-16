package br.com.mildevs.dao;

import java.util.List;

import br.com.mildevs.entity.Aluno;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

public class AlunoDAO {
	private EntityManager manager;

	public AlunoDAO() {
		this.manager = Persistence.createEntityManagerFactory("escola").createEntityManager();
	}
	
	// CRIAR
	public boolean criaAluno(Aluno aluno) {
		manager.getTransaction().begin();
		manager.persist(aluno);
		manager.getTransaction().commit();

		return true;
	}
	
	// CONSULTAR
	public Aluno consultaAluno(int matricula) {
		return manager.find(Aluno.class, matricula);
	}
	
	// LISTAR
	public List<Aluno> listaAlunos() {
		Query query = manager.createQuery("select a from Aluno as a");
		return query.getResultList();
	}
	
	// REMOVER
	public boolean removeAluno(int matricula) {
		Aluno alunoASerRemovido = manager.find(Aluno.class, matricula);
		
		if (alunoASerRemovido == null)
			return false;
		
		manager.getTransaction().begin();
		manager.remove(alunoASerRemovido);
		manager.getTransaction().commit();
		
		return true;
	}
}
