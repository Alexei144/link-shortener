package ru.fazlyev.linkshortener.property;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
@Getter
@Setter
@ConfigurationProperties(prefix = "link-shortener")
public class LinkShortenerProperty {
    private int shortLinkLength;



}
