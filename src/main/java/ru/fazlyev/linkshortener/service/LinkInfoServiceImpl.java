package ru.fazlyev.linkshortener.service;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import ru.fazlyev.linkshortener.beanpostprocessor.LogExecutionTime;
import ru.fazlyev.linkshortener.dto.CreateShortLinkRequest;
import ru.fazlyev.linkshortener.dto.CreateShortLinkResponse;
import ru.fazlyev.linkshortener.exception.NotFoundException;
import ru.fazlyev.linkshortener.mapper.LinkInfoMapper;
import ru.fazlyev.linkshortener.model.LinkInfo;
import ru.fazlyev.linkshortener.property.LinkShortenerProperty;
import ru.fazlyev.linkshortener.repository.LinkInfoRepository;

import java.util.List;
import java.util.UUID;

@Service
@Primary
public class LinkInfoServiceImpl implements LinkInfoService {

    @Autowired
    private LinkInfoRepository linkInfoRepository;
    @Autowired
    private LinkShortenerProperty linkShortenerProperty;
    @Autowired
    private LinkInfoMapper linkInfoMapper;

    public LinkInfoServiceImpl() {
    }

    @LogExecutionTime
    public CreateShortLinkResponse createLinkInfo(CreateShortLinkRequest request) {
        LinkInfo linkInfo = linkInfoMapper.fromCreateRequest(request);
        linkInfo.setShortLink(RandomStringUtils.randomAlphanumeric(linkShortenerProperty.getShortLinkLength()));
        linkInfo.setOpeningCount(0L);

        linkInfoRepository.saveShortLink(linkInfo);

        return linkInfoMapper.toResponse(linkInfo);
    }

    @LogExecutionTime
    public LinkInfo getByShortLink(String shortLink) {
        return linkInfoRepository.findByShortLink(shortLink)
                .orElseThrow(() -> new NotFoundException("Нет ссылки в базе" + shortLink));
    }

    @Override
    public List<LinkInfo> getAllShortLinks() {

        return linkInfoRepository.getAllShortLinks();
    }

    @Override
    public void delete(UUID id) {
        linkInfoRepository.delete(id);
    }
}
