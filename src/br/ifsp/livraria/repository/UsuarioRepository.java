package br.ifsp.livraria.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.ifsp.livraria.entities.Usuario;

public class UsuarioRepository {
	
	private EntityManager manager ;
	
	public UsuarioRepository (EntityManager manager ) {
		this.manager = manager ;
	}
	
	public void adiciona (Usuario u) {
		this.manager.persist(u);
	}
	
	public Usuario busca (int id) {
		return this.manager.find (Usuario.class, id);
	}
	
	public Usuario busca (String login, String senha) {
		Query query = 
				this.manager.createQuery(" SELECT u FROM Usuario u "
						+ "WHERE u.login= :login AND u.senha= :senha");
	    query.setParameter("login",login); 
	    query.setParameter("senha",senha);
	    
	    if (query.getResultList() != null && 
	    		query.getResultList().size() != 0) {
	    	return (Usuario) query.getResultList().get(0);
	    } else {
	    	return null;
	    }
	}
	
	@SuppressWarnings("unchecked")
	public List <Usuario> buscaTodas() {
		Query query = this.manager.createQuery(" SELECT u FROM Usuario u");
		return query . getResultList ();
	}

}
