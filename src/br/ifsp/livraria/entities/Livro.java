package br.ifsp.livraria.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table (name="Livro")
public class Livro {
	
	@Id
	@GeneratedValue
	int id;
	
	@Column (name="titulo", nullable=false)
	String titulo;
	
	@Column (name="preco", nullable=false)
	double preco;
	
	@ManyToOne(fetch=FetchType.LAZY) 
	@JoinColumn( name="editora_id")  
	Editora editora;  

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public Editora getEditora() {
		return editora;
	}

	public void setEditora(Editora editora) {
		this.editora = editora;
	}
}
