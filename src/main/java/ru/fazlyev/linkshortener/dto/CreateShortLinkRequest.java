package ru.fazlyev.linkshortener.dto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.ZonedDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CreateShortLinkRequest {

    private String link;
    private ZonedDateTime endTime;
    private String description;
    private Boolean active;
}
