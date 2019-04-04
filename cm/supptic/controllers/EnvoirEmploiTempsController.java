/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cm.supptic.controllers;

import cm.supptic.DAOcontroller.DAOEnseignants;
import cm.supptic.avoirConnection.AvoirConnection;
import cm.supptic.managerClass.Enseignant;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;
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
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

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
    @FXML
    private JFXPasswordField passWord;

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
        if (isEmpty()) {
            envoyerLesParaEnoir(expediteurText.getText(), passWord.getText(), destinaireText.getText(), sujetText.getText(), messageArea.getText(), "emploi de temps", new File(selectionText.getText()));

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
     * Vérifier si les champs sont libres
     *
     * @return
     */
    public boolean isEmpty() {
        if (!expediteurText.getText().equals("") && !passWord.getText().equals("") && !destinaireText.getText().equals("") && !selectionText.getText().equals("") && expediteurText.getText().contains("@")) {
            return true;
        } else {
            return false;
        }
    }
    //methode de l'envoir

    public void envoyerLesParaEnoir(String userAddr, String userpass, String destination, String subject, String messageArea, String fileName, File path) {

        final String username = userAddr;
        final String password = userpass;

        Properties props = System.getProperties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", 587);

        Session session = Session.getDefaultInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(userAddr));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(destination));
            message.setSubject(subject);
            BodyPart messageBordypart = new MimeBodyPart();
            messageBordypart.setText(messageArea);
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBordypart);
            messageBordypart = new MimeBodyPart();

            //attacher un fichier ------------------------------------------------------------------
            DataSource source = new FileDataSource(path);
            messageBordypart.setDataHandler(new DataHandler(source));
            messageBordypart.setFileName(fileName);
            multipart.addBodyPart(messageBordypart);

            message.setContent(multipart);

            System.out.println("sending ...");
            Transport.send(message);
            System.out.println("Done!");

        } catch (MessagingException ex) {
            ex.printStackTrace();
        }
    }

    //fin de la methode de l'envoir
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
        if (!(this.selectionText.getText().isEmpty())) {
            fileChooser.setInitialDirectory(new File(this.selectionText.getText().substring(0, this.selectionText.getText().lastIndexOf("\\"))));
        } else {

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
