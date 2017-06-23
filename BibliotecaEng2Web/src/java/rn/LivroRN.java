/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rn;

import java.util.List;
import model.Livro;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Francke
 */
@Stateless
public class LivroRN extends AbstractRN<Livro>{
    @PersistenceContext(unitName="BibliotecaWeb2PU")
    private EntityManager manager;
    
    public LivroRN(){
        super(Livro.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return manager;
    }
    
    public void salvar(Livro l){
        //validar parâmetros
        if(l.getId()==null){
            super.adicionar(l);
        }
        else
            super.atualizar(l);
    }
    
    public List<Livro> buscarPorTitulo(String titulo) {
        Query query = manager.createQuery("SELECT p FROM Livro p WHERE  LOWER(p.nome) LIKE :nome");
        query.setParameter("nome", "%"+titulo.toLowerCase()+"%");
        return query.getResultList();
    }
    
    public List<Livro> maisRetirados() {
        String jpql = "SELECT p FROM Livro p WHERE p.retiradas > 2 "
                + "ORDER BY p.retiradas DESC";   
        Query query = manager.createQuery(jpql);
        query.setMaxResults(4);
        return query.getResultList();
    }
    
    public List<Livro> disponiveis() {
        Query query = manager.createQuery("SELECT p FROM Livro p WHERE p.disponivel = TRUE ORDER BY p.nome");
        return query.getResultList();
    }
}
