package com.lvelier;

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
    private String content;

    public EmailSender(EmailAccount emailAccount, String subject, String recipient) {
        this.emailAccount = emailAccount;
        this.subject = subject;
        this.recipient = recipient;
    }

    public void setContent(String content) {
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
            //System.out.println("" + emailAccount.getSession());
        } catch (Exception e) {
            e.printStackTrace();
            return EmailLoginResult.FAILED_BY_UNEXPECTED_ERROR;
        }
        return EmailLoginResult.SUCCESS;
    }

    public EmailSendingResult sendEmail() {
        if(!content.isEmpty()) {
            try {
                MimeMessage mimeMessage = new MimeMessage(emailAccount.getSession());
                mimeMessage.setFrom(emailAccount.getAddress());
                mimeMessage.addRecipients(Message.RecipientType.TO, recipient);
                mimeMessage.setSubject(subject);

                StringBuilder htmlContent = new StringBuilder();
                htmlContent.append("<html><body>" + content + "</body></html>");

                Multipart multipart = new MimeMultipart();
                BodyPart messageBodyPart = new MimeBodyPart();
                messageBodyPart.setContent(htmlContent.toString(), "text/html");
                multipart.addBodyPart(messageBodyPart);
                mimeMessage.setContent(multipart);

                //System.out.println("emailAccount.getSession().getTransport(): " + emailAccount.getSession().getTransport());
                //System.out.println("Transport: " + emailAccount);
                Transport transport = emailAccount.getSession().getTransport();
                //System.out.println(emailAccount.getAddress() + " " + emailAccount.getPassword());
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
            }  
        }
        return EmailSendingResult.NO_CONTENT;
    }
}
