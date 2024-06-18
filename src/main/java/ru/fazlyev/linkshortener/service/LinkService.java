package ru.fazlyev.linkshortener.service;

import org.apache.commons.lang3.RandomStringUtils;
import ru.fazlyev.linkshortener.dto.CreateShortLinkRequest;
import ru.fazlyev.linkshortener.util.Constants;


public class LinkService {

    public String generateShortLink(CreateShortLinkRequest request) {
        String link = request.getLink();

        return RandomStringUtils.randomAlphabetic(Constants.SHORT_LINK_LENGTH);

    }

}
