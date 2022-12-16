package br.com.mildevs;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Programa {

	public static void main(String[] args) {
		
		  //criando as tabelas
		  // o argumento daí é o name do persistence
		  EntityManagerFactory entityManager =
		  Persistence.createEntityManagerFactory("escola"); entityManager.close();
		 
	}
}