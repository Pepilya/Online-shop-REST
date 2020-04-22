package com.springboottest.app.service;

import com.springboottest.app.model.Product;

import java.util.List;

public interface EditorService {

    List<Product> getListOfProducts(String searchQuery, Integer minPrice, Integer maxPrice);

    Product getProductById(int id);

    Product addProduct(Product product);

    Product updateProduct(int id, Product product);

    Product deleteProduct(int id);
}
