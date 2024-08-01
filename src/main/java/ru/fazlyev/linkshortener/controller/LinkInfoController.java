package ru.fazlyev.linkshortener.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.fazlyev.linkshortener.dto.CreateShortLinkRequest;
import ru.fazlyev.linkshortener.dto.CreateShortLinkResponse;
import ru.fazlyev.linkshortener.dto.common.CommonRequest;
import ru.fazlyev.linkshortener.dto.common.CommonResponse;
import ru.fazlyev.linkshortener.model.LinkInfo;
import ru.fazlyev.linkshortener.service.LinkInfoService;
import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/link-infos")
public class LinkInfoController {

    private final LinkInfoService linkInfoService;

    @GetMapping
    public List<LinkInfo> getAllShortLinks() {
        log.info("Поступил запрос на получение всех ссылок");

        return linkInfoService.getAllShortLinks();
    }

    @PostMapping()
    public CommonResponse<CreateShortLinkResponse> postCreateShortLink(
            @RequestBody @Valid CommonRequest<CreateShortLinkRequest> request) {
        log.info("Поступил запрос на создание короткой ссылки: {}", request);

        CreateShortLinkResponse createShortLinkResponse = linkInfoService.createLinkInfo(request.getBody());

        return CommonResponse.<CreateShortLinkResponse>builder()
                .body(createShortLinkResponse)
                .build();
    }

    @DeleteMapping("/{id}")
    public void deleteById (@PathVariable UUID id) {
        log.info("Поступил запрос на удаление ссылки: {}", id);

        linkInfoService.delete(id);
    }
}
