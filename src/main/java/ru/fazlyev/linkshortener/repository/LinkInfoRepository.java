package ru.fazlyev.linkshortener.repository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import ru.fazlyev.linkshortener.model.LinkInfo;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Repository
public interface LinkInfoRepository {

    Optional<LinkInfo> findByShortLink(String shortLink);

    LinkInfo saveShortLink(LinkInfo linkInfo);

    List<LinkInfo> getAllShortLinks();

    void delete (UUID id);
}
