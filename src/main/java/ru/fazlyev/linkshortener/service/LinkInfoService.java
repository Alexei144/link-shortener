package ru.fazlyev.linkshortener.service;
import org.apache.commons.lang3.RandomStringUtils;
import ru.fazlyev.linkshortener.dto.CreateShortLinkRequest;
import ru.fazlyev.linkshortener.exception.NotFoundException;
import ru.fazlyev.linkshortener.model.LinkInfo;
import ru.fazlyev.linkshortener.repository.LinkInfoRepository;
import ru.fazlyev.linkshortener.repository.impl.LinkInfoRepositoryImpl;
import ru.fazlyev.linkshortener.util.Constants;

public class LinkInfoService {

    private LinkInfoRepository repository = new LinkInfoRepositoryImpl();

    public LinkInfo createLinkInfo(CreateShortLinkRequest request) {
        LinkInfo linkInfo = new LinkInfo();
        linkInfo.setLink(request.getLink());
        linkInfo.setEndTime(request.getEndTime());
        linkInfo.setDescription(request.getDescription());
        linkInfo.setActive(request.getActive());
        linkInfo.setShortLink(generateShortLink(request));
        linkInfo.setOpeningCount(0);
        return repository.saveShortLink(linkInfo);
    }

    public LinkInfo getByShortLink(String shortLink) {
        return repository.findByShortLink(shortLink)
                .orElseThrow(() -> new NotFoundException("Нет ссылки в базе"));
    }


    public String generateShortLink(CreateShortLinkRequest request) {
        return RandomStringUtils.randomAlphanumeric(Constants.SHORT_LINK_LENGTH);
    }

}
