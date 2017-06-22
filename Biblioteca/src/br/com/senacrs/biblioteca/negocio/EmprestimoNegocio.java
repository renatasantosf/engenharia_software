/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senacrs.biblioteca.negocio;

import br.com.senacrs.biblioteca.dao.EmprestimoDao;
import br.com.senacrs.biblioteca.dao.impl_BD.EmprestimoDaoBd;
import br.com.senacrs.biblioteca.dominio.Emprestimo;
import java.util.List;

/**
 *
 * @author renat
 */
public class EmprestimoNegocio {
    private EmprestimoDao emprestimoDao;

    public EmprestimoNegocio() {
        emprestimoDao = new EmprestimoDaoBd();
    }

    public void salvar(Emprestimo emprestimo) throws NegocioException {
        this.validarCamposObrigatorios(emprestimo);
        this.validarEmprestimoExistente(emprestimo);
        emprestimoDao.salvar(emprestimo);
    }

    public List<Emprestimo> listar() {
        return (emprestimoDao.listar());
    }

    public void deletar(Emprestimo emprestimo) throws NegocioException {
        if (emprestimo == null || emprestimo.getCodigo()== 0) {
            throw new NegocioException("Empréstimo inexistente!");
        }
        emprestimoDao.deletar(emprestimo);
    }

    public void atualizar(Emprestimo emprestimo) throws NegocioException {
        //não será implementado
    }

    public Emprestimo procurarPorCodigo(int codigo) throws NegocioException {
        Emprestimo emprestimo = emprestimoDao.procurarPorCodigo(codigo);
        if (codigo== 0) {
            throw new NegocioException("Empréstimo não encontrado");
        }
        return (emprestimoDao.procurarPorCodigo(codigo));
    }

    
   
    public boolean emprestimoExiste(int codigo) {
        Emprestimo emprestimo = emprestimoDao.procurarPorCodigo(codigo);
        return (emprestimo != null);
    }

    private void validarCamposObrigatorios(Emprestimo emprestimo) throws NegocioException {
        if (emprestimo.getUsuario().getMatricula() == 0) {
            throw new NegocioException("Matrícula não informada.");
        }
        if (emprestimo.getLivro().getCodigo_exemplar()== 0) {
            throw new NegocioException("Livro não informado. ");
        }
        
    }

    private void validarEmprestimoExistente(Emprestimo emprestimo) throws NegocioException {
        if (emprestimoExiste(emprestimo.getCodigo())) {
            throw new NegocioException("Emprestimo existente.");
        }
    }
}
