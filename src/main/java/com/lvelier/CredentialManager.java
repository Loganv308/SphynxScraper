package com.lvelier;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

public class CredentialManager {

    private String username;
    private String password;
    private String subject;
    private String recipient;

    private String PATH = "src\\main\\java\\com\\lvelier\\credentials.properties";

    // Constructor
    public CredentialManager(String username, String password, String subject, String recipient) {
        this.username = username;
        this.password = password;
        this.subject = subject;
        this.recipient = recipient;
    }

    @Override
    public String toString() {
        return "Email: " + username + " | " + "Password: " + password + " | " + "Subject: " + subject + " | " + "Recipient: " + recipient ;
    }

    public String createConfigFile() {
        try {            
            File file = new File(PATH);
            
            FileWriter fileWriter;

            if (file.createNewFile()) {
                System.out.println("File created: " + file.getName());

                fileWriter = new FileWriter("src\\main\\java\\com\\lvelier\\credentials.properties");
                
                fileWriter.write("email=" + "\n");
                fileWriter.write("password=" + "\n");
                fileWriter.write("subject=" + "\n");
                fileWriter.write("recipient=" + "\n");

                fileWriter.close();

                System.exit(0);
            } else {
                System.out.println("File already created");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return PATH;
    }

    public Properties setProperties() {

        Properties props = new Properties();

        try (FileInputStream input = new FileInputStream(PATH)) {
            props.load(input);

            this.username = props.getProperty("email");
            this.password = props.getProperty("password");
            this.subject = props.getProperty("subject");
            this.recipient = props.getProperty("recipient");

        } catch (IOException e) {
            e.printStackTrace();
        }

        return props;
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
