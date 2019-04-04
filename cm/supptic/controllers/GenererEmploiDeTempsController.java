/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cm.supptic.controllers;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.List;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author Noumodong
 */
public class GenererEmploiDeTempsController implements Initializable {

    @FXML
    private AnchorPane panFocus;
    @FXML
    private JFXComboBox<?> cycleText;
    @FXML
    private JFXComboBox<?> niveauTxet;
    @FXML
    private JFXComboBox<?> optionText;
    @FXML
    private JFXComboBox<?> MoisText;
    @FXML
    private JFXTextField matiere;
    @FXML
    private Text errorText;
    @FXML
    private JFXComboBox<?> semestreText;
    String Chemin;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ArrayList<String> cycle = new ArrayList<>();
        cycle.add("ITT");
        cycle.add("IPT");
        ObservableList<Object> cycleList = FXCollections.observableArrayList(cycle);
        cycleText.setItems((ObservableList) cycleList);
        cycleText.getSelectionModel().selectFirst();

        ArrayList<String> niveau = new ArrayList<>();
        niveau.add("I");
        niveau.add("II");
        niveau.add("III");
        niveau.add("MI");
        niveau.add("MII");

        ObservableList<Object> niveauList = FXCollections.observableArrayList(niveau);
        niveauTxet.setItems((ObservableList) niveauList);
        niveauTxet.getSelectionModel().selectFirst();

        ArrayList<String> option = new ArrayList<>();
        option.add("IR");
        option.add("RT");
        option.add("RC");
        option.add("SERES");

        ObservableList<Object> optionLst = FXCollections.observableArrayList(option);
        optionText.setItems((ObservableList) optionLst);
        optionText.getSelectionModel().selectFirst();

        ArrayList<String> mois = new ArrayList<>();
        mois.add("Janvier");
        mois.add("Février");
        mois.add("Mars");
        mois.add("Avril");
        mois.add("Mai");
        mois.add("Juin");
        mois.add("Juillet");
        mois.add("Août");
        mois.add("Septembre");
        mois.add("Octobre");
        mois.add("Novembre");
        mois.add("Decembre");
        ObservableList<Object> moisList = FXCollections.observableArrayList(mois);
        MoisText.setItems((ObservableList) moisList);
        MoisText.getSelectionModel().selectFirst();

        ArrayList<String> semestre = new ArrayList<>();
        semestre.add("I");
        semestre.add("II");
        semestre.add("II");
        semestre.add("IV");
        semestre.add("V");
        semestre.add("VI");
        semestre.add("VII");
        semestre.add("VIII");

        ObservableList<Object> SemesList = FXCollections.observableArrayList(semestre);
        semestreText.setItems((ObservableList) SemesList);
        semestreText.getSelectionModel().selectFirst();

    }

    @FXML
    private void GénérerCliquer(MouseEvent event) throws Exception {
        try{
        if (isEmpty()) {
            creerPDFEmploiTemps(cycleText.getSelectionModel().getSelectedItem().toString(), niveauTxet.getSelectionModel().getSelectedItem().toString(), optionText.getSelectionModel().getSelectedItem().toString(),
                     MoisText.getSelectionModel().getSelectedItem().toString(), semestreText.getSelectionModel().getSelectedItem().toString(),
                     matiere.getText());
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
        }}finally{
            Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + this.Chemin);
        }
    }

    public void creerPDFEmploiTemps(String cycle, String niveau, String option, String mois, String semestre, String matiere) throws DocumentException, FileNotFoundException, BadElementException, IOException {

        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, new FileOutputStream("C:\\emploiDeTempsSUPPTIC\\emploiDeTemps cycle" + cycle + " niveau " + niveau + "semestre " + semestre + " " + matiere +".pdf"));
        this.Chemin="C:\\emploiDeTempsSUPPTIC\\emploiDeTemps cycle" + cycle + " niveau " + niveau + "semestre " + semestre +" " + matiere +".pdf";

        document.open();
        Image image = Image.getInstance("logo_supptic.jpg");
        image.scalePercent(15, 15);
        image.setAlignment(5);
        document.add(image);

        document.add(new Paragraph("Ecole Nationale Supérieure des Postes des Télécommunications et des TIC", FontFactory.getFont(FontFactory.TIMES_BOLD, 13, Font.BOLD, BaseColor.BLACK)));
        document.add(new Paragraph("                  "));
        document.add(new Paragraph(cycle + " niveau " + niveau + " -semestre " + semestre + " -option : " + option + "", FontFactory.getFont(FontFactory.TIMES_BOLD, 11, Font.NORMAL, BaseColor.BLACK)));
        document.add(new Paragraph("                  "));
        document.add(new Paragraph("                  "));

        PdfPTable table = new PdfPTable(5);
