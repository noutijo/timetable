/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cm.supptic.controllers;

import cm.supptic.DAOcontroller.DAOEnseignants;
import cm.supptic.avoirConnection.AvoirConnection;
import cm.supptic.managerClass.Enseignant;
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
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;

/**
 *
 * @author Noumodong
 */
public class AjouterEnseignantController implements Initializable {

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

    private JFXComboBox<?> code_categorie;

    @FXML
    void enregistrerCliquer(MouseEvent event) throws SQLException, ClassNotFoundException {
        try {
            Enseignant enseignant = new Enseignant(id.getText(), nom.getText(), prenom.getText(), adresse.getText(), email.getText(),
                    Integer.valueOf(telephone.getText()), statut.getSelectionModel().getSelectedItem().toString());

            new DAOEnseignants().addProduit(enseignant, new AvoirConnection().getConnnection());
            
        } catch (ClassNotFoundException | NumberFormatException | SQLException ex) {
        Alert alert=new Alert(Alert.AlertType.valueOf("Veillez vérifier vos informatiions"));
        alert.show();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
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

    public void vider() {

    }
}
