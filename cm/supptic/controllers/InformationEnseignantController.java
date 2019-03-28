/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cm.supptic.controllers;

import cm.supptic.subclass.TempEnseignant;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.print.JobSettings;
import javafx.print.PageLayout;
import javafx.print.PrinterJob;
import javafx.scene.effect.BoxBlur;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

/**
 *
 * @author Noumodong
 */
public class InformationEnseignantController implements Initializable {

    @FXML
    private AnchorPane panToPrint;

    @FXML
    private Text textId;

    @FXML
    private Text textNom;

    @FXML
    private Text textPrenom;

    @FXML
    private Text textAdresse;

    @FXML
    private Text textEmail;

    @FXML
    private Text textTelephone;
    @FXML
    private Text textStatut;

    @FXML
    private Text textCodeCategorie;

    @FXML
    void closeFenModifierCliquer(MouseEvent event) {
        BoxBlur ff = new BoxBlur(0, 0, 0);
        VoirListeEnseignantController.panFocuss.setEffect(ff);
        VoirListeEnseignantController.diaglogFocus.close();
    }

    @FXML
    void imprimerCliquer(MouseEvent event) {
        PrinterJob printerJob = PrinterJob.createPrinterJob();

        if (null != printerJob) {
            boolean proceed = printerJob.showPageSetupDialog(null);
            if (proceed) {

                // Print the node
                boolean printed = printerJob.printPage(panToPrint);
                if (printed) {
// End the printer job
                    printerJob.endJob();
                } else {
                    System.out.println("Printing failed.");
                }
            }

        } else {
            System.out.println("Could not create a printer job.");
        }
    }

    //On charge l'ensignant à modifier des le demarrage
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        TempEnseignant enseignantAModifier = VoirListeEnseignantController.enseignaneAModifier;
        textId.setText(enseignantAModifier.getTempEnseignant().getId_enseignant());
        textNom.setText(enseignantAModifier.getTempEnseignant().getNom_enseignant());
        textPrenom.setText(enseignantAModifier.getTempEnseignant().getPrenom_enseignant());
        textAdresse.setText(enseignantAModifier.getTempEnseignant().getAdresse_enseignant());
        textEmail.setText(enseignantAModifier.getTempEnseignant().getEmail_enseignant());
        textTelephone.setText(String.valueOf(enseignantAModifier.getTempEnseignant().getTelephone()));
        textStatut.setText(enseignantAModifier.getTempEnseignant().getStatut_enseignant());
      
    }

}
