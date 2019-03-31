/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cm.supptic.controllers;

import cm.supptic.DAOcontroller.DAOEnseignants;
import cm.supptic.avoirConnection.AvoirConnection;
import cm.supptic.managerClass.Enseignant;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;

/**
 * FXML Controller class
 *
 * @author Noumodong
 */
public class EnvoirEmploiTempsController implements Initializable {

    @FXML
    private AnchorPane panFocus;
    @FXML
    private TableView<Enseignant> tableEnseignant;
    @FXML
    private TableColumn<Enseignant, String> nomColumn;
    @FXML
    private TableColumn<Enseignant, String> prenomsColumn;
    @FXML
    private TableColumn<Enseignant, String> emailColumn;
    @FXML
    private JFXTextField nomRecherche;
    @FXML
    private JFXTextField destinaireText;
    @FXML
    private JFXTextField expediteurText;
    @FXML
    private JFXTextField sujetText;
    @FXML
    private JFXTextArea messageArea;
    File fichierSelectionne = null;
    @FXML
    private Text alertEnvoyer;
    @FXML
    private JFXTextField selectionText;
    @FXML
    private Text errorText;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ChargementableEnseignant();
    }

    private void ChargementableEnseignant() {

        //Chargement de la table enseignant lors du démarrage
        try {
            ObservableList<Enseignant> data = FXCollections.observableArrayList(new DAOEnseignants().getNomsPreEmailEnseignants(new AvoirConnection().getConnnection()));
            chargerEnsembleEnseignants(data);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void chargerEnsembleEnseignants(ObservableList<Enseignant> data) {
        try {
            //chargement de la table des enseignants au niveau de l'interface apres la demande de la liste des enseignants
            nomColumn.setCellValueFactory(new PropertyValueFactory<>("nom_enseignant"));
            prenomsColumn.setCellValueFactory(new PropertyValueFactory<>("prenom_enseignant"));
            emailColumn.setCellValueFactory(new PropertyValueFactory<>("email_enseignant"));

            tableEnseignant.setItems(data);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void envoyerCliquer(MouseEvent event) {
    }

    @FXML
    private void actualiserCliquer(MouseEvent event) {
        ChargementableEnseignant();
    }

    @FXML
    private void rechercheEvoyerPressed(KeyEvent event) {
        loadSearch();
    }

    @FXML
    private void rechercheCliquer(MouseEvent event) {
        loadSearch();
    }

    public void loadSearch() {
        try {
            ObservableList<Enseignant> data = FXCollections.observableArrayList(new DAOEnseignants().searchEnseigants(nomRecherche.getText(), new AvoirConnection().getConnnection()));
            chargerEnsembleEnseignants(data);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void tableauCliquer(MouseEvent event) {
        try {
            destinaireText.setText(tableEnseignant.getSelectionModel().getSelectedItem().getEmail_enseignant());

        } catch (Exception e) {
        }
    }

    @FXML
    private void selectionnerFichierPDFCliquer(MouseEvent event) throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Fichier", "*.pdf", "*.docx"));
        if (!(fichierSelectionne == null)) {
            String path = this.fichierSelectionne.getCanonicalPath();
            path = path.substring(0, path.lastIndexOf("\\"));
            fileChooser.setInitialDirectory(new File(path));
        }

        this.fichierSelectionne = fileChooser.showOpenDialog(null);
        if (this.fichierSelectionne != null) {
            this.selectionText.setText(fichierSelectionne.getAbsolutePath());

        } else if ((this.fichierSelectionne == null && selectionText.getText().isEmpty())) {
            new Thread(() -> {
                try {
                    alertEnvoyer.setText("Veillez sélectionner un fichier.");
                    Thread.sleep(5000);
                    alertEnvoyer.setText("");
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }).start();
        }
    }
}
