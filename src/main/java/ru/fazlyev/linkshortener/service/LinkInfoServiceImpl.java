package ru.fazlyev.linkshortener.service;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import ru.fazlyev.linkshortener.beanpostprocessor.LogExecutionTime;
import ru.fazlyev.linkshortener.dto.CreateShortLinkRequest;
import ru.fazlyev.linkshortener.dto.FilterLinkInfoRequest;
import ru.fazlyev.linkshortener.dto.LinkInfoResponse;
import ru.fazlyev.linkshortener.dto.PagebleRequest;
import ru.fazlyev.linkshortener.dto.UpdateLinkInfoRequest;
import ru.fazlyev.linkshortener.exception.NotFoundException;
import ru.fazlyev.linkshortener.exception.NotFoundPageException;
import ru.fazlyev.linkshortener.mapper.LinkInfoMapper;
import ru.fazlyev.linkshortener.model.LinkInfo;
import ru.fazlyev.linkshortener.property.LinkShortenerProperty;
import ru.fazlyev.linkshortener.repository.LinkInfoRepository;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
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
    public LinkInfoResponse createLinkInfo(CreateShortLinkRequest request) {
        LinkInfo linkInfo = linkInfoMapper.fromCreateRequest(request);
        linkInfo.setShortLink(RandomStringUtils.randomAlphanumeric(linkShortenerProperty.getShortLinkLength()));
        linkInfo.setOpeningCount(0L);

        linkInfoRepository.save(linkInfo);

        return linkInfoMapper.toResponse(linkInfo);
    }

    @LogExecutionTime
    public LinkInfo getByShortLink(String shortLink) {
        LinkInfo linkInfo = linkInfoRepository.findByShortLinkAndActiveTrueAndEndTimeIsAfter(shortLink, ZonedDateTime.now())
                .orElseThrow(() -> new NotFoundPageException("Нет ссылки в базе: " + shortLink));

        linkInfoRepository.incrementOpeningCountByShortLink(shortLink);

        return linkInfo;
    }

    @Override
    public List<LinkInfoResponse> findByFilter(FilterLinkInfoRequest filterRequest) {
        Pageable pageable = createPageable(filterRequest);

        return linkInfoRepository.findByFilter(
                        filterRequest.getLinkPart(),
                        filterRequest.getEndTimeFrom(),
                        filterRequest.getEndTimeTo(),
                        filterRequest.getDescriptionPart(),
                        filterRequest.getActive(),
                        pageable).stream()
                .map(linkInfoMapper::toResponse)
                .toList();
    }

    private static Pageable createPageable(FilterLinkInfoRequest filterRequest) {
        PagebleRequest page = filterRequest.getPage();

        List<Sort.Order> orders = page.getSorts().stream()
                .map(sort -> new Sort.Order(Sort.Direction.fromString(sort.getDirection()), sort.getField()))
                .toList();

        Sort sort = CollectionUtils.isEmpty(orders)
                ? Sort.unsorted()
                : Sort.by(orders);

        Pageable pageable = PageRequest.of(page.getNumber() - 1, page.getSize(), sort);
        return pageable;
    }

    @Override
    public void deleteById(UUID id) {
        linkInfoRepository.deleteById(id);
    }

    @Override
    public LinkInfoResponse updateById(UpdateLinkInfoRequest request){
        LinkInfo updateRequest = linkInfoRepository.findById(request.id())
                .orElseThrow(()-> new NotFoundException("Ссылка для обновления не найдена: " + request.id()));
        if (request.link() != null) {
            updateRequest.setLink(request.link());
        }

        if (request.active() != null) {
            updateRequest.setActive(request.active());
        }

        if (request.description() != null) {
            updateRequest.setDescription(request.description());
        }

        if (request.endTime() != null) {
            updateRequest.setEndTime(request.endTime());
        }
        linkInfoRepository.save(updateRequest);

        return linkInfoMapper.toResponse(updateRequest);
    }
}
