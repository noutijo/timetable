/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cm.supptic.controllers;

import cm.supptic.subclass.StageManager;
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
public class LaunchController implements Initializable {

    @FXML
    AnchorPane anchorLaunch;

    @FXML
    void reduireCliquer(MouseEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setIconified(true);
    }

    @FXML
    void closeCliquer(MouseEvent event) {
        Platform.exit();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        new ShowSplashScreen().start();
    }

    class ShowSplashScreen extends Thread {

        @Override
        public void run() {
            try {
                Thread.sleep(3000);

                Platform.runLater(() -> {
                     new StageManager().changerStage("acceuil.fxml");
                    anchorLaunch.getScene().getWindow().hide();
                });
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }

    }
}
