package br.ifsp.livraria.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.ifsp.livraria.entities.Editora;
import br.ifsp.livraria.entities.Livro;
import br.ifsp.livraria.repository.EditoraRepository;
import br.ifsp.livraria.repository.LivroRepository;

@ManagedBean
public class LivroBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String titulo;
	private double  preco;
	private int idEditora;
	private List <Editora> editoras;
	
	public LivroBean() {
		
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("livraria");
		EntityManager manager = factory.createEntityManager();
		
		EditoraRepository editoraRepository = new EditoraRepository(manager);
		
		manager.getTransaction().begin();
		
		editoras = editoraRepository.buscaTodas();
		
		factory.close();
	}

	public String cadastrarLivro(){
		
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("livraria");
		EntityManager manager = factory.createEntityManager();
		
		// Obtendo Editora
		EditoraRepository editoraRepository = new EditoraRepository(manager);
		
		manager.getTransaction().begin();
		
		Editora editora = editoraRepository.busca(idEditora);
		
		// Livro
		Livro livro = new Livro();
		livro.setTitulo(titulo);
		livro.setPreco(preco);
		livro.setEditora(editora);
		
		// Salvando Livro
		LivroRepository livroRepository = new LivroRepository(manager);
		
		livroRepository.adiciona(livro);
		
		manager.getTransaction().commit();
		factory.close();
		
		System.out.println("Titulo: " + titulo);
		System.out.println("Preço : " + preco);
		System.out.println("IdEditora : " + idEditora);
		
		return "RespostaLivro";
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

	public List <Editora> getEditoras() {
		return editoras;
	}

	public void setEditoras(List <Editora> editoras) {
		this.editoras = editoras;
	}

	public int getIdEditora() {
		return idEditora;
	}

	public void setIdEditora(int idEditora) {
		this.idEditora = idEditora;
	}
	
}
