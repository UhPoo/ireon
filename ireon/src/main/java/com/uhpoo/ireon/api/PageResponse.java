package com.uhpoo.ireon.api;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PageResponse<T> {

    private Boolean hasNext;
    private T items;

    public PageResponse(Boolean hasNext, T items) {
        this.hasNext = hasNext;
        this.items = items;
    }

    public static <T> PageResponse<T> of(Boolean hasNext, T items) {
        return new PageResponse<>(hasNext, items);
    }
}
