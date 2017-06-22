/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senacrs.biblioteca.controller;

import br.com.senacrs.biblioteca.dominio.Emprestimo;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author renat
 */
public class EmprestimoController implements Initializable {

    @FXML
    private AnchorPane principalEmprestimo;
    
    @FXML
    private Button btIncluir;
    
    @FXML
    private Button btRemover;
    
    @FXML
    private TextField tfBuscaCodigo;
    
    @FXML
    private Button btBuscaEmprestimo;
    
    @FXML
    private TableView<Emprestimo> tableViewEmprestimos;
    
    @FXML
    
    
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
      
    }
    
}
