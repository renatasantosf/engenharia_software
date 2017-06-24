package br.com.senacrs.biblioteca.dao.impl_BD;

import br.com.senacrs.biblioteca.dao.LivroDao;
import br.com.senacrs.biblioteca.dominio.Livro;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Renata Fraga
 */
public class LivroDaoBd extends DaoBd<Livro> implements LivroDao{

    @Override
    public void salvar(Livro livro) {
        int id = 0;
        try {
            String sql = "INSERT INTO livro ("
                    + "isbn,editora,categoria,nome,autor,disponivel) "
                    + "VALUES (?,?,?,?,?,?)";
           
            conectarObtendoId(sql);
            comando.setString(1, livro.getIsbn());
            comando.setString(2, livro.getEditora());
            comando.setString(3, livro.getCategoria());
            comando.setString(4, livro.getTitulo());
            comando.setString(5, livro.getAutor());
            comando.setBoolean(6, false);
            comando.executeUpdate();
            
            ResultSet resultado = comando.getGeneratedKeys();
            if (resultado.next()) {
                //seta o id para o objeto
                id = resultado.getInt(1);
                livro.setCodigo_exemplar(id);
                JOptionPane.showMessageDialog(null,"Livro cadastrado com sucesso.");
            }
            else{
                System.err.println("Erro de Sistema - Nao gerou o id conforme esperado!");
                throw new BDException("Nao gerou o id conforme esperado!");
            }
            

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Erro ao incluir Livro.");
            throw new RuntimeException(ex);
        } finally {
            fecharConexao();
        }
    
    }

    @Override
    public void deletar(Livro livro) {
      try {
            String sql = "DELETE FROM livro WHERE id = ?";

            conectar(sql);
            comando.setInt(1, livro.getCodigo_exemplar());
            comando.executeUpdate();
            JOptionPane.showMessageDialog(null,"Livro removido com sucesso.");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Erro ao remover Livro.");
            throw new RuntimeException(ex);
        } finally {
            fecharConexao();
        }

    
    }

    @Override
    public void atualizar(Livro livro) {
         try {
            String sql = "UPDATE livro SET isbn=?, editora=?, categoria=?, nome=?, "
                    + "autor=?, disponivel =?"
                    + "WHERE id=?";
          
            conectar(sql);
            comando.setString(1, livro.getIsbn());
            comando.setString(2, livro.getEditora());
            comando.setString(3, livro.getCategoria());
            comando.setString(4, livro.getTitulo());
            comando.setString(5, livro.getAutor());
            comando.setBoolean(6, livro.isStatus());
            comando.executeUpdate();
            JOptionPane.showMessageDialog(null,"Livro alterado com sucesso.");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Erro ao atualizar Usuário.");
            throw new RuntimeException(ex);
        } finally {
            fecharConexao();
        }
    }

    @Override
    public List<Livro> listar() {
        List<Livro> listaLivros = new ArrayList<>();
      
        String sql = "SELECT * FROM livro";

        try {
            conectar(sql);
            
            ResultSet resultado = comando.executeQuery();

            while (resultado.next()) {
                int codigo_exemplar = resultado.getInt("id");
                String isbn = resultado.getString("isbn");
                String editora = resultado.getString("editora");
                String categoria = resultado.getString("categoria");
                String titulo = resultado.getString("nome");
                String autor = resultado.getString("autor");
                boolean status = resultado.getBoolean("disponivel");
                

                Livro livro = new Livro(codigo_exemplar,isbn,editora,categoria,titulo,
                autor,status);

                listaLivros.add(livro);

            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Erro ao buscar livros.");
            throw new RuntimeException(ex);
        } finally {
            fecharConexao();
        }

        return (listaLivros);
    }

    
    @Override
    public List<Livro> listarLivro() {
        List<Livro> listaLivros = new ArrayList<>();
      
        String sql = "SELECT id,nome FROM livro";

        try {
            conectar(sql);
            
            ResultSet resultado = comando.executeQuery();

            while (resultado.next()) {
                int codigo_exemplar = resultado.getInt("id");
                String isbn = resultado.getString("isbn");
                String editora = resultado.getString("editora");
                String categoria = resultado.getString("categoria");
                String titulo = resultado.getString("nome");
                String autor = resultado.getString("autor");
                boolean status = resultado.getBoolean("disponivel");
                

                Livro livro = new Livro(codigo_exemplar,isbn,editora,categoria,titulo,
                autor,status);

                listaLivros.add(livro);

            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Erro ao buscar livros.");
            throw new RuntimeException(ex);
        } finally {
            fecharConexao();
        }

        return (listaLivros);
    }

   
   
    @Override
    public Livro procurarPorCodigo(int codigo_exemplar) {
        String sql = "SELECT * FROM livro WHERE id = ?";

        try {
            conectar(sql);
            comando.setInt(1, codigo_exemplar);
            ResultSet resultado = comando.executeQuery();

            if (resultado.next()) {
                String isbn = resultado.getString("isbn");
                String editora = resultado.getString("editora");
                String categoria = resultado.getString("categoria");
                String titulo = resultado.getString("nome");
                String autor = resultado.getString("autor");
                boolean status = resultado.getBoolean("disponivel");
                

                Livro livro = new Livro(codigo_exemplar,isbn,editora,categoria,titulo,
                autor,status);

                return livro;

            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Erro ao buscar .");
            throw new RuntimeException(ex);
        } finally {
            fecharConexao();
        }

        return (null);
    }

    @Override
    public Livro procurarPorTitulo(String titulo) {
          
        String sql = "SELECT * FROM livro WHERE nome = ?";

        try {
            conectar(sql);
            comando.setString(1, titulo);
            ResultSet resultado = comando.executeQuery();

            while (resultado.next()) {
                int codigo_exemplar = resultado.getInt("id");
                String isbn = resultado.getString("isbn");
                String editora = resultado.getString("editora");
                String categoria = resultado.getString("categoria");
                String autor = resultado.getString("autor");
                boolean status = resultado.getBoolean("disponivel");
                

                Livro livro = new Livro(codigo_exemplar,isbn,editora,categoria,titulo,
                autor,status);

                return livro;

            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Erro ao buscar livros.");
            throw new RuntimeException(ex);
        } finally {
            fecharConexao();
        }

        return (null);
      
    }

}
