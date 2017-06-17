package br.com.senacrs.biblioteca.controller;

import br.com.senacrs.biblioteca.Biblioteca;
import br.com.senacrs.biblioteca.dominio.Livro;
import br.com.senacrs.biblioteca.negocio.LivroNegocio;
import br.com.senacrs.biblioteca.negocio.NegocioException;
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
import javafx.scene.control.ComboBox;
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
 * @author renat
 */
public class LivroController  implements Initializable {

    @FXML
    private AnchorPane principalLivro;
    @FXML
    private TableView<Livro> tableViewLivros;
    @FXML
    private TableColumn<Livro, Integer> tableColumnCodigo;
    @FXML
    private TableColumn<Livro, Integer> tableColumnIsbn;
    @FXML
    private TableColumn<Livro, String> tableColumnTitulo;
    @FXML
    private TableColumn<Livro, String> tableColumnAutor;
    @FXML
    private TableColumn<Livro, String> tableColumnEditora;
    @FXML
    private TableColumn<Livro, Integer> tableColumnEdicao;
    @FXML
    private TableColumn<Livro, String> tableColumnCategoria;
    @FXML
    private TableColumn<Livro, String> tableColumnStatus;
    @FXML
    private TextField tfBuscaLivro;
    @FXML
    private Button btBuscar;
    @FXML
    private ComboBox cbBusca;

    @FXML
    private AnchorPane formLivro;
    @FXML
    private TextField tfIsbn;
    @FXML
    private TextField tfTitulo;
    @FXML
    private TextField tfAutor;
    @FXML
    private TextField tfEditora;
    
    @FXML
    private TextField tfEdicao;
    
    @FXML
    private TextField tfCategoria;
   
    @FXML
    private TextField tfBuscaMatricula;
    
    @FXML
    private Button btFechar;
    
    @FXML
    private Button btSalvar;
    
    private int tela;
    private List<Livro> listaLivros;
    private Livro livroSelecionado;

    private ObservableList<Livro> observableListaLivros;
    private LivroNegocio livroNegocio;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
       livroNegocio = new LivroNegocio();

        //Codigo meio redundante - por isso as vezes é melhor um controller para cada view 
        if (tableViewLivros != null) {
            carregarTableViewLivros();
        }
        
