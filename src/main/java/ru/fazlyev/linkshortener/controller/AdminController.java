package ru.fazlyev.linkshortener.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.fazlyev.linkshortener.dto.CreateShortLinkRequest;
import ru.fazlyev.linkshortener.dto.CreateShortLinkResponse;
import ru.fazlyev.linkshortener.dto.common.CommonRequest;
import ru.fazlyev.linkshortener.dto.common.CommonResponse;
import ru.fazlyev.linkshortener.service.LinkInfoService;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/link-infos")
public class AdminController {

    private final LinkInfoService linkInfoService;

    @PostMapping()
    public CommonResponse<CreateShortLinkResponse> postCreateShortLink(
            @RequestBody CommonRequest <CreateShortLinkRequest> request) {
        log.info("Поступил запрос на создание короткой ссылки: {}", request);

        CreateShortLinkResponse createShortLinkResponse = linkInfoService.createLinkInfo(request.getBody());

        return CommonResponse.<CreateShortLinkResponse>builder()
                .body(createShortLinkResponse)
                .build();

    }

}
