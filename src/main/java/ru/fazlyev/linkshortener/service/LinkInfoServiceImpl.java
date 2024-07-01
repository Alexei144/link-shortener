package ru.fazlyev.linkshortener.service;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import ru.fazlyev.linkshortener.dto.CreateShortLinkRequest;
import ru.fazlyev.linkshortener.exception.NotFoundException;
import ru.fazlyev.linkshortener.model.LinkInfo;
import ru.fazlyev.linkshortener.repository.LinkInfoRepository;
import ru.fazlyev.linkshortener.util.Constants;
@Service
@Primary
public class LinkInfoServiceImpl implements LinkInfoService {
    @Value("${link-shortener.short-link-length}")
    private int shortLinkLength;

    @Autowired
    private LinkInfoRepository linkInfoRepository;


    public LinkInfo createLinkInfo(CreateShortLinkRequest request) {
        LinkInfo linkInfo = new LinkInfo();
        linkInfo.setLink(request.getLink());
        linkInfo.setEndTime(request.getEndTime());
        linkInfo.setDescription(request.getDescription());
        linkInfo.setActive(request.getActive());
        linkInfo.setShortLink(RandomStringUtils.randomAlphanumeric(shortLinkLength));
        linkInfo.setOpeningCount(0L);
        return linkInfoRepository.saveShortLink(linkInfo);
    }

    public String getByShortLink(String shortLink) {
        return linkInfoRepository.findByShortLink(shortLink)
                .orElseThrow(() -> new NotFoundException("Нет ссылки в базе" + shortLink)).getLink();
    }



}
