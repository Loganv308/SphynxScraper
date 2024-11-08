package com.lvelier;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        
        Scraper scraper = new Scraper();
        
        EmailAccount emailAccount = new EmailAccount("", "");

        List<Cat> cats = scraper.run();

        EmailSender emailSender = new EmailSender(emailAccount, "Kittys Available", "loganv308@gmail.com", cats);

        EmailLoginResult emailLoginResult = emailSender.login();

        switch (emailLoginResult) {
            case SUCCESS:
                if (cats.isEmpty()) {
                    System.out.println("No available cats.");
                    
                } else {
                    for (Cat cat : cats) {
                        System.out.println(cat.toDisplayString());
                    }
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
    }
}