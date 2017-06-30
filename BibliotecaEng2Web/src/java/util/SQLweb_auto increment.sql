CREATE SEQUENCE livro_id_seq;
ALTER TABLE livro ALTER COLUMN id SET NOT NULL;
ALTER TABLE livro ALTER COLUMN id SET DEFAULT nextval('livro_id_seq');
ALTER SEQUENCE livro_id_seq OWNED BY livro.id;