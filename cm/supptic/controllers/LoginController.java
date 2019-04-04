/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cm.supptic.controllers;

import cm.supptic.subclass.StageManager;
import com.itextpdf.text.pdf.PdfName;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

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
    private Text errorText;

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

            NotificationType notification = NotificationType.CUSTOM;

            TrayNotification tray = new TrayNotification();
            tray.setTitle("Bienvenue sur timeTable");
            tray.setImage(new Image(getClass().getResource("/cm/supptic/images/logo_supptic.jpg").toString()));
            tray.setMessage("Vous pouvez accéder à votre espace.");
            tray.setNotificationType(notification);
            tray.showAndDismiss(Duration.millis(7000));

            anchorLaunchLogin.getScene().getWindow().hide();
            new StageManager().changerStage("launch.fxml");

        } else {
            new Thread(() -> {
                this.user.setText("");
                this.passwd.setText("");
                errorText.setVisible(true);
                try {
                    Thread.sleep(4000);
                    errorText.setVisible(false);
                } catch (InterruptedException ex1) {
                    ex1.printStackTrace();
                }
            }).start();
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
