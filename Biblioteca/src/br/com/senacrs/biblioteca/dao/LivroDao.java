package br.com.senacrs.biblioteca.dao;

import br.com.senacrs.biblioteca.dominio.Livro;
import java.util.List;

/**
 *
 * @author Renata Fraga
 */
public interface LivroDao extends Dao<Livro> {
    public Livro procurarPorCodigo(int codigo_exemplar);
    public List<Livro> procurarPorTitulo(String titulo);
    public List<Livro> procurarPorStatus();
    
}
