package br.ifsp.livraria.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.ifsp.livraria.entities.Editora;

public class EditoraRepository {
	
	private EntityManager manager ;
	
	public EditoraRepository (EntityManager manager ) {
		this.manager = manager ;
	}
	
	public void adiciona (Editora e) {
		this.manager.persist(e);
	}
	
	public Editora busca (int id) {
		return this.manager.find (Editora.class, id);
	}
	
	@SuppressWarnings("unchecked")
	public List <Editora> buscaTodas() {
		Query query = this.manager.createQuery(" SELECT e FROM Editora e");
		return query.getResultList();
	}

}
