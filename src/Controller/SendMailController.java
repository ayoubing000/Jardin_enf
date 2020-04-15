/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.net.URL;
import java.util.Properties;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;

/**
 * FXML Controller class
 *
 * @author ayoub
 */
public class SendMailController implements Initializable {

    @FXML
    private TextField nom;
    @FXML
    private Button btnsend;
    @FXML
    private TextField email;
    @FXML
    private TextField subject;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void sendMail(ActionEvent event) {
       final String fromEmail = "nikfinoum@gmail.com"; //requires valid gmail id
       //final String username = "463a28409b6e1c2d4bfd6092b29500ee";
        final String password = "3asba.com"; // correct password for gmail id
	 final String toEmail = "ayoub.bousselmi@esprit.tn"; // can be any email id 
		
		System.out.println("TLS Email Start");
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com"); //SMTP Host
               props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
		props.put("mail.smtp.port", "587"); //TLS Port
		props.put("mail.smtp.auth", "true"); //enable authentication
		props.put("mail.smtp.starttls.enable", "true"); //enable STARTTLS
		
                //create Authenticator object to pass in Session.getInstance argument
               
		Authenticator auth = new Authenticator() {
			//override the getPasswordAuthentication method
                        @Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(fromEmail,password);
			}
		};
		Session session = Session.getInstance(props, auth);
		
		Mailing.sendEmail(session, toEmail,subject.getText(),email.getText());
    }
    
}
