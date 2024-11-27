
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

CredentialManager.java

// The Credentials.properties file will be created at your user.home.
// For Windows, this would be "C:/users/<username>
// For Linux, this would be "/home/<username>"
// The Credentials.properties file will contain the following:

email=<email>
password=<password key>
subject=<Email Subject> 
recipient=<Email Recipient(s)>

// Fill this out. Then re-run the program and it'll start to run.

------------------------------------------------------------------------------------------------------------------------

Main.java

// This is the main driver code for authentication in this program. 
EmailAccount emailAccount = new EmailAccount(creds.getUsername(), creds.getPassword());

// The line of code below will send an email based on the emailAccount, subject, and recipient. 
EmailSender emailSender = new EmailSender(emailAccount, creds.getSubject(), creds.getRecipient());

```


## Authors

- [@Loganv308](https://github.com/Loganv308)


## License

[MIT](https://choosealicense.com/licenses/mit/)

