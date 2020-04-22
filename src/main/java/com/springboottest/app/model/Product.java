package com.springboottest.app.model;

import lombok.Data;

@Data
public class Product {
    private int id;
    private String name;
    private Integer price;
    private Integer productCount;
}
