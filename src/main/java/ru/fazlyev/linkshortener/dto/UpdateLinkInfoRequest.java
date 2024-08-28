package ru.fazlyev.linkshortener.dto;;

import lombok.Builder;

import javax.validation.constraints.*;
import java.time.ZonedDateTime;
import java.util.UUID;

@Builder
public record UpdateLinkInfoRequest(
        @NotNull(message = "ID не может быть пустым")
        UUID id,
        @NotEmpty(message = "Link не может быть пустым")
        @Size(min = 10, max = 4096, message = "Длинна ссылки от 10 до 4096")
        @Pattern(regexp = "https?://.+\\..+", message = "не подходит под паттерн email")
        String link,
        @Future(message = "дата должна быть в будующем")
        ZonedDateTime endTime,
        String description,
        Boolean active) {
}