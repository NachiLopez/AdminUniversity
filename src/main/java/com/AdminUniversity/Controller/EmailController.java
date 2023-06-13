package com.AdminUniversity.Controller;


import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.Properties;


public class EmailController {
    public static void sendEmail(String destination, String title, File file)  {
        Properties props = System.getProperties();
        props.setProperty("mail.smtp.host", "smtp.mailgun.org");
        props.put("mail.smtp.auth", "true");

        Session session = Session.getDefaultInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("university@mail.jmarango.co",
                        "c13aabc1a5c69f2bb474c50a8831e853-af778b4b-dace6b6e");

            }
        });

        try {
            MimeMessage message = new MimeMessage(session);
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(destination));
            message.setSubject(title);

            message.setDataHandler(new DataHandler(new FileDataSource(file)));
            message.setFileName(file.getName());

            Transport.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
