package ru.fazlyev.linkshortener;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.fazlyev.loggingstarter.LoggingConfiguration;

@SpringBootApplication
public class LinkShortenerApp {
    public static void main(String[] args) {
        LoggingConfiguration.testLog("Hello");
        SpringApplication.run(LinkShortenerApp.class);
    }
}
