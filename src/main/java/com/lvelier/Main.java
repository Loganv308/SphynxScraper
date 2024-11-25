package com.lvelier;

public class Main {
    public static void main(String[] args) {
        
        Scraper scraper = new Scraper();

        CredentialManager creds = new CredentialManager(null, null, null, null);
        
        creds.createConfigFile();

        creds.setProperties();
        
        EmailAccount emailAccount = new EmailAccount(creds.getUsername(), creds.getPassword());

        EmailSender emailSender = new EmailSender(emailAccount, creds.getSubject(), creds.getRecipient());

        System.out.println(creds.toString());

        EmailLoginResult emailLoginResult = emailSender.login();

        while (true) {
            String cats = scraper.run();
            emailSender.setContent(cats);
            switch (emailLoginResult) {
                case SUCCESS:
                    if (cats.isBlank()) {
                        System.out.println("No available cats.");
                    } else {
                        System.out.println(cats);
                    }
                    emailSender.sendEmail();
                    break;
                case FAILED_BY_CREDENTIALS:
                    System.out.println("Failed by credentials");
                    break;
                case FAILED_BY_UNEXPECTED_ERROR:
                    System.out.println("Failed by unexpected Error");
                    break;
                default:
                    return;
            } 
            try {
                Thread.sleep(1000*60*60*2);
                //Thread.sleep(6000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } 
    }
}