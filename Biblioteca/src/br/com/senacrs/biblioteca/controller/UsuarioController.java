
package br.com.senacrs.biblioteca.controller;

import br.com.senacrs.biblioteca.Biblioteca;
import br.com.senacrs.biblioteca.dominio.Usuario;
import br.com.senacrs.biblioteca.negocio.NegocioException;
import br.com.senacrs.biblioteca.negocio.UsuarioNegocio;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 *
 * @author Renata Fraga
 */
public class UsuarioController implements Initializable {

    @FXML
    private AnchorPane principal;
    @FXML
    private TableView<Usuario> tableViewUsuarios;
    @FXML
    private TableColumn<Usuario, Integer> tableColumnMatricula;
    @FXML
    private TableColumn<Usuario, String> tableColumnNome;
    @FXML
    private TableColumn<Usuario, String> tableColumnCpf;
    @FXML
    private TableColumn<Usuario, Integer> tableColumnTelefone;
    @FXML
    private TableColumn<Usuario, String> tableColumnEmail;
    
    @FXML
    private TableColumn<Usuario, String> tableColumnStatus;
    

    @FXML
    private AnchorPane painelUsuario;
    @FXML
    private TextField tfMatricula;
    @FXML
    private TextField tfNome;
    @FXML
    private TextField tfCpf;
    @FXML
    private TextField tfTelefone;
    
    @FXML
    private TextField tfEmail;
    
    @FXML
    private TextField tfSenha;
    @FXML
    private TextField tfBuscaMatricula;
    
    @FXML
    private Button btLimpar;
    
    @FXML
    private Button btSalvar;
    
    private int tela;
    private List<Usuario> listaUsuarios;
    private Usuario usuarioSelecionado;

    private ObservableList<Usuario> observableListaUsuarios;
    private UsuarioNegocio usuarioNegocio;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        usuarioNegocio = new UsuarioNegocio();

        //Codigo meio redundante - por isso as vezes é melhor um controller para cada view 
        if (tableViewUsuarios != null) {
            carregarTableViewUsuarios();
        }

    }        
    
    
    private void carregarTableViewUsuarios() {
        tableColumnMatricula.setCellValueFactory(new PropertyValueFactory<>("matricula"));
        tableColumnNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        tableColumnCpf.setCellValueFactory(new PropertyValueFactory<>("cpf"));
        tableColumnTelefone.setCellValueFactory(new PropertyValueFactory<>("telefone"));
        tableColumnEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        tableColumnStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        
        listaUsuarios = usuarioNegocio.listar();

        observableListaUsuarios = FXCollections.observableArrayList(listaUsuarios);
        tableViewUsuarios.setItems(observableListaUsuarios);
    }
    
    @FXML
    private void buscarUsuarioPorMatricula(ActionEvent event) throws IOException, NegocioException {
        tableColumnMatricula.setCellValueFactory(new PropertyValueFactory<>("matricula"));
        tableColumnNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        tableColumnCpf.setCellValueFactory(new PropertyValueFactory<>("cpf"));
        tableColumnTelefone.setCellValueFactory(new PropertyValueFactory<>("telefone"));
        tableColumnStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        
        usuarioSelecionado = usuarioNegocio.procurarPorMatricula(Integer.parseInt(tfBuscaMatricula.getText()));
        observableListaUsuarios = FXCollections.observableArrayList(usuarioSelecionado);
        tableViewUsuarios.setItems(observableListaUsuarios);
    }

    @FXML
    public void tratarBotaoCadastrar(ActionEvent event) throws IOException {
        usuarioSelecionado = null;
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(Biblioteca.class.getResource("view/PainelFormUsuario.fxml"));
        stage.setScene(new Scene(root));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(principal.getScene().getWindow());
        stage.showAndWait();
        carregarTableViewUsuarios();
    }

    @FXML
    public void tratarBotaoEditar(ActionEvent event) throws IOException {
        usuarioSelecionado = tableViewUsuarios.getSelectionModel().getSelectedItem();
        if (usuarioSelecionado != null) {
            FXMLLoader loader = new FXMLLoader(Biblioteca.class.getResource("view/PainelFormUsuario.fxml"));
            Parent root = (Parent) loader.load();

            UsuarioController controller = (UsuarioController) loader.getController();
            controller.setUsuarioSelecionado(usuarioSelecionado);

            Stage dialogStage = new Stage();
            dialogStage.setScene(new Scene(root));
            dialogStage.initModality(Modality.APPLICATION_MODAL);
            dialogStage.initOwner(principal.getScene().getWindow());
            dialogStage.showAndWait();
            carregarTableViewUsuarios();
        } else {
            JOptionPane.showMessageDialog(null,"você precisa ter um usuário cadastrado");
        }
    }

    @FXML
    public void tratarBotaoRemover(ActionEvent event) throws IOException {
        Usuario usuarioSelecionado = tableViewUsuarios.getSelectionModel().getSelectedItem();
        if (usuarioSelecionado != null) {
            try {
                usuarioNegocio.deletar(usuarioSelecionado);
                this.carregarTableViewUsuarios();
            } catch (NegocioException ex) {
                 JOptionPane.showMessageDialog(null,ex.getMessage());
            }
        } else {
             JOptionPane.showMessageDialog(null,"Precisa selecionar um usuário.");
        }
    }
    
   
    @FXML
    public void tratarBotaoSalvar(ActionEvent event) throws IOException {
        Stage stage = (Stage) painelUsuario.getScene().getWindow();
        
        if(usuarioSelecionado == null) //Se for cadastrar
        {
            try {
                       usuarioNegocio.salvar(new Usuario(
                        Integer.parseInt(tfMatricula.getText()), 
                        tfNome.getText(),
                        tfCpf.getText(),
                        Integer.parseInt(tfTelefone.getText()),
                        tfSenha.getText(),
                        tfEmail.getText(),
                        false
                        )           
                );                
                stage.close();
            } catch (NegocioException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
            
        }
        else //Se for editar
        {
            try {
               
                    usuarioSelecionado.setMatricula(Integer.parseInt(tfMatricula.getText()));
                    usuarioSelecionado.setNome(tfNome.getText());
                    usuarioSelecionado.setCpf(tfCpf.getText());
                    usuarioSelecionado.setTelefone(Integer.parseInt(tfTelefone.getText()));                
                    usuarioSelecionado.setSenha(tfSenha.getText());
                    usuarioSelecionado.setEmail(tfEmail.getText());
                    usuarioNegocio.atualizar(usuarioSelecionado);
                              
                stage.close();
            } catch (NegocioException ex) {
                JOptionPane.showMessageDialog(null,ex.getMessage());
            }

            
        } 
    }

    @FXML
    public void tratarBotaoCancelar(ActionEvent event) throws IOException {
        Stage stage = (Stage) painelUsuario.getScene().getWindow();
        stage.close();

    }

    public Usuario getUsuarioSelecionado() {
        return usuarioSelecionado;
    }

    public void setUsuarioSelecionado(Usuario usuarioSelecionado) {
        this.usuarioSelecionado = usuarioSelecionado;
        tfMatricula.setText(String.valueOf(usuarioSelecionado.getMatricula()));
        tfNome.setText(usuarioSelecionado.getNome());
        tfCpf.setText(usuarioSelecionado.getCpf());
        tfTelefone.setText(String.valueOf(usuarioSelecionado.getTelefone()));
        tfSenha.setText(usuarioSelecionado.getSenha());
        tfEmail.setText(usuarioSelecionado.getEmail());
        
    }
    
}