//        PdfPCell cell = new PdfPCell(new Paragraph("report".toUpperCase()));
//        cell.setColspan(8);
//        cell.setPadding(10);
//        cell.setBackgroundColor(BaseColor.WHITE);
//        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
//        table.addCell(cell);

        //load table with somes elements
        table.addCell("Horaire/date");
        table.addCell("07H30-09H30");
        table.addCell("09H40-11H40");
        table.addCell("12H30-14H30");
        table.addCell("14H40-16H40");

        table.addCell("L01");
        table.addCell("");
        table.addCell(matiere);
        table.addCell("");
        table.addCell("");

        table.addCell("M02");
        table.addCell("");
        table.addCell("");
        table.addCell("");
        table.addCell("");

        table.addCell("M03");
        table.addCell(matiere);
        table.addCell("");
        table.addCell("");
        table.addCell("");

        table.addCell("J04");
        table.addCell(matiere);
        table.addCell("");
        table.addCell("");
        table.addCell("");

        table.addCell("V05");
        table.addCell("");
        table.addCell("");
        table.addCell("");
        table.addCell("");

        table.addCell("S06");
        table.addCell("");
        table.addCell("");
        table.addCell("");
        table.addCell("");

        table.addCell("D07");
        table.addCell("");
        table.addCell("");
        table.addCell("");
        table.addCell("");

        table.addCell("L08");
        table.addCell("");
        table.addCell("");
        table.addCell("");
        table.addCell("");

        table.addCell("M09");
        table.addCell(matiere);
        table.addCell(matiere);
        table.addCell("");
        table.addCell("");

        table.addCell("M10");
        table.addCell("");
        table.addCell("");
        table.addCell("");
        table.addCell("");

        table.addCell("J11");
        table.addCell("");
        table.addCell("");
        table.addCell("");
        table.addCell("");

        table.addCell("V12");
        table.addCell("");
        table.addCell("");
        table.addCell("");
        table.addCell(matiere);

        table.addCell("S13");
        table.addCell("");
        table.addCell("");
        table.addCell("");
        table.addCell("");

        table.addCell("D14");
        table.addCell("");
        table.addCell("");
        table.addCell("");
        table.addCell("");

        table.addCell("L15");
        table.addCell("");
        table.addCell("");
        table.addCell("");
        table.addCell("");

        table.addCell("M16");
        table.addCell("");
        table.addCell("");
        table.addCell("");
        table.addCell("");

        table.addCell("M17");
        table.addCell(matiere);
        table.addCell("");
        table.addCell("");
        table.addCell("");

        table.addCell("J18");
        table.addCell("");
        table.addCell("");
        table.addCell("");
        table.addCell("");

        table.addCell("V19");
        table.addCell("");
        table.addCell("");
        table.addCell("");
        table.addCell("");

        table.addCell("S20");
        table.addCell("");
        table.addCell("");
        table.addCell("");
        table.addCell("");

        table.addCell("D21");
        table.addCell("");
        table.addCell("");
        table.addCell("");
        table.addCell("");

        table.addCell("L22");
        table.addCell(matiere);
        table.addCell("");
        table.addCell("");
        table.addCell("");

        table.addCell("M23");
        table.addCell("");
        table.addCell("");
        table.addCell("");
        table.addCell("");

        table.addCell("M24");
        table.addCell("");
        table.addCell("");
        table.addCell("");
        table.addCell("");

        table.addCell("J25");
        table.addCell(matiere);
        table.addCell("");
        table.addCell("");
        table.addCell("");

        table.addCell("V26");
        table.addCell(matiere);
        table.addCell("");
        table.addCell("");
        table.addCell("");

        table.addCell("S27");
        table.addCell("");
        table.addCell("");
        table.addCell("");
        table.addCell("");

        table.addCell("D28");
        table.addCell("");
        table.addCell("");
        table.addCell("");
        table.addCell("");

        table.addCell("L29");
        table.addCell("");
        table.addCell("");
        table.addCell("");
        table.addCell("");

        table.addCell("M30");
        table.addCell("");
        table.addCell("");
        table.addCell("");
        table.addCell("");

        table.addCell("M311");
        table.addCell(matiere);
        table.addCell(matiere);
        table.addCell("");
        table.addCell("");

        document.add(table);

        document.close();
        System.out.println("Save succed ... :)");

    }

    public boolean isEmpty() {
        if (!matiere.getText().equals("")) {
            return true;
        } else {
            return false;
        }
    }
}
