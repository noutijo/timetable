/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cm.supptic.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

/**
 * FXML Controller class
 *
 * @author Noumodong
 */
public class EnvoirEmploiTempsController implements Initializable {

    @FXML
    private AnchorPane panFocus;
    @FXML
    private TableView<?> tableEnseignant;
    @FXML
    private TableColumn<?, ?> nomColumn;
    @FXML
    private TableColumn<?, ?> prenomsColumn;
    @FXML
    private TableColumn<?, ?> emailColumn;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void envoyerCliquer(MouseEvent event) {
    }
    
}
