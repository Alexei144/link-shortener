package ru.fazlyev.linkshortener.dto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.ZonedDateTime;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        CreateShortLinkRequest that = (CreateShortLinkRequest) o;
        return Objects.equals(link, that.link);
    }
}
