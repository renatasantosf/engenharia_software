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
        try {
            String sql = "INSERT INTO livro ("
                    + "isbn,editora,categoria,edicao,titulo,autor,status) "
                    + "VALUES (?,?,?,?,?,?,?)";

            
            conectarObtendoId(sql);
            comando.setString(1, livro.getIsbn());
            comando.setString(2, livro.getEditora());
            comando.setString(3, livro.getCategoria());
            comando.setInt(4,livro.getEdicao());
            comando.setString(5, livro.getTitulo());
            comando.setString(6, livro.getAutor());
            comando.setBoolean(7, false);
            comando.executeUpdate();
            

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
            String sql = "DELETE FROM livro WHERE codigo_exemplar = ?";

            conectar(sql);
            comando.setInt(1, livro.getCodigo_exemplar());
            comando.executeUpdate();

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
            String sql = "UPDATE livro SET isbn=?, editora=?, categoria=?, edicao=?,titulo=?, "
                    + "autor=?"
                    + "WHERE codigo_exemplar=?";
          
            conectar(sql);
            comando.setString(1, livro.getIsbn());
            comando.setString(2, livro.getEditora());
            comando.setString(3, livro.getCategoria());
            comando.setInt(4, livro.getEdicao());
            comando.setString(5, livro.getTitulo());
            comando.setString(6, livro.getAutor());
            comando.setBoolean(7, livro.isStatus());
            comando.executeUpdate();

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
                int codigo_exemplar = resultado.getInt("codigo_exemplar");
                String isbn = resultado.getString("isbn");
                String editora = resultado.getString("editora");
                String categoria = resultado.getString("categoria");
                int edicao = resultado.getInt("edicao");
                String titulo = resultado.getString("titulo");
                String autor = resultado.getString("autor");
                boolean status = resultado.getBoolean("status");
                

                Livro livro = new Livro(codigo_exemplar,isbn,editora,categoria,edicao,titulo,
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
        String sql = "SELECT * FROM livro WHERE codigo_exemplar = ?";

        try {
            conectar(sql);
            comando.setInt(1, codigo_exemplar);
            ResultSet resultado = comando.executeQuery();

            if (resultado.next()) {
                String isbn = resultado.getString("isbn");
                String editora = resultado.getString("editora");
                String categoria = resultado.getString("categoria");
                int edicao = resultado.getInt("edicao");
                String titulo = resultado.getString("titulo");
                String autor = resultado.getString("autor");
                boolean status = resultado.getBoolean("status");
                

                Livro livro = new Livro(codigo_exemplar,isbn,editora,categoria,edicao,titulo,
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
    public List<Livro> procurarPorTitulo(String titulo) {
         List<Livro> listaLivros = new ArrayList<>();
      
        String sql = "SELECT * FROM livro WHERE titulo = ?";

        try {
            conectar(sql);
            comando.setString(1, titulo);
            ResultSet resultado = comando.executeQuery();

            while (resultado.next()) {
                int codigo_exemplar = resultado.getInt("codigo_exemplar");
                String isbn = resultado.getString("isbn");
                String editora = resultado.getString("editora");
                String categoria = resultado.getString("categoria");
                int edicao = resultado.getInt("edicao");
                String autor = resultado.getString("autor");
                boolean status = resultado.getBoolean("status");
                

                Livro livro = new Livro(codigo_exemplar,isbn,editora,categoria,edicao,titulo,
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
    public List<Livro> procurarPorStatus() {
       List<Livro> listaLivros = new ArrayList<>();
      
        String sql = "SELECT * FROM livro WHERE status = ?";

        try {
            conectar(sql);
            comando.setBoolean(1,false);
            ResultSet resultado = comando.executeQuery();

            while (resultado.next()) {
                int codigo_exemplar = resultado.getInt("codigo_exemplar");
                String isbn = resultado.getString("isbn");
                String editora = resultado.getString("editora");
                String categoria = resultado.getString("categoria");
                int edicao = resultado.getInt("edicao");
                String titulo = resultado.getString("titulo");
                String autor = resultado.getString("autor");
                boolean status = resultado.getBoolean("status");
                

                Livro livro = new Livro(codigo_exemplar,isbn,editora,categoria,edicao,titulo,
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
    
}
