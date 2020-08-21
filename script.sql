create table pessoa(
  id bigserial not null primary key,
  nome varchar(100),
  rg varchar(20),
  cpf varchar(20),
  datanascimento date
);

create table pessoacontato(
  id bigserial not null primary key,
  nome varchar(100),
  telefone varchar(20),
  celular varchar(20),
  pessoaid integer references pessoa(id)
);


CREATE SEQUENCE hibernate_sequence START 1;


