
package br.com.senacrs.biblioteca.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author renat
 */
public class PrincipalController implements Initializable {

    @FXML 
    private AnchorPane painelMenu;
    
    @FXML
    private Button btLivro;
    
    @FXML
    private Button btUsuario;
    

       
    
    @FXML
    private void tratarTelaUsuario(ActionEvent event) throws IOException {
        FXMLLoader.load(getClass().getResource("/view/PainelTabelaUsuario.fxml"));

    }
    
    @FXML
    private void tratarTelaLivro(ActionEvent event) throws IOException {
        Stage stage = (Stage) painelMenu.getScene().getWindow();
        stage.setTitle("Livros");
        Parent painelTelaProxima = FXMLLoader.load(this.getClass().getResource("PainelTabelaLivro.fxml"));
        stage.setScene(new Scene(painelTelaProxima));

    }
    
    @FXML
    private void tratarTelaEmprestimos(ActionEvent event) throws IOException {
        Stage stage = (Stage) painelMenu.getScene().getWindow();
        stage.setTitle("Empréstimos");
        Parent painelTelaProxima = FXMLLoader.load(this.getClass().getResource("view/PainelTabelaEmprestimo.fxml"));
        stage.setScene(new Scene(painelTelaProxima));

    }
    
    @FXML
    private void tratarTelaDevolucoes(ActionEvent event) throws IOException {
       /* Stage stage = (Stage) mainPanel.getScene().getWindow();
        stage.setTitle("Filmes");
        Parent painelTelaProxima = FXMLLoader.load(this.getClass().getResource("/view/FilmePainel1.fxml"));
        stage.setScene(new Scene(painelTelaProxima));
        */
    }
    
    @FXML
    private void tratarTelaAtrasos(ActionEvent event) throws IOException {
       /* Stage stage = (Stage) mainPanel.getScene().getWindow();
        stage.setTitle("Filmes");
        Parent painelTelaProxima = FXMLLoader.load(this.getClass().getResource("/view/FilmePainel1.fxml"));
        stage.setScene(new Scene(painelTelaProxima));*/

       
    }
    
     @Override
        public void initialize(URL url, ResourceBundle rb) {
        // TODO
        }
    
}
