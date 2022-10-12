
-- 1 -> Baixar o postgresql versão 10.22
-- 2 -> Criar a base de dados projeto2-estoque
-- 3 -> Acessar a query tools do postgresql e rodar o script abaixo.

create table produtos (
	id_produto SERIAL,
	id_categorias int,
	name VARCHAR NOT NULL,
	price INTEGER NOT NULL,
	qtd INTEGER NOT NULL,
	PRIMARY KEY ( id_produto ),
  	CONSTRAINT  FK_produtos_categoria  FOREIGN KEY ( id_categorias) REFERENCES  categoria  ( id_categorias)
);

create table categoria (
	id_categorias SERIAL,
	name VARCHAR NOT NULL,
	PRIMARY KEY (id_categorias),
  	constraint id_categorias_unique  UNIQUE ( id_categorias )
)