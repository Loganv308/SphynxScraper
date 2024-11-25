
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

// Variable at the top of this file will contain your designated path to the credentials file on your PC.
private String PATH = "src\\main\\java\\com\\<username>\\credentials.properties";

// Create a file if not already there called "credentials.properties", this will act as your config file for the program.
try (FileInputStream input = new FileInputStream(PATH))

// It should contain the following:

email=<email>
password=<password key>
subject=<Email Subject> 
recipient=<Email Recipient(s)>

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

