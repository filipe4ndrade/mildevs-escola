package br.com.mildevs.dao;

import java.util.ArrayList;
import java.util.List;

import br.com.mildevs.entity.Aluno;
import br.com.mildevs.entity.Professor;
import br.com.mildevs.entity.Sala;
import br.com.mildevs.entity.Turma;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

public class TurmaDAO {
	private EntityManager manager;

	public TurmaDAO() {
		this.manager = Persistence.createEntityManagerFactory("escola").createEntityManager();
	}
	
	public Turma criaTurma(Sala sala) {
		Turma turma = new Turma();
		turma.setSala(sala);
		sala.setTurma(turma);
		
		this.manager.getTransaction().begin();
		this.manager.persist(turma);
		this.manager.getTransaction().commit();
		
		return turma;
	}
	
	public boolean adicionaProfessor(Professor professor, int codTurma) {
		
		Turma turma = this.manager.find(Turma.class, codTurma);
		
		if (turma == null)
			return false;
		
		if (turma.getProfessor() != null)
			return false;
		
		turma.setProfessor(professor);
		
		this.manager.getTransaction().begin();
		this.manager.merge(turma);
		this.manager.getTransaction().commit();
		
		return true;
	}

	public boolean adicionaAluno(Aluno aluno, int codTurma) {
		
		Turma turma = this.manager.find(Turma.class, codTurma);
		
		if (turma == null)
			return false;
		
		if (turma.getProfessor() == null)
			return false;
		
		List<Aluno> alunosTurma = turma.getAlunos();
		
		if (alunosTurma == null) {
			alunosTurma = new ArrayList<Aluno>();
		}
		
		alunosTurma.add(aluno);
		turma.setAlunos(alunosTurma);
		
		List<Turma> turmasAluno = aluno.getTurmas();
		
		if (turmasAluno == null) {
			turmasAluno = new ArrayList<Turma>();
		}
		turmasAluno.add(turma);
		aluno.setTurmas(turmasAluno);
		
		
		this.manager.getTransaction().begin();
		this.manager.merge(turma);
		this.manager.getTransaction().commit();
		
		return true;
	}
	
	public void listaDeChamada(int codTurma) {
	
		Turma turma = this.manager.find(Turma.class, codTurma);
		
		if (turma == null)
			return;
		
		for (Aluno aluno : turma.getAlunos()) {
			System.out.println(aluno);
		}
	}
	
	public List<Turma> listarTurmas() {
		Query query = this.manager.createQuery("select t from Turma as t");
		
		return query.getResultList();
	}
	
	public boolean removeTurma(int codTurma) {
		
		Turma turma = this.manager.find(Turma.class, codTurma);
		
		if (turma == null)
			return false;
		
		this.manager.getTransaction().begin();
		this.manager.remove(turma);
		this.manager.getTransaction().commit();
		
		return true;
	}
}
