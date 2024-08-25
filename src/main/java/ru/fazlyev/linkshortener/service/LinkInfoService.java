package ru.fazlyev.linkshortener.service;

import ru.fazlyev.linkshortener.dto.ShortLinkRequest;
import ru.fazlyev.linkshortener.dto.FilterLinkInfoRequest;
import ru.fazlyev.linkshortener.dto.LinkInfoResponse;
import ru.fazlyev.linkshortener.model.LinkInfo;

import java.util.List;
import java.util.UUID;

public interface LinkInfoService {

    LinkInfoResponse createLinkInfo(ShortLinkRequest request);

    LinkInfo getByShortLink(String shortLink);

    List<LinkInfoResponse> findByFilter(FilterLinkInfoRequest filterLinkInfoRequest);

    void deleteById (UUID id);

    LinkInfoResponse updateById(ShortLinkRequest shortLinkRequest, String shortLink);
}
