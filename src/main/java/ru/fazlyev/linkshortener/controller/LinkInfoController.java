package ru.fazlyev.linkshortener.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.fazlyev.linkshortener.dto.ShortLinkRequest;
import ru.fazlyev.linkshortener.dto.FilterLinkInfoRequest;
import ru.fazlyev.linkshortener.dto.LinkInfoResponse;
import ru.fazlyev.linkshortener.dto.common.CommonRequest;
import ru.fazlyev.linkshortener.dto.common.CommonResponse;
import ru.fazlyev.linkshortener.dto.IdRequest;
import ru.fazlyev.linkshortener.service.LinkInfoService;
import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/link-infos")
public class LinkInfoController {

    private final LinkInfoService linkInfoService;

    @PostMapping("/filter")
    public CommonResponse<List<LinkInfoResponse>> filter(@RequestBody @Valid CommonRequest<FilterLinkInfoRequest> request) {
        log.info("Поступил запрос на получение всех ссылок");

        List<LinkInfoResponse> linkInfoResponses = linkInfoService.findByFilter(request.getBody());

        return CommonResponse.<List<LinkInfoResponse>>builder()
                .body(linkInfoResponses)
                .build();
    }

    @PostMapping()
    public CommonResponse<LinkInfoResponse> postCreateShortLink(
            @RequestBody @Valid CommonRequest<ShortLinkRequest> request) {
        log.info("Поступил запрос на создание короткой ссылки: {}", request);

        LinkInfoResponse createLinkInfoResponse = linkInfoService.createLinkInfo(request.getBody());

        return CommonResponse.<LinkInfoResponse>builder()
                .body(createLinkInfoResponse)
                .build();
    }

    @DeleteMapping
    public CommonResponse<?> deleteById(@RequestBody @Validated CommonRequest<IdRequest> request) {
        log.info("Поступил запрос на удаление ссылки: {}", request.getBody().getId());

        linkInfoService.deleteById(request.getBody().getId());

        return CommonResponse.builder()
                .build();
    }

    @PutMapping
    public CommonResponse<LinkInfoResponse> updateByShortLink(@PathVariable String shortLink,
                                                              @RequestBody @Valid CommonRequest<ShortLinkRequest> request) {
        log.info("Поступил запрос на обновление ссылки: {}", shortLink);

        return CommonResponse.<LinkInfoResponse>builder()
                .body(linkInfoService.updateById(request.getBody(), shortLink))
                .build();
    }
}


















