/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cm.supptic.controllers;

import cm.supptic.subclass.StageManager;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 * @author Noumodong
 */
public class LoginController implements Initializable {

    @FXML
    private AnchorPane anchorLaunchLogin;
    @FXML
    private JFXTextField user;

    @FXML
    private JFXPasswordField passwd;

    @FXML
    void closeCliquer(MouseEvent event) {
        Platform.exit();
    }

    @FXML
    void reduireCliquer(MouseEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setIconified(true);
    }

    @FXML
    void loginCliquer(MouseEvent event) {
        if (user.getText().equals("oreol") && passwd.getText().equals("oreol")) {
            anchorLaunchLogin.getScene().getWindow().hide();
            new StageManager().changerStage("launch.fxml");
        } else if (user.getText().equals("Ayang") && passwd.getText().equals("Ayang")) {
            System.out.println("C'est la tentative de monsieur Ayang");
        } else {
            System.out.println("Veillez entrer les informations valides ...:)");
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try {
          //  Font font = Font.loadFont(LoginController.class.getResource("/cm/supptic/fonts/CHILLER.TTF").toExternalForm(), 40);
        } catch (Exception e) {
        }

    }

}
