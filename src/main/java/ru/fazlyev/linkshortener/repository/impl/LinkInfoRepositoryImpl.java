package ru.fazlyev.linkshortener.repository.impl;
import ru.fazlyev.linkshortener.model.LinkInfo;
import ru.fazlyev.linkshortener.repository.LinkInfoRepository;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class LinkInfoRepositoryImpl implements LinkInfoRepository {

    private final Map<String, LinkInfo> storage = new ConcurrentHashMap<>();

    @Override
    public Optional<LinkInfo> findByShortLink(String shortLink) {
        return Optional.ofNullable(storage.get(shortLink));
    }

    @Override
    public LinkInfo saveShortLink(LinkInfo linkInfo) {
        return storage.put(linkInfo.getShortLink(), linkInfo);
    }
}
