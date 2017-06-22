package br.com.senacrs.biblioteca.dao.impl_BD;

import br.com.senacrs.biblioteca.dao.EmprestimoDao;
import br.com.senacrs.biblioteca.dominio.Emprestimo;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author renat
 */
public class EmprestimoDaoBd extends DaoBd<Emprestimo> implements EmprestimoDao {
    UsuarioDaoBd usuarioDao = new UsuarioDaoBd();
    LivroDaoBd livroDaoBd = new LivroDaoBd();
    
    @Override
    public void salvar(Emprestimo emprestimo) {
        int id = 0;
               
      try {
            String sql = "INSERT INTO emprestimo ("
                    + "usuario_matricula,livro_codigo_exemplar,dt_emprestimo,"
                    + "dt_entrega "
                    + "VALUES (?,?,?,?)";

            
            conectarObtendoId(sql);
            comando.setInt(1, emprestimo.getUsuario().getMatricula());
            comando.setInt(2, emprestimo.getLivro().getCodigo_exemplar());
            comando.setDate(3, java.sql.Date.valueOf(emprestimo.getDt_emprestimo()));
            comando.setDate(4,java.sql.Date.valueOf(emprestimo.getDt_entrega()));
            comando.executeUpdate();
            ResultSet resultado = comando.getGeneratedKeys();
            if (resultado.next()) {
                //seta o id para o objeto
                id = resultado.getInt(1);
                emprestimo.setCodigo(id);
                JOptionPane.showMessageDialog(null,"Empréstimo cadastrado com sucesso.");
                sql = "UPDATE livro SET status = ? WHERE matricula = ?";
                conectarObtendoId(sql);
                comando.setBoolean(1, true);
                comando.setInt(2, id);
                comando.executeUpdate();
                sql = "UPDATE livro SET status = ? WHERE codigo_exemplar = ?";
                conectarObtendoId(sql);
                comando.setBoolean(1, true);
                comando.setInt(2, id);
                comando.executeUpdate();
                
            } else{
                System.err.println("Erro de Sistema - Nao gerou o id conforme esperado!");
                throw new BDException("Nao gerou o id conforme esperado!");
            }
            

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Erro ao incluir Empréstimo.");
            throw new RuntimeException(ex);
        } finally {
            fecharConexao();
        }
    
    }

    @Override
    public void deletar(Emprestimo emprestimo) {
     try {
            String sql = "DELETE FROM emprestimo WHERE codigo = ?";

            conectar(sql);
            comando.setInt(1, emprestimo.getCodigo());
            comando.executeUpdate();
            JOptionPane.showMessageDialog(null,"Empréstimo removido com sucesso.");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Erro ao remover Empréstimo.");
            throw new RuntimeException(ex);
        } finally {
            fecharConexao();
        }

    
    }

    @Override
    public void atualizar(Emprestimo dominio) {
      /*não se atualiza empréstimo*/ 
    }

    @Override
    public List<Emprestimo> listar() {
        
        
        List<Emprestimo> listaEmprestimos = new ArrayList<>();
      
        String sql = "SELECT *FROM emprestimo";

        try {
            conectar(sql);
            
            ResultSet resultado = comando.executeQuery();

            while (resultado.next()) {
                int codigo = resultado.getInt("codigo");
                int usuario_matricula = resultado.getInt("usuario_matricula");
                int livro_codigo_exemplar = resultado.getInt("livro_codigo_exemplar");
               
                Emprestimo emprestimo = new Emprestimo(codigo,usuarioDao.procurarPorMatricula(usuario_matricula),
                livroDaoBd.procurarPorCodigo(livro_codigo_exemplar));

                listaEmprestimos.add(emprestimo);

            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Erro ao buscar livros.");
            throw new RuntimeException(ex);
        } finally {
            fecharConexao();
        }

        return (listaEmprestimos);
    }

    @Override
    public Emprestimo procurarPorCodigo(int codigo) {
       String sql = "SELECT * FROM emprestimo WHERE codigo = ?";

        try {
            conectar(sql);
            comando.setInt(1, codigo);
            ResultSet resultado = comando.executeQuery();

            if (resultado.next()) {
                int usuario_matricula = resultado.getInt("usuario_matricula");
                int livro_codigo_exemplar = resultado.getInt("livro_codigo_exemplar");
                                
                

                Emprestimo emprestimo = new Emprestimo(codigo,usuarioDao.procurarPorMatricula(usuario_matricula),
                livroDaoBd.procurarPorCodigo(livro_codigo_exemplar));

                return emprestimo;

            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Erro ao buscar empréstimo.");
            throw new RuntimeException(ex);
        } finally {
            fecharConexao();
        }

        return (null);
    }
    
    
}
