package com.lvelier;

import java.util.List;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.BodyPart;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class EmailSender {

    private EmailAccount emailAccount;
    private String subject;
    private String recipient;
    private List<Cat> content;

    public EmailSender(EmailAccount emailAccount, String subject, String recipient, List<Cat> content) {
        this.emailAccount = emailAccount;
        this.subject = subject;
        this.recipient = recipient;
        this.content = content;
    }

    public EmailLoginResult login() {
        Authenticator authenticator = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(emailAccount.getAddress(), emailAccount.getPassword());
            }
        };

        try {
            //Thread.sleep(6000);
            Session session = Session.getInstance(emailAccount.getProperties(), authenticator);
            emailAccount.setSession(session);
        } catch (Exception e) {
            e.printStackTrace();
            return EmailLoginResult.FAILED_BY_UNEXPECTED_ERROR;
        }
        return EmailLoginResult.SUCCESS;
    }

    public EmailSendingResult sendEmail() {
        try {
            MimeMessage mimeMessage = new MimeMessage(emailAccount.getSession());
            mimeMessage.setFrom(emailAccount.getAddress());
            mimeMessage.addRecipients(Message.RecipientType.TO, recipient);
            mimeMessage.setSubject(subject);

            Multipart multipart = new MimeMultipart();
            BodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setContent(content, "text/html");
            multipart.addBodyPart(messageBodyPart);
            mimeMessage.setContent(multipart);

            Transport transport = emailAccount.getSession().getTransport();
            transport.connect(
                emailAccount.getProperties().getProperty("mail.smtp.host"),
                emailAccount.getAddress(),
                emailAccount.getPassword()
            );
            transport.sendMessage(mimeMessage, mimeMessage.getAllRecipients());
            transport.close();
            return EmailSendingResult.SUCCESS;

        } catch(MessagingException e) {
            e.printStackTrace();
            return EmailSendingResult.FAILED_BY_PROVIDER;
        } catch(Exception e) {
            e.printStackTrace();
            return EmailSendingResult.FAILED_BY_UNEXPECTED_ERROR;
        }
    }
}
