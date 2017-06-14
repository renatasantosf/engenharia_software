USE BIBLIOTECA_DB;
CREATE TABLE USUARIO(
    MATRICULA INTEGER PRIMARY KEY,
    NOME VARCHAR(100) NOT NULL,
    CPF VARCHAR(11) NOT NULL,
    TELEFONE VARCHAR(11) NOT NULL,
    SENHA VARCHAR(20) NOT NULL,
    STATUS BOOLEAN NOT NULL
);

CREATE TABLE LIVRO(
    CODIGO_EXEMPLAR SERIAL PRIMARY KEY,
    ISBN VARCHAR(13) NOT NULL,
    EDITORA VARCHAR(50) NOT NULL,
    CATEGORIA VARCHAR(50) NOT NULL,
    EDICAO INTEGER NOT NULL,
    TITULO VARCHAR(100) NOT NULL,
    AUTOR VARCHAR(100) NOT NULL,
    STATUS BOOLEAN NOT NULL
);

CREATE TABLE EMPRESTIMO(
    CODIGO SERIAL PRIMARY KEY,
    USUARIO_MATRICULA INTEGER REFERENCES USUARIO(MATRICULA) NOT NULL,
    LIVRO_CODIGO_EXEMPLAR INTEGER REFERENCES LIVRO(CODIGO_EXEMPLAR) NOT NULL,
    DT_EMPRESTIMO DATE NOT NULL,
    DT_ENTREGA DATE NOT NULL
);

CREATE TABLE ATRASO(
    ID_ATRASO SERIAL PRIMARY KEY,
    EMPRESTIMO_CODIGO INTEGER REFERENCES EMPRESTIMO(CODIGO),
    QTD_DIAS INTEGER NOT NULL,
    MULTA DECIMAL NOT NULL
);
    

    
    
    