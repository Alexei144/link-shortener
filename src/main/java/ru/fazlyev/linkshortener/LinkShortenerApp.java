package ru.fazlyev.linkshortener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.fazlyev.linkshortener.dto.CreateShortLinkRequest;
import ru.fazlyev.linkshortener.service.LinkInfoService;
import ru.fazlyev.linkshortener.service.LinkInfoServiceImpl;

import javax.annotation.PostConstruct;


@SpringBootApplication
public class LinkShortenerApp {

    @Autowired
    @Qualifier("linkInfoServiceProxy")
    private LinkInfoService linkInfoService;

    @PostConstruct
    public void pc() {
        System.out.println(linkInfoService.createLinkInfo(new CreateShortLinkRequest()));
    }

    public static void main(String[] args) {
        SpringApplication.run(LinkShortenerApp.class);
    }
}
