/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senacrs.biblioteca.dao;
import br.com.senacrs.biblioteca.dominio.Usuario;
import java.util.List;

/**
 *
 * @author renat
 */
public interface UsuarioDao extends Dao<Usuario>{
   public Usuario procurarPorMatricula(int matricula);
   public List<Usuario> procurarPorNome(String nome);
      
}
