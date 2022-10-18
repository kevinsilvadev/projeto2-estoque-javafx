
-- 1 -> Baixar o postgresql versão 10.22
-- 2 -> Criar a base de dados projeto2-estoque
-- 3 -> Acessar a query tools do postgresql e rodar o script abaixo.

create table produtos (
	id_produto SERIAL,
	name_categoria VARCHAR NOT NULL,
	name VARCHAR NOT NULL,
	price INTEGER NOT NULL,
	qtd INTEGER NOT NULL,
	description VARCHAR NOT NULL,
	PRIMARY KEY ( id_produto ),
  	CONSTRAINT  FK_produtos_categoria  FOREIGN KEY ( name_categoria) REFERENCES  categoria  (name)
);

create table categoria (
	name VARCHAR NOT NULL,
	PRIMARY KEY (name),
  	constraint name_unique  UNIQUE ( name )
)