
# SphynxScraper

Java program to webscrape our local Sphinx cat breeders website to see if there are any new entries.

This was developed to learn the ins and outs of specific Java functionality such as Web Scraping, javax.mail, and Maven. 






## Deployment

To deploy this project run

```bash
  mvn package -f <path_to_pom.xml>

  java -jar <path to jar>
```


## Usage/Examples

```java
Scraper.java
// Loop to filter if there are new cats avialable or not. 

    for (Cat cat : cats) {
        System.out.println(cat.toDisplayString());

        if (!(cat.getPrice().toUpperCase().contains("SOLD") || cat.getPrice().toUpperCase().contains("NOT FOR")
        || cat.getType().toUpperCase().contains("SPHYNX-CATTERY.COM"))) {
            result += cat.toDisplayString() + "<br>";
        }

        // Uncomment the line below to send email regardless of new cats or not. 
        // Otherwise, the script will only send an email if there's new cats. 
        // result += cat.toDisplayString() + "<br>";
    }

```
``` java
    Main.java 

    EmailAccount emailAccount = new EmailAccount(<EmailAddress>, <Password key>);

    EmailSender emailSender = new EmailSender(emailAccount, <subject>, <recipient>);

```


## Authors

- [@Loganv308](https://github.com/Loganv308)


## License

[MIT](https://choosealicense.com/licenses/mit/)

