package br.com.senacrs.biblioteca.dominio;

/**
 *
 * @author renat
 */
public class Livro {
    private int codigo_exemplar;
    private String isbn;
    private String editora;
    private String categoria;
    private int edicao;
    private String titulo;
    private String autor;
    private boolean status;

    public Livro(int codigo_exemplar, String isbn, String editora, String categoria, int edicao, String titulo, String autor) {
        this.codigo_exemplar = codigo_exemplar;
        this.isbn = isbn;
        this.editora = editora;
        this.categoria = categoria;
        this.edicao = edicao;
        this.titulo = titulo;
        this.autor = autor;
        this.status = false;
    }
     public Livro(String isbn, String editora, String categoria, int edicao, String titulo, String autor,boolean status) {
        this.isbn = isbn;
        this.editora = editora;
        this.categoria = categoria;
        this.edicao = edicao;
        this.titulo = titulo;
        this.autor = autor;
        this.status = status;
    }
     
     public Livro(int codigo_exemplar, String isbn, String editora, String categoria, int edicao, String titulo, String autor
     ,boolean status) {
        this.codigo_exemplar = codigo_exemplar;
        this.isbn = isbn;
        this.editora = editora;
        this.categoria = categoria;
        this.edicao = edicao;
        this.titulo = titulo;
        this.autor = autor;
        this.status = status;
    }

    public int getCodigo_exemplar() {
        return codigo_exemplar;
    }

    public void setCodigo_exemplar(int codigo_exemplar) {
        this.codigo_exemplar = codigo_exemplar;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getEditora() {
        return editora;
    }

    public void setEditora(String editora) {
        this.editora = editora;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public int getEdicao() {
        return edicao;
    }

    public void setEdicao(int edicao) {
        this.edicao = edicao;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    
     
    
    
    
}
