package com.tibame.web.controller;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;


public class SendEmail {
	
	public static void sendEmail(String to, String subject,String text) {
//		String to = "eric543029@gmail.com";
        String from = "eric543029@gmail.com";
        String host = "smtp.gmail.com";

        Properties properties = System.getProperties();
        properties.setProperty("mail.smtp.host", host);
        properties.setProperty("mail.smtp.port", "587");
        properties.setProperty("mail.smtp.auth", "true");
        properties.setProperty("mail.smtp.starttls.enable", "true");

        Session session = Session.getDefaultInstance(properties,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication("eric543029@gmail.com", "gikyttzkvoddzuba");
                    }
                });

        try{
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject("Test Email");
            message.setText("This is a test email");

            Transport.send(message);
            System.out.println("Email sent successfully!");
        }catch (MessagingException mex) {
            mex.printStackTrace();
        }
	}
	
	public static void main(String[] args) {

        
    }
}
