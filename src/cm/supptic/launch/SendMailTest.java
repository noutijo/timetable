/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cm.supptic.launch;

/**
 *
 * @author Noumodong
 */
import java.util.Properties;
import javax.activation.*;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class SendMailTest {
    
    public void send2() {
        
        final String username = "oreolnoumodong@gmail.com";
        final String password = "bihoncare2018";
        
        Properties props = System.getProperties();
        props.put("mail.smtp.auth", "true");
       // props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", 587);
        
        Session session = Session.getDefaultInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
        
        try {
            
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("oreolnoumodong@gmail.com"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("oreolnoumodong@gmail.com"));
            message.setSubject("Emploi de temps.");
            BodyPart messageBordypart = new MimeBodyPart();
            messageBordypart.setText("C'est le message ici ...");
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBordypart);
            messageBordypart=new MimeBodyPart();

            //attache 1 ------------------------------------------------------------------
            String file = "G:\\pfe.pdf";
            String fileName = "PFE";
            DataSource source = new FileDataSource(file);
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
}
