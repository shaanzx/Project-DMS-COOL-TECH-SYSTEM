package lk.ijse.pos.util;


import javafx.scene.control.Alert;

import javax.mail.*;
import javax.mail.internet.*;
import java.io.File;
import java.io.IOException;
import java.util.Properties;

public class Mail {
    private static Session newSession = null;

    private static void setUpServerProperties() {
        Properties properties = new Properties();
        properties.put("mail.smtp.port", "587"); // Use TLS port
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true"); // Enable STARTTLS

        newSession = Session.getInstance(properties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(
                        "anuhaslakshan07@gmail.com", "zona ualj clqg sslr");
            }
        });
    }

   /* public static void setOtpMail(String receiverMail, String otp) throws MessagingException, IOException {
        setUpServerProperties();
        MimeMessage mimeMessage = draftOtpMail(receiverMail, otp);
        sendOtpMail(mimeMessage);
    }

    private static MimeMessage draftOtpMail(String receiverMail, String otp) throws MessagingException, IOException {
        MimeMessage mimeMessage = new MimeMessage(newSession);

        mimeMessage.setFrom(new InternetAddress("kumudufurniture275@gmail.com"));
        mimeMessage.addRecipients(Message.RecipientType.TO, receiverMail);
        mimeMessage.setSubject("One Time Password!");

        MimeBodyPart bodyPart = new MimeBodyPart();
        bodyPart.setContent("Your One Time Password is: " + otp + ".", "text/html");

        MimeMultipart multipart = new MimeMultipart(); //mime msg's body
        multipart.addBodyPart(bodyPart);

        mimeMessage.setContent(multipart);

        return mimeMessage;
    }

    private static void sendOtpMail(MimeMessage mimeMessage) throws MessagingException {
        String host = "smtp.gmail.com";
        Transport transport = newSession.getTransport("smtp");
        try {
            transport.connect(host, System.getenv("anuhaslakshan07@gmail.com"), System.getenv("zona ualj clqg sslr"));
            transport.sendMessage(mimeMessage, mimeMessage.getAllRecipients());
            new Alert(Alert.AlertType.INFORMATION, "OTP sent successfully!").show();
        } finally {
            transport.close();
        }
    }*/

    public static void setMail(String title, String subject, String body, String receiverMail, File pdfFile) throws MessagingException, IOException {
        setUpServerProperties();
        MimeMessage mimeMessage = draftMail(title, subject, body, receiverMail, pdfFile);
        sendMail(mimeMessage);
    }

    private static void sendMail(MimeMessage mimeMessage) throws MessagingException {
        String host = "smtp.gmail.com";
        Transport transport = newSession.getTransport("smtp");
        try {
            transport.connect(host, System.getenv("anuhaslakshan07@gmail.com"), System.getenv("zona ualj clqg sslr"));
            transport.sendMessage(mimeMessage, mimeMessage.getAllRecipients());
            new Alert(Alert.AlertType.INFORMATION, "Email sent successfully!").show();
        } finally {
            transport.close();
        }
    }

    private static MimeMessage draftMail(String title, String sub, String body, String receiverMail, File pdfFile) throws MessagingException, IOException {
        MimeMessage mimeMessage = new MimeMessage(newSession);

        mimeMessage.setFrom(new InternetAddress("anuhaslakshan07@gmail.com"));
        mimeMessage.addRecipients(Message.RecipientType.TO, receiverMail);
        mimeMessage.setSubject(sub);

        // Create the message part
        MimeBodyPart messageBodyPart = new MimeBodyPart();
        String emailBody = "<h1>" + title + "</h1>" + "<p>" + body + "</p>";
        messageBodyPart.setContent(emailBody, "text/html");

        // Create a multipart message
        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(messageBodyPart);
// Attachment part
        if (pdfFile != null && pdfFile.exists()) {
            MimeBodyPart attachmentBodyPart = new MimeBodyPart();
            attachmentBodyPart.attachFile(pdfFile);
            multipart.addBodyPart(attachmentBodyPart);
        }

        // Send the complete message parts
        mimeMessage.setContent(multipart);

        return mimeMessage;
    }
}
