/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rn;

import java.util.List;
import model.Cliente;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Francke
 */
@Stateless
public class ClienteRN extends AbstractRN<Cliente>{
    @PersistenceContext(unitName="BibliotecaWeb2PU")
    private EntityManager manager;
    
    public ClienteRN(){
        super(Cliente.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return manager;
    }
    
    public void salvar(Cliente u){
        //validar parâmetros
        if(u.getMatricula()==null){
            super.adicionar(u);
        }
        else
            super.atualizar(u);
    }
    
    public List<Cliente> buscarPorNome(String nome) {
        Query query = manager.createQuery("SELECT p FROM Cliente p WHERE  LOWER(p.nome) LIKE :nome");
        query.setParameter("nome", "%"+nome.toLowerCase()+"%");
        return query.getResultList();
    }
    
    public List<Cliente> topQueRetiram() {
        String jpql = "SELECT p FROM Cliente p WHERE p.retiradas > 1 "
                + "ORDER BY p.retiradas DESC";   
        Query query = manager.createQuery(jpql);
        query.setMaxResults(3);
        return query.getResultList();
    }
    
    public List<Cliente> topQueAtrasam() {
        String jpql = "SELECT p FROM Cliente p WHERE p.atrasos > 1 "
                + "ORDER BY p.atrasos DESC";   
        Query query = manager.createQuery(jpql);
        query.setMaxResults(3);
        return query.getResultList();
    }
}
