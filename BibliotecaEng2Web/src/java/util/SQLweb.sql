CREATE TABLE cliente
(
  matricula bigint NOT NULL,
  atrasos integer,
  nome character varying(255),
  retiradas integer,
  telefone character varying(255),
  email character varying,
  cpf character varying(255),
  CONSTRAINT cliente_pkey PRIMARY KEY (matricula)
)

CREATE TABLE devolucao
(
  id bigint NOT NULL,
  datadevolucao date,
  datadevolvido date,
  dataretirada date,
  cliente_matricula bigint,
  livro_id bigint,
  CONSTRAINT devolucao_pkey PRIMARY KEY (id),
  CONSTRAINT fk_devolucao_cliente_matricula FOREIGN KEY (cliente_matricula)
      REFERENCES cliente (matricula) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_devolucao_livro_id FOREIGN KEY (livro_id)
      REFERENCES livro (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)

CREATE TABLE livro
(
  id bigint NOT NULL,
  autor character varying(255),
  dataliberacao date,
  disponivel boolean,
  editora character varying(255),
  isbn character varying(255),
  nome character varying(255),
  retiradas integer,
  categoria character varying,
  CONSTRAINT livro_pkey PRIMARY KEY (id)
)

CREATE TABLE retiradas
(
  id bigint NOT NULL,
  datadevolucao date,
  dataretirada date,
  cliente_matricula bigint,
  livro_id bigint,
  CONSTRAINT retiradas_pkey PRIMARY KEY (id),
  CONSTRAINT fk_retiradas_cliente_matricula FOREIGN KEY (cliente_matricula)
      REFERENCES cliente (matricula) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_retiradas_livro_id FOREIGN KEY (livro_id)
      REFERENCES livro (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)

CREATE TABLE sequence
(
  seq_name character varying(50) NOT NULL,
  seq_count numeric(38,0),
  CONSTRAINT sequence_pkey PRIMARY KEY (seq_name)
)

CREATE TABLE usuario
(
  id bigint NOT NULL,
  admin boolean,
  email character varying(255),
  estado character varying(255),
  login character varying(255),
  nome character varying(255),
  senha character varying(255),
  sexo integer,
  telefone character varying(255),
  CONSTRAINT usuario_pkey PRIMARY KEY (id)
)