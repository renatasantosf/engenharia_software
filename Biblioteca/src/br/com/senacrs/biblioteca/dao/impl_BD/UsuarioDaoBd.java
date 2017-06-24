
package br.com.senacrs.biblioteca.dao.impl_BD;

import br.com.senacrs.biblioteca.dao.UsuarioDao;
import br.com.senacrs.biblioteca.dominio.Usuario;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author renat
 */
public class UsuarioDaoBd extends DaoBd<Usuario> implements UsuarioDao {
  

    @Override
    public void salvar(Usuario usuario) {
        try {
            String sql = "INSERT INTO cliente ("
                    + "matricula,nome,cpf,telefone,email) "
                    + "VALUES (?,?,?,?,?)";

            //Foi criado um novo método conectar para obter o id
            conectarObtendoId(sql);
            comando.setInt(1, usuario.getMatricula());
            comando.setString(2, usuario.getNome());
            comando.setString(3, usuario.getCpf());
            comando.setInt(4, usuario.getTelefone());
            comando.setString(5, usuario.getEmail());
                                   
            comando.executeUpdate();
            JOptionPane.showMessageDialog(null,"Usuário cadastrado com sucesso.");

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Erro ao incluir Usuário.");
            throw new RuntimeException(ex);
        } finally {
            fecharConexao();
        }
    }

    @Override
    public void deletar(Usuario usuario) {
        try {
            String sql = "DELETE FROM cliente WHERE matricula = ?";

            conectar(sql);
            comando.setInt(1, usuario.getMatricula());
            comando.executeUpdate();
            JOptionPane.showMessageDialog(null,"Usuário removido com sucesso.");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Erro ao remover Usuário.");
            throw new RuntimeException(ex);
        } finally {
            fecharConexao();
        }

    }

    @Override
    public void atualizar(Usuario usuario) {
        try {
            String sql = "UPDATE cliente SET nome=?, cpf=?, telefone=?, email=?"
                    + "WHERE matricula=?";
          
            conectar(sql);
            comando.setString(1, usuario.getNome());
            comando.setString(2, usuario.getCpf());
            comando.setInt(3, usuario.getTelefone());
            comando.setString(4, usuario.getEmail());
            comando.setInt(5, usuario.getMatricula());
            comando.executeUpdate();
            JOptionPane.showMessageDialog(null,"Usuário alterado com sucesso.");

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Erro ao atualizar Usuário.");
            throw new RuntimeException(ex);
        } finally {
            fecharConexao();
        }

    }

    @Override
    public List<Usuario> listar() {
        List<Usuario> listaUsuarios = new ArrayList<>();
      
        String sql = "SELECT * FROM cliente";

        try {
            conectar(sql);
            
            ResultSet resultado = comando.executeQuery();

            while (resultado.next()) {
                int matricula = resultado.getInt("matricula");
                String nome = resultado.getString("nome");
                String cpf = resultado.getString("cpf");
                int telefone = resultado.getInt("telefone");
                String email = resultado.getString("email");
                
               

                Usuario usuario = new Usuario(matricula,nome,cpf,telefone,email);

                listaUsuarios.add(usuario);

            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Erro ao buscar Usuários.");
            throw new RuntimeException(ex);
        } finally {
            fecharConexao();
        }

        return (listaUsuarios);
    }
    
    
    @Override
    public List<Usuario> listarUsuario() {
        List<Usuario> listaUsuarios = new ArrayList<>();
      
        String sql = "SELECT MATRICULA FROM cliente";

        try {
            conectar(sql);
            
            ResultSet resultado = comando.executeQuery();

            while (resultado.next()) {
                int matricula = resultado.getInt("matricula");
                String nome = resultado.getString("nome");
                String cpf = resultado.getString("cpf");
                int telefone = resultado.getInt("telefone");
                String email = resultado.getString("email");
               

                Usuario usuario = new Usuario(matricula,nome,cpf,telefone,email);

                listaUsuarios.add(usuario);

            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Erro ao buscar Usuários.");
            throw new RuntimeException(ex);
        } finally {
            fecharConexao();
        }

        return (listaUsuarios);
    }

    @Override
    public Usuario procurarPorMatricula(int matricula) {
        String sql = "SELECT * FROM cliente WHERE matricula = ?";

        try {
            conectar(sql);
            comando.setInt(1, matricula);
            ResultSet resultado = comando.executeQuery();

            if (resultado.next()) {
                String nome = resultado.getString("nome");
                String cpf = resultado.getString("cpf");
                int telefone = resultado.getInt("telefone");
                String email = resultado.getString("email");
               

                Usuario usuario = new Usuario(matricula,nome,cpf,telefone,email);

                return usuario;

            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Erro ao buscar Usuário pela matrícula.");
            throw new RuntimeException(ex);
        } finally {
            fecharConexao();
        }

        return (null);
    }

   
    @Override
    public List<Usuario> procurarPorNome(String nome) {
        List<Usuario> listaUsuarios = new ArrayList<>();
        String sql = "SELECT * FROM cliente WHERE nome LIKE ?";

        try {
            conectar(sql);
            comando.setString(1, "%" + nome + "%");
            ResultSet resultado = comando.executeQuery();

            while (resultado.next()) {
                int matricula = resultado.getInt("matricula");
                String cpf = resultado.getString("cpf");
                int telefone = resultado.getInt("telefone");
                String senha = resultado.getString("senha");
                String email = resultado.getString("email");
                boolean status = resultado.getBoolean("status");
               
                 Usuario usuario = new Usuario(matricula,nome,cpf,telefone,email);
                

                listaUsuarios.add(usuario);

            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Erro ao listar usuários pelo nome.");
            throw new RuntimeException(ex);
        } finally {
            fecharConexao();
        }
        return (listaUsuarios);
    }

    
}


