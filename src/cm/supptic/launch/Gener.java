/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cm.supptic.launch;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;

/**
 *
 * @author Noumodong
 */
public class Gener {

    public static void main(String[] args) throws Exception {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, new FileOutputStream("lands2.pdf"));

        document.open();
        Image image = Image.getInstance("logo_supptic.jpg");
        image.scalePercent(15, 15);
        image.setAlignment(5);
        document.add(image);

        document.add(new Paragraph("Ecole Nationale Supérieure des Postes des Télécommunications et des TIC", FontFactory.getFont(FontFactory.TIMES_BOLD, 13, Font.BOLD, BaseColor.BLACK)));
        document.add(new Paragraph("                  "));
        document.add(new Paragraph("Ingénieur des Travaux des Télécommunications niveau 3 -semestre 6 -option : Informatique et Réseaux.", FontFactory.getFont(FontFactory.TIMES_BOLD, 11, Font.NORMAL, BaseColor.BLACK)));
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
        table.addCell("");
        table.addCell("");
        table.addCell("");

        table.addCell("M02");
        table.addCell("");
        table.addCell("");
        table.addCell("");
        table.addCell("");
        
        table.addCell("M03");
        table.addCell("");
        table.addCell("");
        table.addCell("");
        table.addCell("");
        
        table.addCell("J04");
        table.addCell("");
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
        table.addCell("");
        table.addCell("");
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
        table.addCell("");
        
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
        table.addCell("");
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
        table.addCell("");
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
        table.addCell("");
        table.addCell("");
        table.addCell("");
        table.addCell("");
        
        table.addCell("V26");
        table.addCell("");
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
        table.addCell("");
        table.addCell("");
        table.addCell("");
        table.addCell("");
        

        document.add(table);

        document.close();
        System.out.println("Save succed ... :)");
    }
}
