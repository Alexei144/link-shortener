package ru.fazlyev.linkshortener.mapper;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import ru.fazlyev.linkshortener.dto.CreateShortLinkRequest;
import ru.fazlyev.linkshortener.dto.CreateShortLinkResponse;
import ru.fazlyev.linkshortener.model.LinkInfo;

@Component
@Mapper(componentModel = "spring")
public interface LinkInfoMapper {

    LinkInfo fromCreateRequest(CreateShortLinkRequest request);

    CreateShortLinkResponse toResponse(LinkInfo linkInfo);
}
