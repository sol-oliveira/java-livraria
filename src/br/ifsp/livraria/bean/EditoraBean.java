package br.ifsp.livraria.bean;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.ifsp.livraria.entities.Editora;
import br.ifsp.livraria.repository.EditoraRepository;

@ManagedBean
public class EditoraBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String nome;
	private String email;
	
	public String cadastrarEditora(){
		
		Editora edit = new Editora();
		edit.setNome(nome);
		edit.setEmail(email);
		
		EntityManagerFactory factory = 
				Persistence.createEntityManagerFactory("livraria");
		
		EntityManager manager = factory.createEntityManager();
		
		EditoraRepository editoraRepository = new EditoraRepository(manager);
		
		manager.getTransaction().begin();
		
		editoraRepository.adiciona(edit);
		
		manager.getTransaction().commit();
		
		factory.close();
		
		System.out.println("Nome: " + nome);
		System.out.println("E-mail: " + email);
		
		
		return "RespostaEditora";
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
