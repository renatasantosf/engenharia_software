/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senacrs.biblioteca.negocio;

import br.com.senacrs.biblioteca.dao.UsuarioDao;
import br.com.senacrs.biblioteca.dao.impl_BD.UsuarioDaoBd;
import br.com.senacrs.biblioteca.dominio.Usuario;
import java.util.List;

/**
 *
 * @author renat
 */
public class UsuarioNegocio {
    private UsuarioDao usuarioDao;

    public UsuarioNegocio() {
        usuarioDao = new UsuarioDaoBd();
    }

    public void salvar(Usuario usuario) throws NegocioException {
        this.validarCamposObrigatorios(usuario);
        this.validarMatriculaExistente(usuario);
        usuarioDao.salvar(usuario);
    }

    public List<Usuario> listar() {
        return (usuarioDao.listar());
    }

    public void deletar(Usuario usuario) throws NegocioException {
        if (usuario == null || usuario.getMatricula() == 0) {
            throw new NegocioException("Usuário inexistente!");
        }
        usuarioDao.deletar(usuario);
    }

    public void atualizar(Usuario usuario) throws NegocioException {
        if (usuario == null || usuario.getMatricula() == 0) {
            throw new NegocioException("Usuário inexistente!");
        }
        this.validarCamposObrigatorios(usuario);
        usuarioDao.atualizar(usuario);
    }

    public Usuario procurarPorMatricula(int matricula) throws NegocioException {
        if (matricula == 0) {
            throw new NegocioException("Campo matrícula não informado.");
        }
        Usuario usuario = usuarioDao.procurarPorMatricula(matricula);
        if (matricula== 0) {
            throw new NegocioException("Usuário não encontrado");
        }
        return (usuario);
    }

    public List<Usuario> procurarPorNome(String nome) throws NegocioException {
        if (nome == null || nome.isEmpty()) {
            throw new NegocioException("Campo nome nao informado");
        }
        return(usuarioDao.procurarPorNome(nome));
    }

    public boolean usuarioExiste(int matricula) {
        Usuario usuario = usuarioDao.procurarPorMatricula(matricula);
        return (usuario != null);
    }

    private void validarCamposObrigatorios(Usuario usuario) throws NegocioException {
        if (usuario.getMatricula() == 0) {
            throw new NegocioException("Campo matrícula nao informado");
        }

        if (usuario.getNome() == null || usuario.getNome().isEmpty()) {
            throw new NegocioException("Campo nome nao informado");
        }
        if (usuario.getSenha() == null || usuario.getSenha().isEmpty()) {
            throw new NegocioException("Campo senha nao informado");
        }
        if (usuario.getCpf() == null || usuario.getCpf().isEmpty()) {
            throw new NegocioException("Campo cpf nao informado");
        }
        if (usuario.getTelefone() == 0) {
            throw new NegocioException("Campo telefone nao informado");
        }
    }

    private void validarMatriculaExistente(Usuario usuario) throws NegocioException {
        if (usuarioExiste(usuario.getMatricula())) {
            throw new NegocioException("matricula ja existente");
        }
    }
    
}
