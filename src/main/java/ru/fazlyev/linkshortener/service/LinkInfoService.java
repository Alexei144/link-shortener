package ru.fazlyev.linkshortener.service;

import ru.fazlyev.linkshortener.dto.CreateShortLinkRequest;
import ru.fazlyev.linkshortener.dto.CreateShortLinkResponse;
import ru.fazlyev.linkshortener.model.LinkInfo;

public interface LinkInfoService {

    CreateShortLinkResponse createLinkInfo(CreateShortLinkRequest request);

    public LinkInfo getByShortLink(String shortLink);
}
