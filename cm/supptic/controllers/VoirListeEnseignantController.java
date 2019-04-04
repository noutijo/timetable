/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cm.supptic.controllers;

import cm.supptic.managerClass.Enseignant;
import cm.supptic.DAOcontroller.DAOEnseignants;
import cm.supptic.avoirConnection.AvoirConnection;
import cm.supptic.subclass.TempEnseignant;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXTextField;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.BoxBlur;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.util.Duration;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 *
 * @author Noumodong
 */
public class VoirListeEnseignantController implements Initializable {

    @FXML
    private TableView<Enseignant> tableEnseignant;

    @FXML
    private TableColumn<Enseignant, String> idColumn;

    @FXML
    private TableColumn<Enseignant, String> nomColumn;

    @FXML
    private TableColumn<Enseignant, String> prenomsColumn;

    @FXML
    private TableColumn<Enseignant, String> adresseColumn;

    @FXML
    private TableColumn<Enseignant, String> emailColumn;

    @FXML
    private TableColumn<Enseignant, Integer> telephoneColumn;

    @FXML
    private TableColumn<Enseignant, String> statutColumn;

    @FXML
    private Pane panSnackbar;
    String idSupprimer;
    public static TempEnseignant enseignaneAModifier = null;
    @FXML
    private JFXTextField nomRecherche;
    @FXML
    private Text demandeUneSelection;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ChargementableEnseignant();
    }

    /**
     *
     */
    public void ChargementableEnseignant() {

        //Chargement de la table enseignant lors du démarrage
        try {
            ObservableList<Enseignant> data = FXCollections.observableArrayList(new DAOEnseignants().getAllEnseignants(new AvoirConnection().getConnnection()));
            chargerEnsembleEnseignants(data);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    void actualiserCliquer(MouseEvent event) {
        ChargementableEnseignant();
    }

    //Nous voulons supprimer un enseignant
    @FXML
    void supprimerCliquer(MouseEvent event) throws Exception {
        if (retourneId() == null) {

        } else {
            confirmeLaSuppression(retourneId());
        }
    }

    /**
     *
     * @param id
     * @throws SQLException
     */
    public void confirmeLaSuppression(String id) throws Exception {

        new DAOEnseignants().effacerProduit(id, new AvoirConnection().getConnnection());

        NotificationType notification = NotificationType.CUSTOM;

        TrayNotification tray = new TrayNotification();
        tray.setTitle("Confirmation de suppression");
        tray.setImage(new Image(getClass().getResource("/cm/supptic/images/logo_supptic.jpg").toString()));
        tray.setMessage("Enseignant dont id :" + id + " supprimer avec succès");
        tray.setNotificationType(notification);
        tray.showAndDismiss(Duration.millis(5000));
        ChargementableEnseignant();

    }
    //Modifier un enseignant
    public static JFXDialog diaglogFocus;
    @FXML
    private StackPane panStackFocus;

    @FXML
    private AnchorPane panFocus;
    //variable pour enlever le focus
    public static AnchorPane panFocuss;

    //Pour l'impression de la table enseignant
    @FXML
    void imprimerCliquer(MouseEvent event) throws BadElementException, IOException {
        Rectangle pagesize = new Rectangle(400f, 720f);
        String file_name = "C:\\Users\\Noumodong\\Documents\\NetBeansProjects\\charts\\src\\itextpdf\\generate_pdf\\listes_enseignantss.pdf";

        try {
            Document document = new Document(PageSize.LETTER.rotate(), 5f, 5f, 5f, 5f);

            PdfWriter.getInstance(document, new FileOutputStream(file_name));

            //Simple paragraph
            document.open();
            /*Paragraph para = new Paragraph("This is the best testing from oreolnoumodong.com ....");
            document.add(para);*/

 /*  document.add(new Paragraph(" "));
            document.add(new Paragraph(" "));*/
            //add table
            //La creation de la table pour l'impression de la table des etudiants 
            PdfPTable table = new PdfPTable(8);
            PdfPCell c1 = new PdfPCell(new Phrase("Id"));
            c1.setBackgroundColor(BaseColor.DARK_GRAY);
            table.addCell(c1);

            c1 = new PdfPCell(new Phrase("Noms"));
            c1.setBackgroundColor(BaseColor.DARK_GRAY);
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(c1);
            c1 = new PdfPCell(new Phrase("Prenoms"));
            c1.setBackgroundColor(BaseColor.DARK_GRAY);
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(c1);
            c1 = new PdfPCell(new Phrase("Adresses"));
            c1.setBackgroundColor(BaseColor.DARK_GRAY);
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(c1);
            c1 = new PdfPCell(new Phrase("Emails"));
            c1.setBackgroundColor(BaseColor.DARK_GRAY);
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(c1);
            c1 = new PdfPCell(new Phrase("Téléphones"));
            c1.setBackgroundColor(BaseColor.DARK_GRAY);
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(c1);
            c1 = new PdfPCell(new Phrase("Statuts"));
            c1.setBackgroundColor(BaseColor.DARK_GRAY);
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(c1);
            
            //On spécifie que la table apres chaque aller de la page suivante doit reddéfinir l'entête d'identification des differentes tâches de la table 
            table.setHeaderRows(1);

            //Chargement de la table apres demande de l'impression de la table des enseignants
            for (Enseignant enseignant : tableEnseignant.getItems()) {
                table.addCell(enseignant.getId_enseignant());
                table.addCell(enseignant.getNom_enseignant());
                table.addCell(enseignant.getPrenom_enseignant());
                table.addCell(enseignant.getAdresse_enseignant());
                table.addCell(enseignant.getEmail_enseignant());
                table.addCell(String.valueOf(enseignant.getTelephone()));
                table.addCell(enseignant.getStatut_enseignant());
            }

            document.add(table);

            //add image
            //document.add(Image.getInstance("C:\\Users\\Noumodong\\Documents\\NetBeansProjects\\charts\\src\\itextpdf\\camera.png"));
            document.close();
            //affiche apres une bonne impression de la table des enseignants
            System.out.println("We have finished to save succed list of teaches :)");

        } catch (DocumentException | FileNotFoundException ex) {
            ex.printStackTrace();
        } finally {
            //Ouverture apres l'impressio de la table des enseignants
            Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + file_name);
        }

    }
    //fin de l'impression de la table enseignant

    @FXML
    void modifierCliquer(MouseEvent event) throws IOException {
        ChargePanFocue("ModierEnseignant.fxml");
    }

    //Avoir les informations sur l'enseignant FXML
    @FXML
    void infosEnseignantCliquer(MouseEvent event) throws IOException {
        ChargePanFocue("informationEnseignant.fxml");
    }

    //chargement du focus
    private void ChargePanFocue(String fxmlPath) throws IOException {
        try {
            //inialisons le panfoncus public
            this.panFocuss = panFocus;
            //Creation de l'objet enseignant pour gerer les affichesndes differents view blur demandés a chaque interaction avec les deux buttons infos et modifier 
            Enseignant itemSelectionne = this.tableEnseignant.getItems().get(this.tableEnseignant.getSelectionModel().getSelectedIndex());
            this.enseignaneAModifier = new TempEnseignant(itemSelectionne);
            //l'effet de rendre les choses  un peu flou apres la demande de l'affichage de l'outil d'aide ou les informations sur le constructeur de l'application
            BoxBlur ff = new BoxBlur(10.0, 10.0, 1);
            panFocus.setEffect(ff);
            Region root = FXMLLoader.load(getClass().getResource("/cm/supptic/fxml/" + fxmlPath));
            diaglogFocus = new JFXDialog(panStackFocus, root, JFXDialog.DialogTransition.CENTER);
            //dia.setBackground(Background.EMPTY);
            diaglogFocus.setOverlayClose(false);
            diaglogFocus.show();
        } catch (Exception e) {
            new Thread(() -> {
                demandeUneSelection.setVisible(true);
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
                demandeUneSelection.setVisible(false);
            }).start();
        }

    }

    //retourn ID selectionner
    /**
     *
     * @return
     */
    private String retourneId() {
        String id = null;
        try {
            this.idSupprimer = this.tableEnseignant.getSelectionModel().getSelectedItem().getId_enseignant();
            id = this.tableEnseignant.getSelectionModel().getSelectedItem().getId_enseignant();

        } catch (Exception e) {
            new Thread(() -> {
                demandeUneSelection.setVisible(true);
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException ex1) {
                }
                demandeUneSelection.setVisible(false);
            }).start();
        }
        return id;
    }

    @FXML
    void recherchePressed(KeyEvent event) {
        loadsearch();
    }

    @FXML
    void rechercheCliquer(MouseEvent event) {
        loadsearch();
    }

    public void loadsearch() {
        try {
            ObservableList<Enseignant> data = FXCollections.observableArrayList(new DAOEnseignants().searchEnseigants(nomRecherche.getText(), new AvoirConnection().getConnnection()));
            chargerEnsembleEnseignants(data);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    //Charger les enseignants dans le tableau
    /**
     *
     * @param data
     */
    public void chargerEnsembleEnseignants(ObservableList<Enseignant> data) {
        try {
            //chargement de la table des enseignants au niveau de l'interface apres la demande de la liste des enseignants
            idColumn.setCellValueFactory(new PropertyValueFactory<>("id_enseignant"));
            nomColumn.setCellValueFactory(new PropertyValueFactory<>("nom_enseignant"));
            prenomsColumn.setCellValueFactory(new PropertyValueFactory<>("prenom_enseignant"));
            adresseColumn.setCellValueFactory(new PropertyValueFactory<>("adresse_enseignant"));
            emailColumn.setCellValueFactory(new PropertyValueFactory<>("email_enseignant"));
            telephoneColumn.setCellValueFactory(new PropertyValueFactory<>("telephone"));
            statutColumn.setCellValueFactory(new PropertyValueFactory<>("statut_enseignant"));

            tableEnseignant.setItems(data);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