       cbBusca.setEditable(true);
       cbBusca.getItems().addAll("Código","Titulo","Status");

    }        

    
    @FXML
    private void carregarTableViewLivros() {
        tableColumnCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo_exemplar"));
        tableColumnIsbn.setCellValueFactory(new PropertyValueFactory<>("isbn"));
        tableColumnTitulo.setCellValueFactory(new PropertyValueFactory<>("titulo"));
        tableColumnAutor.setCellValueFactory(new PropertyValueFactory<>("autor"));
        tableColumnEditora.setCellValueFactory(new PropertyValueFactory<>("editora"));
        tableColumnEdicao.setCellValueFactory(new PropertyValueFactory<>("edicao"));
        tableColumnCategoria.setCellValueFactory(new PropertyValueFactory<>("categoria"));
        tableColumnStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        
        listaLivros = livroNegocio.listar();

        observableListaLivros = FXCollections.observableArrayList(listaLivros);
        tableViewLivros.setItems(observableListaLivros);
    }
    
     @FXML
     private void tratarBusca(ActionEvent event) throws IOException, NegocioException {
        if(cbBusca.getValue().equals("Código")) {
            tableColumnCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo_exemplar"));
            tableColumnIsbn.setCellValueFactory(new PropertyValueFactory<>("isbn"));
            tableColumnTitulo.setCellValueFactory(new PropertyValueFactory<>("titulo"));
            tableColumnAutor.setCellValueFactory(new PropertyValueFactory<>("autor"));
            tableColumnEditora.setCellValueFactory(new PropertyValueFactory<>("editora"));
            tableColumnEdicao.setCellValueFactory(new PropertyValueFactory<>("edicao"));
            tableColumnCategoria.setCellValueFactory(new PropertyValueFactory<>("categoria"));
            tableColumnStatus.setCellValueFactory(new PropertyValueFactory<>("status"));

            livroSelecionado = livroNegocio.procurarPorCodigo(Integer.parseInt(tfBuscaLivro.getText()));

            observableListaLivros = FXCollections.observableArrayList(livroSelecionado);
            tableViewLivros.setItems(observableListaLivros);
        }else if(cbBusca.getValue().equals("Título")) {
            tableColumnCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo_exemplar"));
            tableColumnIsbn.setCellValueFactory(new PropertyValueFactory<>("isbn"));
            tableColumnTitulo.setCellValueFactory(new PropertyValueFactory<>("titulo"));
            tableColumnAutor.setCellValueFactory(new PropertyValueFactory<>("autor"));
            tableColumnEditora.setCellValueFactory(new PropertyValueFactory<>("editora"));
            tableColumnEdicao.setCellValueFactory(new PropertyValueFactory<>("edicao"));
            tableColumnCategoria.setCellValueFactory(new PropertyValueFactory<>("categoria"));
            tableColumnStatus.setCellValueFactory(new PropertyValueFactory<>("status"));

            listaLivros = livroNegocio.procurarPorTitulo(tfBuscaLivro.getText());

            observableListaLivros = FXCollections.observableArrayList(livroSelecionado);
            tableViewLivros.setItems(observableListaLivros);
        } else if(cbBusca.getValue().equals("Status")) {
            tableColumnCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo_exemplar"));
            tableColumnIsbn.setCellValueFactory(new PropertyValueFactory<>("isbn"));
            tableColumnTitulo.setCellValueFactory(new PropertyValueFactory<>("titulo"));
            tableColumnAutor.setCellValueFactory(new PropertyValueFactory<>("autor"));
            tableColumnEditora.setCellValueFactory(new PropertyValueFactory<>("editora"));
            tableColumnEdicao.setCellValueFactory(new PropertyValueFactory<>("edicao"));
            tableColumnCategoria.setCellValueFactory(new PropertyValueFactory<>("categoria"));
            tableColumnStatus.setCellValueFactory(new PropertyValueFactory<>("status"));

            listaLivros = livroNegocio.procurarPorStatusDisponivel();

            observableListaLivros = FXCollections.observableArrayList(livroSelecionado);
            tableViewLivros.setItems(observableListaLivros);
            
        }
           
    }

    @FXML
    public void tratarBotaoCadastrar(ActionEvent event) throws IOException {
        livroSelecionado = null;
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(Biblioteca.class.getResource("view/PainelFormularioLivro.fxml"));
        stage.setScene(new Scene(root));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(principalLivro.getScene().getWindow());
        stage.showAndWait();
        carregarTableViewLivros();
    }

    @FXML
    public void tratarBotaoEditar(ActionEvent event) throws IOException {
        livroSelecionado = tableViewLivros.getSelectionModel().getSelectedItem();
        if (livroSelecionado != null) {
            FXMLLoader loader = new FXMLLoader(Biblioteca.class.getResource("view/PainelFormularioLivro.fxml"));
            Parent root = (Parent) loader.load();

            LivroController controller = (LivroController) loader.getController();
            controller.setLivroSelecionado(livroSelecionado);

            Stage dialogStage = new Stage();
            dialogStage.setScene(new Scene(root));
            dialogStage.initModality(Modality.APPLICATION_MODAL);
            dialogStage.initOwner(principalLivro.getScene().getWindow());
            dialogStage.showAndWait();
            carregarTableViewLivros();
        } else {
            JOptionPane.showMessageDialog(null,"você precisa ter um livro cadastrado");
        }
    }

    @FXML
    public void tratarBotaoRemover(ActionEvent event) throws IOException {
        Livro livroSelecionado = tableViewLivros.getSelectionModel().getSelectedItem();
        if (livroSelecionado != null) {
            try {
                livroNegocio.deletar(livroSelecionado);
                this.carregarTableViewLivros();
            } catch (NegocioException ex) {
                 JOptionPane.showMessageDialog(null,ex.getMessage());
            }
        } else {
             JOptionPane.showMessageDialog(null,"Precisa selecionar um livro.");
        }
    }
    
   
    @FXML
    public void tratarBotaoSalvar(ActionEvent event) throws IOException {
        Stage stage = (Stage) formLivro.getScene().getWindow();
        
        if(livroSelecionado == null) //Se for cadastrar
        {
            try {
                livroNegocio.salvar(new Livro(
                        tfIsbn.getText(), 
                        tfEditora.getText(),
                        tfCategoria.getText(),
                        Integer.parseInt(tfEdicao.getText()),
                        tfTitulo.getText(),
                        tfAutor.getText(),
                        false)           
                );                
                stage.close();
            } catch (NegocioException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
            
        }
        else //Se for editar
        {
            try {
          
                    livroSelecionado.setIsbn(tfIsbn.getText());
                    livroSelecionado.setEditora(tfEditora.getText());
                    livroSelecionado.setCategoria(tfCategoria.getText());
                    livroSelecionado.setEdicao(Integer.parseInt(tfEdicao.getText()));                
                    livroSelecionado.setTitulo(tfTitulo.getText());
                    livroSelecionado.setAutor(tfAutor.getText());
                    livroNegocio.atualizar(livroSelecionado);
                              
                stage.close();
            } catch (NegocioException ex) {
                JOptionPane.showMessageDialog(null,ex.getMessage());
            }

            
        } 
    }

    @FXML
    public void tratarBotaoCancelar(ActionEvent event) throws IOException {
        Stage stage = (Stage) formLivro.getScene().getWindow();
        stage.close();

    }

    public Livro getLivroSelecionado() {
        return livroSelecionado;
    }

    public void setLivroSelecionado(Livro livroSelecionado) {
        this.livroSelecionado = livroSelecionado;
        tfIsbn.setText(livroSelecionado.getIsbn());
        tfEditora.setText(livroSelecionado.getEditora());
        tfCategoria.setText(livroSelecionado.getCategoria());
        tfEdicao.setText(String.valueOf(tfEdicao.getText()));
        tfTitulo.setText(livroSelecionado.getTitulo());
        tfAutor.setText(livroSelecionado.getAutor());
    }

    
}
