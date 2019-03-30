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
import com.jfoenix.controls.JFXSnackbar;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

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

    @FXML
    private Pane panSnack;
    @FXML
    private Text errorText;

    @FXML
    void enregistrerCliquer(MouseEvent event) throws SQLException, ClassNotFoundException {
        try {
            if (rienEstVide()) {
                Enseignant enseignant = new Enseignant(id.getText(), nom.getText(), prenom.getText(), adresse.getText(), email.getText(),
                        Integer.valueOf(telephone.getText()), statut.getSelectionModel().getSelectedItem().toString());

                new DAOEnseignants().addProduit(enseignant, new AvoirConnection().getConnnection());

                JFXSnackbar snac = new JFXSnackbar(this.panSnack);
                snac.getStyleClass().add("jfx-snackbar-content");
                snac.show("Nouvel enseigant ajouté avec succès", 5000);
                vider();
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

    @FXML
    void viderCliquer(MouseEvent event) {
        vider();
    }

    public void vider() {
        nom.setText("");
        prenom.setText("");
        adresse.setText("");
        email.setText("");
        telephone.setText("");
        statut.setId("");
    }

    public boolean rienEstVide() {

        if (!id.getText().equals("") && !nom.getText().equals("") && !prenom.getText().equals("") && !adresse.getText().equals("")
                && !email.getText().equals("") && !telephone.getText().equals("") && !statut.getSelectionModel().getSelectedItem().toString().equals("")) {
            return true;
        } else {
            return false;
        }
    }
}
