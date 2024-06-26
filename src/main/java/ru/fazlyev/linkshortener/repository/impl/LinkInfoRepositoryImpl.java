package ru.fazlyev.linkshortener.repository.impl;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import ru.fazlyev.linkshortener.model.LinkInfo;
import ru.fazlyev.linkshortener.repository.LinkInfoRepository;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class LinkInfoRepositoryImpl implements LinkInfoRepository {

    private final Map<String, LinkInfo> storage = new ConcurrentHashMap<>();

    @Override
    public Optional<LinkInfo> findByShortLink(String shortLink) {
        return Optional.ofNullable(storage.get(shortLink));
    }

    @Override
    public LinkInfo saveShortLink(LinkInfo linkInfo) {
        linkInfo.setId(UUID.randomUUID());
        storage.put(linkInfo.getShortLink(), linkInfo);
        return linkInfo;
    }
}
