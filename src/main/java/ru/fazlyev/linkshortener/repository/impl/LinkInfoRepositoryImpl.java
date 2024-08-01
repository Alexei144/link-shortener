package ru.fazlyev.linkshortener.repository.impl;
import org.springframework.stereotype.Service;
import ru.fazlyev.linkshortener.model.LinkInfo;
import ru.fazlyev.linkshortener.repository.LinkInfoRepository;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class LinkInfoRepositoryImpl implements LinkInfoRepository {

    private final Map<String, LinkInfo> storage = new ConcurrentHashMap<>();

    @Override
    public List<LinkInfo> getAllShortLinks() {
        List<LinkInfo> links = new ArrayList<>();

        for (var itr : storage.entrySet()){
            links.add(itr.getValue());
        }
        return links;
    }

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

    @Override
    public void delete(UUID id) {
        for (LinkInfo linkInfo : storage.values()){
            if (linkInfo.getId().equals(id)){
                storage.remove(linkInfo.getShortLink());
            }
        }
    }
}
