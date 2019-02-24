CREATE DATABASE livraria;

USE livraria ;

CREATE TABLE Usuario (
	id BIGINT NOT NULL AUTO_INCREMENT ,
	login VARCHAR (50) NOT NULL ,
	senha VARCHAR (50) NOT NULL ,
	PRIMARY KEY (id),
	UNIQUE KEY (login)
)
ENGINE = InnoDB;

INSERT INTO Usuario (login, senha) VALUES ('admin', 'admin');


CREATE TABLE Editora (
	id BIGINT NOT NULL AUTO_INCREMENT ,
	nome VARCHAR (255) NOT NULL ,
	email VARCHAR (255) NOT NULL ,
	PRIMARY KEY (id)
)
ENGINE = InnoDB;

INSERT INTO Editora (nome, email) VALUES ('Oreilly', 'oreilly@email.com');
INSERT INTO Editora (nome, email) VALUES ('Wrox', 'wrox@email.com');
INSERT INTO Editora (nome, email) VALUES ('Apress', 'apress@email.com');

CREATE TABLE Livro (
	id BIGINT NOT NULL AUTO_INCREMENT ,
	titulo VARCHAR (255) NOT NULL ,
	preco DOUBLE NOT NULL ,
	editora_id BIGINT NOT NULL ,
	PRIMARY KEY (id),
	CONSTRAINT fk_editora FOREIGN KEY fk_editora ( editora_id )
	REFERENCES Editora (id)
	ON DELETE RESTRICT
	ON UPDATE RESTRICT
)
ENGINE = InnoDB ;

INSERT INTO Livro (titulo, preco, editora_id) VALUES ('Aprendendo C#', 89.90, 1);
INSERT INTO Livro (titulo, preco, editora_id) VALUES ('Introdução ao JSF 2', 122.90, 3);
INSERT INTO Livro (titulo, preco, editora_id) VALUES ('JSF 2 Avançado', 149.90, 3);
