/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senacrs.biblioteca.dominio;

import java.time.LocalDate;

/**
 *
 * @author renat
 *
 */
public class Emprestimo {
    private int codigo;
    private Usuario usuario;
    private Livro livro;
    private LocalDate dt_emprestimo;
    private LocalDate dt_entrega;

    public Emprestimo(int codigo, Usuario usuario, Livro livro) {
        this.codigo = codigo;
        this.usuario = usuario;
        this.livro = livro;
        this.dt_emprestimo = LocalDate.now();
        this.dt_entrega = dt_emprestimo.plusDays(7);
         
    }
     public Emprestimo(Usuario usuario, Livro livro) {
        this.codigo = codigo;
        this.usuario = usuario;
        this.livro = livro;
        this.dt_emprestimo = LocalDate.now();
        this.dt_entrega = dt_emprestimo.plusDays(7);
         
    }
    
    
     public Emprestimo(Usuario usuario, Livro livro, LocalDate dt_emprestimo, LocalDate dt_entrega) {
        this.usuario = usuario;
        this.livro = livro;
        this.dt_emprestimo = dt_emprestimo;
        this.dt_entrega = dt_entrega;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Livro getLivro() {
        return livro;
    }

    public void setLivro(Livro livro) {
        this.livro = livro;
    }

    public LocalDate getDt_emprestimo() {
        return dt_emprestimo;
    }

    public void setDt_emprestimo(LocalDate dt_emprestimo) {
        this.dt_emprestimo = dt_emprestimo;
    }

    public LocalDate getDt_entrega() {
        return dt_entrega;
    }

    public void setDt_entrega(LocalDate dt_entrega) {
        this.dt_entrega = dt_entrega;
    }
     
     
    
}
