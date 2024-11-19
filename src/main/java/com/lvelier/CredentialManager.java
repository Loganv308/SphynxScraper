package com.lvelier;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class CredentialManager {

    private String username;
    private String password;
    private String subject;
    private String recipient;

    // Constructor
    public CredentialManager() {

        Properties props = new Properties();

        try (FileInputStream input = new FileInputStream("src\\main\\java\\com\\lvelier\\credentials.properties")) {

            this.username = props.getProperty("email");
            this.password = props.getProperty("password");
            this.subject = props.getProperty("subject");
            this.recipient = props.getProperty("recipient");

            props.load(input);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "Email: " + username + " | " + "Password: " + password + " | " + "Subject: " + subject + " | " + "Recipient: " + recipient + " | ";
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

}
