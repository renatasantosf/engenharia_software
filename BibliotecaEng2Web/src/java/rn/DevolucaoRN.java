/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rn;

import java.util.List;
import model.Devolucao;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Francke
 */
@Stateless
public class DevolucaoRN extends AbstractRN<Devolucao>{
    @PersistenceContext(unitName="BibliotecaWeb2PU")
    private EntityManager manager;
    
    public DevolucaoRN(){
        super(Devolucao.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return manager;
    }
    
    public void salvar(Devolucao l){
        //validar par√¢metros
        if(l.getId()==null){
            super.adicionar(l);
        }
        else
            super.atualizar(l);
    }
    
    //metodo usado para deletar livro em cascata = (cascade = {CascadeType.PERSIST}) n„o funciona
    public List<Devolucao> buscarLivroExclusao(Long id) {
        Query query = manager.createQuery("SELECT p FROM Devolucao p WHERE p.livro.id = :id");
        query.setParameter("id", id);
        return query.getResultList();
    }
    
    //metodo usado para deletar cliente em cascata = (cascade = {CascadeType.PERSIST}) n„o funciona
    public List<Devolucao> buscarClienteExclusao(Long matricula) {
        Query query = manager.createQuery("SELECT p FROM Devolucao p WHERE p.cliente.matricula = :matricula");
        query.setParameter("matricula", matricula);
        return query.getResultList();
    }
}
