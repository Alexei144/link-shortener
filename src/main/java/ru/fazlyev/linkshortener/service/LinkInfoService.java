package ru.fazlyev.linkshortener.service;

import ru.fazlyev.linkshortener.dto.CreateShortLinkRequest;
import ru.fazlyev.linkshortener.model.LinkInfo;

public interface LinkInfoService {

    public LinkInfo createLinkInfo(CreateShortLinkRequest request);

    public String getByShortLink(String shortLink);
}
