package ru.fazlyev.linkshortener.repository;
import ru.fazlyev.linkshortener.model.LinkInfo;
import java.util.Optional;

public interface LinkInfoRepository {

    Optional<LinkInfo> findByShortLink(String shortLink);

    LinkInfo saveShortLink(LinkInfo linkInfo);
}
