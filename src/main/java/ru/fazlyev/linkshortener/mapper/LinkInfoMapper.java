package ru.fazlyev.linkshortener.mapper;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import ru.fazlyev.linkshortener.dto.ShortLinkRequest;
import ru.fazlyev.linkshortener.dto.LinkInfoResponse;
import ru.fazlyev.linkshortener.model.LinkInfo;

@Component
@Mapper(componentModel = "spring")
public interface LinkInfoMapper {

    LinkInfo fromCreateRequest(ShortLinkRequest request);

    LinkInfoResponse toResponse(LinkInfo linkInfo);
}
