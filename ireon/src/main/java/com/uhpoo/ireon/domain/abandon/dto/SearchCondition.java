package com.uhpoo.ireon.domain.abandon.dto;

import lombok.Data;

@Data
public class SearchCondition {

    private String keyword;

    public SearchCondition(String keyword) {
        this.keyword = keyword;
    }

    public static SearchCondition of (String keyword) {
        return new SearchCondition(keyword);
    }
}
