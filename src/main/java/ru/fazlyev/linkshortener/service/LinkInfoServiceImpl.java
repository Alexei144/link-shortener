package ru.fazlyev.linkshortener.service;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.fazlyev.linkshortener.beanpostprocessor.LogExecutionTime;
import ru.fazlyev.linkshortener.dto.ShortLinkRequest;
import ru.fazlyev.linkshortener.dto.FilterLinkInfoRequest;
import ru.fazlyev.linkshortener.dto.LinkInfoResponse;
import ru.fazlyev.linkshortener.exception.NotFoundException;
import ru.fazlyev.linkshortener.mapper.LinkInfoMapper;
import ru.fazlyev.linkshortener.model.LinkInfo;
import ru.fazlyev.linkshortener.property.LinkShortenerProperty;
import ru.fazlyev.linkshortener.repository.LinkInfoRepository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
//@Primary
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
    public LinkInfoResponse createLinkInfo(ShortLinkRequest request) {
        LinkInfo linkInfo = linkInfoMapper.fromCreateRequest(request);
        linkInfo.setShortLink(RandomStringUtils.randomAlphanumeric(linkShortenerProperty.getShortLinkLength()));
        linkInfo.setOpeningCount(0L);

        linkInfoRepository.save(linkInfo);

        return linkInfoMapper.toResponse(linkInfo);
    }

    @LogExecutionTime
    public LinkInfo getByShortLink(String shortLink) {
        LinkInfo linkInfo = linkInfoRepository.findByShortLinkAndActiveTrue(shortLink)
                .orElseThrow(() -> new NotFoundException("Нет ссылки в базе: " + shortLink));

        linkInfoRepository.incrementOpeningCountByShortLink(shortLink);

        return linkInfo;
    }

    @Override
    public List<LinkInfoResponse> findByFilter(FilterLinkInfoRequest filterRequest) {
        return linkInfoRepository.findByFilter(
                        filterRequest.getLinkPart(),
                        filterRequest.getEndTimeFrom(),
                        filterRequest.getEndTimeTo(),
                        filterRequest.getDescriptionPart(),
                        filterRequest.getActive()).stream()
                .map(linkInfoMapper::toResponse)
                .toList();
    }

    @Override
    public void deleteById(UUID id) {
        linkInfoRepository.deleteById(id);
    }

    @Override
    public LinkInfoResponse updateById(ShortLinkRequest shortLinkRequest, String shortLink){
        Optional<LinkInfo> linkInfoOptional = linkInfoRepository.findByShortLinkAndActiveTrue(shortLink);

        if (linkInfoOptional.isPresent()) {
            LinkInfo linkInfoUpdate = new LinkInfo(linkInfoOptional.get().getId(),
                    shortLinkRequest.getLink(),
                    shortLinkRequest.getEndTime(),
                    shortLinkRequest.getDescription(),
                    shortLinkRequest.getActive(),
                    shortLink,
                    linkInfoOptional.get().getOpeningCount());
            return linkInfoMapper.toResponse(linkInfoRepository.save(linkInfoUpdate));
        }

        throw new NotFoundException("Ссылка для обновления не найдена");
    }
}
