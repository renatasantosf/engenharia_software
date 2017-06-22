
package br.com.senacrs.biblioteca.negocio;

import br.com.senacrs.biblioteca.dao.LivroDao;
import br.com.senacrs.biblioteca.dao.impl_BD.LivroDaoBd;
import br.com.senacrs.biblioteca.dominio.Livro;
import java.util.List;

/**
 *
 * @author renat
 */
public class LivroNegocio {
    private LivroDao livroDao;

    public LivroNegocio() {
        livroDao = new LivroDaoBd();
    }

    public void salvar(Livro livro) throws NegocioException {
        this.validarCamposObrigatorios(livro);
        this.validarExemplarExistente(livro);
        livroDao.salvar(livro);
    }

    public List<Livro> listar() {
        return (livroDao.listar());
    }

    public void deletar(Livro livro) throws NegocioException {
        if (livro == null || livro.getCodigo_exemplar()== 0) {
            throw new NegocioException("Exemplar inexistente!");
        }
        livroDao.deletar(livro);
    }

    public void atualizar(Livro livro) throws NegocioException {
        if (livro == null || livro.getCodigo_exemplar()== 0) {
            throw new NegocioException("Exemplar inexistente!");
        }
        this.validarCamposObrigatorios(livro);
        livroDao.atualizar(livro);
    }

    public Livro procurarPorCodigo(int codigo) throws NegocioException {
        Livro livro = livroDao.procurarPorCodigo(codigo);
        if (codigo== 0) {
            throw new NegocioException("Exemplar não encontrado");
        }
        return (livroDao.procurarPorCodigo(codigo));
    }

    public Livro procurarPorTitulo(String titulo) throws NegocioException {
        return(livroDao.procurarPorTitulo(titulo));
    }

   
    public boolean livroExiste(int codigo_exemplar) {
        Livro livro = livroDao.procurarPorCodigo(codigo_exemplar);
        return (livro != null);
    }

    private void validarCamposObrigatorios(Livro livro) throws NegocioException {
        if (livro.getIsbn() == null || livro.getIsbn().isEmpty()) {
            throw new NegocioException("Campo isbn não informado");
        }
        if (livro.getEditora() == null || livro.getEditora().isEmpty()) {
            throw new NegocioException("Campo editora não informado");
        }
        if (livro.getCategoria() == null || livro.getCategoria().isEmpty()) {
            throw new NegocioException("Campo categoria nao informado");
        }
        if (livro.getEdicao() == 0) {
            throw new NegocioException("Campo edição não informado");
        }
        if (livro.getTitulo() == null || livro.getTitulo().isEmpty()) {
            throw new NegocioException("Campo título não informado");
        }
        if (livro.getAutor() == null || livro.getAutor().isEmpty()) {
            throw new NegocioException("Campo autor não informado");
        }
    }

    private void validarExemplarExistente(Livro livro) throws NegocioException {
        if (livroExiste(livro.getCodigo_exemplar())) {
            throw new NegocioException("Exemplar existente.");
        }
    }
    
}
