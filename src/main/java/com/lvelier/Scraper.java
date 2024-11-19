package com.lvelier;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.*; 
import org.jsoup.nodes.*; 
import org.jsoup.select.*;

public class Scraper {

    public String run() {
        Document doc;

        String baseURL = "https://www.sphynx-cattery.com/";

        try {
            doc = Jsoup.connect("https://www.sphynx-cattery.com/available-kittens.html").get();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        List<Cat> cats = new ArrayList<>();

        Elements catElements = doc.select(".text-side.shrinker-parent");

        int counter = 0;

        for (Element catElement : catElements) {

            if (catElement != null) {
                //System.out.println(catElement);

                String relativeURL = catElement.selectFirst("a") != null ? catElement.selectFirst("a").attr("href") : null;
                if (relativeURL != null) {
                    Cat cat = new Cat();
                    counter++;

                    String absoluteURL = baseURL + relativeURL;

                    cat.setUrl(absoluteURL);
                    cat.setDob(catElement.selectFirst("h3") == null ? "" : catElement.selectFirst("h3").text());
                    cat.setType(catElement.selectFirst("h2") == null ? "" : catElement.selectFirst("h2").text());
                    cat.setPrice(catElement.selectFirst("p") == null ? "" : catElement.selectFirst("p").text());

                    cats.add(cat);
                }
            }
        }
        System.out.println("***Total cats: " + counter);
        
        cats.toString();

        String result = "";

        // Loop to filter if there are new cats or not. 
        for (Cat cat : cats) {
            System.out.println(cat.toDisplayString());
            if (!(cat.getPrice().toUpperCase().contains("SOLD") || cat.getPrice().toUpperCase().contains("NOT FOR")
            || cat.getType().toUpperCase().contains("SPHYNX-CATTERY.COM"))) {
                result += cat.toDisplayString() + "<br>";
            }
            //result += cat.toDisplayString() + "<br>";
        }
        
        return result;
    }
}
