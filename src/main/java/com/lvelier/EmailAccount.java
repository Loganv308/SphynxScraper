package com.lvelier;

import java.util.Properties;

import javax.mail.Session;

public class EmailAccount {
    
    private String address;
    private String password;
    private Properties properties;
    private Session session;

    public EmailAccount(String address, String password) {
        this.address = address;
        this.password = password;

        properties = new Properties();
        properties.put("mail.transport.protocol", "smtp");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com"); // Replace with actual SMTP server if different
        properties.put("mail.smtp.port", "587"); // Port may vary depending on the SMTP server
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.ssl.protocols", "TLSv1.2"); // Explicitly set the TLS version
        properties.put("mail.smtp.ssl.trust", "smtp.gmail.com");

    }
    
    @Override
    public String toString() {
        return address;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Properties getProperties() {
        return properties;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }
}
