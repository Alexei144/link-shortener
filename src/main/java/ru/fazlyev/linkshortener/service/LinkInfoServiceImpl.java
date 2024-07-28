package ru.fazlyev.linkshortener.service;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import ru.fazlyev.linkshortener.beanpostprocessor.LogExecutionTime;
import ru.fazlyev.linkshortener.dto.CreateShortLinkRequest;
import ru.fazlyev.linkshortener.dto.CreateShortLinkResponse;
import ru.fazlyev.linkshortener.exception.NotFoundException;
import ru.fazlyev.linkshortener.model.LinkInfo;
import ru.fazlyev.linkshortener.property.LinkShortenerProperty;
import ru.fazlyev.linkshortener.repository.LinkInfoRepository;
@Service
@Primary
public class LinkInfoServiceImpl implements LinkInfoService {

    @Autowired
    private LinkShortenerProperty linkShortenerProperty;
    private int shortLinkLength;

    @Autowired
    private LinkInfoRepository linkInfoRepository;

    @LogExecutionTime
    public CreateShortLinkResponse createLinkInfo(CreateShortLinkRequest request) {
        LinkInfo linkInfo = new LinkInfo();
        linkInfo.setLink(request.getLink());
        linkInfo.setEndTime(request.getEndTime());
        linkInfo.setDescription(request.getDescription());
        linkInfo.setActive(request.getActive());
        linkInfo.setShortLink(RandomStringUtils.randomAlphanumeric(linkShortenerProperty.getShortLinkLength()));
        linkInfo.setOpeningCount(0L);

        linkInfoRepository.saveShortLink(linkInfo);

        return CreateShortLinkResponse.builder()
                .id(linkInfo.getId())
                .link(linkInfo.getLink())
                .endTime(linkInfo.getEndTime())
                .description(linkInfo.getDescription())
                .active(linkInfo.getActive())
                .shortLink(linkInfo.getShortLink())
                .build();
    }

    @LogExecutionTime
    public LinkInfo getByShortLink(String shortLink) {
        return linkInfoRepository.findByShortLink(shortLink)
                .orElseThrow(() -> new NotFoundException("Нет ссылки в базе" + shortLink));
    }
}
