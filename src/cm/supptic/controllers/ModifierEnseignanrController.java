/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cm.supptic.controllers;

import cm.supptic.DAOcontroller.DAOEnseignants;
import cm.supptic.avoirConnection.AvoirConnection;
import cm.supptic.managerClass.Enseignant;
import cm.supptic.subclass.TempEnseignant;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXSnackbar;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.effect.BoxBlur;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

/**
 *
 * @author Noumodong
 */
public class ModifierEnseignanrController implements Initializable {

    @FXML
    private JFXTextField id;

    @FXML
    private JFXTextField nom;

    @FXML
    private JFXTextField prenom;

    @FXML
    private JFXTextField adresse;

    @FXML
    private JFXTextField email;

    @FXML
    private JFXTextField telephone;

    @FXML
    private JFXComboBox<?> statut;
    @FXML
    private Pane panSnack;
    @FXML
    private Text errorText;

    @FXML
    void closeFenModifierCliquer(MouseEvent event) {
        
        

    }

    //Enregistrer les modifications
    @FXML
    void enregistrerModificationCliquer(MouseEvent event) throws SQLException, ClassNotFoundException {

        if (rienEstVide()) {
            try {

                Enseignant enseignant = new Enseignant(id.getText(), nom.getText(), prenom.getText(), adresse.getText(), email.getText(),
                        Integer.valueOf(telephone.getText()), statut.getSelectionModel().getSelectedItem().toString());

                new DAOEnseignants().modifierEnseignant(enseignant, new AvoirConnection().getConnnection());

                JFXSnackbar snac = new JFXSnackbar(this.panSnack);
                snac.getStyleClass().add("jfx-snackbar-content");
                snac.show("Modifier avec succès", 5000);

                //A la fin de la modification on recharge la table
            } catch (ClassNotFoundException | NumberFormatException | SQLException ex) {
                new Thread(() -> {
                    errorText.setVisible(true);
                    try {
                        Thread.sleep(5000);
                        errorText.setVisible(false);
                    } catch (InterruptedException ex1) {
                        ex1.printStackTrace();
                    }
                }).start();
            }
        } else {
            new Thread(() -> {
                errorText.setVisible(true);
                try {
                    Thread.sleep(5000);
                    errorText.setVisible(false);
                } catch (InterruptedException ex1) {
                    ex1.printStackTrace();
                }
            }).start();
        }
    }

    /**
     *
     * @return
     */
    public boolean rienEstVide() {

        if (!nom.getText().equals("") && !prenom.getText().equals("") && !adresse.getText().equals("") && !email.getText().equals("") && !telephone.getText().equals("")
                && !statut.getSelectionModel().isEmpty() && email.getText().contains("@")) {
            return true;
        } else {
            return false;
        }
    }

    //Chargement lors du demarrage pour la modification
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        TempEnseignant enseignantAModifier = VoirListeEnseignantController.enseignaneAModifier;
        id.setText(enseignantAModifier.getTempEnseignant().getId_enseignant());
        nom.setText(enseignantAModifier.getTempEnseignant().getNom_enseignant());
        prenom.setText(enseignantAModifier.getTempEnseignant().getPrenom_enseignant());
        adresse.setText(enseignantAModifier.getTempEnseignant().getAdresse_enseignant());
        email.setText(enseignantAModifier.getTempEnseignant().getEmail_enseignant());
        telephone.setText(String.valueOf(enseignantAModifier.getTempEnseignant().getTelephone()));

       ArrayList<String> status = new ArrayList<>();
        status.add("Vacataire");
        status.add("Permanent");

        ObservableList<Object> list = FXCollections.observableArrayList(status);

        statut.setItems((ObservableList) list);
        statut.getSelectionModel().selectFirst();

    }

}
