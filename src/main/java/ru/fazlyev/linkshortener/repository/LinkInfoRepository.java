package ru.fazlyev.linkshortener.repository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import ru.fazlyev.linkshortener.model.LinkInfo;
import java.util.Optional;

@Repository
public interface LinkInfoRepository {

    Optional<LinkInfo> findByShortLink(String shortLink);

    LinkInfo saveShortLink(LinkInfo linkInfo);
}
