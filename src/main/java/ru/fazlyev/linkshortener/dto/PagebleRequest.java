package ru.fazlyev.linkshortener.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Positive;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class PagebleRequest {

    @Positive
    private Integer number;

    @Positive
    private Integer size;

    private List<SortRequest> sorts = new ArrayList<>();
}
