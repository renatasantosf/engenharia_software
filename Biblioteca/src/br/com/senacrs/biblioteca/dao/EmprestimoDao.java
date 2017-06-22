package br.com.senacrs.biblioteca.dao;

import br.com.senacrs.biblioteca.dominio.Emprestimo;


/**
 *
 * @author Renata Fraga
 */
public interface EmprestimoDao extends Dao<Emprestimo>{
    public Emprestimo procurarPorCodigo(int codigo);
    
    
}
