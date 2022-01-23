/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cm.supptic.controllers;

import cm.supptic.subclass.StageManager;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXNodesList;
import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.NodeOrientation;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

/**
 *
 * @author Noumodong
 */
public class AcceuilController implements Initializable {

    private double x, y;
    @FXML
    private Circle circleLogo;
    @FXML
    private VBox panneauPrincipalEnseignant;
    @FXML
    private HBox loading;
    @FXML
    private ImageView imageMaxMin;
    @FXML
    private VBox panneauPrincipalTablirEmploi;

    @FXML
    private VBox panneauPrincipalEnvoyerEmploiTemps;
    private final JFXButton plus = new JFXButton();
    private final JFXButton deconnexion = new JFXButton();
    private final JFXButton parametres = new JFXButton();
    @FXML
    private JFXNodesList nodeListe;

    @FXML
    void bareDragged(MouseEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        stage.setX(event.getScreenX() - x);
        stage.setY(event.getScreenY() - y);
        stage.setOpacity(0.85);
    }

    @FXML
    void barePressed(MouseEvent event) {
        x = event.getSceneX();
        y = event.getSceneY();
    }

    @FXML
    void reduireCliquer(MouseEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setIconified(true);
    }

    @FXML
    void closeCliquer(MouseEvent event) {
        Platform.exit();
    }

    //voir enseignants
    @FXML
    void voirEnseignantCliquer(MouseEvent event) {
        ChangeDansLesPanneaux(panneauPrincipalEnseignant, "affichargeEnseignants");
    }

    //Ajouter un enseignant
    @FXML
    void ajouterEnseignantCliquer(MouseEvent event) {
        ChangeDansLesPanneaux(panneauPrincipalEnseignant, "ajouterEnseignant");
    }

    @FXML
    void agrandirANDminimiseCliquer(MouseEvent event) throws FileNotFoundException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        if (!stage.isMaximized()) {
            stage.setMaximized(true);
            imageMaxMin.setImage(new Image(getClass().getResource("/cm/supptic/icons/squaremax.png").toString()));
        } else {
            stage.setMaximized(false);
            imageMaxMin.setImage(new Image(getClass().getResource("/cm/supptic/icons/squaremin.png").toString()));
        }

    }

    //Je charge la fenetre voir les enseignant sur la panneau  de la selection gestion des enseingnants
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Image im = new Image(getClass().getResource("/cm/supptic/images/logo_supptic.jpg").toString());
        circleLogo.setFill(new ImagePattern(im));
        ChangeDansLesPanneaux(panneauPrincipalEnseignant, "affichargeEnseignants");
        ChangeDansLesPanneaux(panneauPrincipalEnvoyerEmploiTemps, "envoirEmploiTemps");
        ChangeDansLesPanneaux(panneauPrincipalTablirEmploi, "genererEmploiDeTemps");
        new File("C:\\emploiDeTempsSUPPTIC").mkdir();
        Image img1 = new Image(getClass().getResource("/cm/supptic/icons/maths.png").toString());
        ImageView img11 = new ImageView(img1);
        img11.setFitHeight(15);
        img11.setFitHeight(15);
        Image img2 = new Image(getClass().getResource("/cm/supptic/icons/logout.png").toString());
        ImageView img22 = new ImageView(img2);
        img22.setRotate(180);
        Image img3 = new Image(getClass().getResource("/cm/supptic/icons/Settings.png").toString());
        ImageView img33 = new ImageView(img3);
        nodeListe.getStylesheets().add(getClass().getResource("/cm/supptic/styles/styleNodeList.css").toExternalForm());
        plus.setGraphic(img11);
        plus.setButtonType(JFXButton.ButtonType.RAISED);
        plus.setStyle("-fx-background-color :white;");

        deconnexion.setButtonType(JFXButton.ButtonType.RAISED);
        deconnexion.setStyle("-fx-background-color :white;");
        deconnexion.setGraphic(img22);

        parametres.setButtonType(JFXButton.ButtonType.RAISED);
        parametres.setStyle("-fx-background-color :white;");
        parametres.setGraphic(img33);

        nodeListe.addAnimatedNode(plus);
        nodeListe.addAnimatedNode(deconnexion);
        nodeListe.addAnimatedNode(parametres);
        nodeListe.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);

        deconnexion.setOnAction((event) -> {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.hide();
            new StageManager().changerStage("login.fxml");
        });

    }

    public void ChangeDansLesPanneaux(Pane root, String fieldFXML) {
        try {

            AnchorPane pan = FXMLLoader.load(getClass().getResource("/cm/supptic/fxml/" + fieldFXML + ".fxml"));
            root.getChildren().clear();
            root.getChildren().add(pan);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void bareReleased(MouseEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setOpacity(1);
    }

}
