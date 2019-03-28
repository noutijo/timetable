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
    private JFXComboBox<?> code_categorie;

    @FXML
    void closeFenModifierCliquer(MouseEvent event) {
        BoxBlur ff = new BoxBlur(0, 0, 0);
        VoirListeEnseignantController.panFocuss.setEffect(ff);
        VoirListeEnseignantController.diaglogFocus.close();
    }

    //Enregistrer les modifications
    @FXML
    void enregistrerModificationCliquer(MouseEvent event) throws SQLException, ClassNotFoundException {
        
        Enseignant enseignant = new Enseignant(id.getText(), nom.getText(), prenom.getText(), adresse.getText(), email.getText(),
                Integer.valueOf(telephone.getText()), statut.getSelectionModel().getSelectedItem().toString());

        new DAOEnseignants().modifierEnseignant(enseignant, new AvoirConnection().getConnnection());
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

        String[] locales = Locale.getISOCountries();
        ArrayList<String> status = new ArrayList<>();
        status.add("Vacataire");
        status.add("Permanent");
//        for (String countryCode : locales) {
//            Locale obj = new Locale("fr", countryCode);
//            pays.add(obj.getDisplayCountry(obj));
//
//        }
        ObservableList<Object> list = FXCollections.observableArrayList(status);

        statut.setItems((ObservableList) list);
    }

}
