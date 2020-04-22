package com.springboottest.app.model;

import lombok.Data;

@Data
public class SearchParam {
    private String searchQuery;
    private Integer min;
    private Integer max;
}
