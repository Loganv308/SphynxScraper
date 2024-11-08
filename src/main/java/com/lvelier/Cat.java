package com.lvelier;

public class Cat {
    private String url; // "More photos" button pertaining to each cat
    private String type; // Type of cat (Black and white female, chocolate and white male, etc)
    private String dob; // Each cats date of birth
    private String price; // Price of each cat

    public void setUrl(String url) {
        this.url = url;
    }
    
    public void setType(String type) {
        this.type = type;
    }
    
    public void setDob(String dob) {
        this.dob = dob;
    }
    
    public void setPrice(String price) {
        this.price = price;
    }

    public String getType() {
        return this.type;
    }

    public String getPrice() {
        return this.price;
    }

    @Override
    public String toString() {
        return "{ \"url\":\"" + url + "\", " 
				+ " \"type\": \"" + type + "\", " 
				+ "\"dob\":\"" + dob + "\", " 
				+ "\"price\": \"" + price + "\", }";
               
    }

    public String toDisplayString() {
        String display = "Cat type: " + type +".\n" + dob +".\n" + price +".\n" + "URL: " + url +".\n";
        return display; 
    }
}
