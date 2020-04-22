package com.springboottest.app.service;

import com.springboottest.app.mappers.EditorMapper;
import com.springboottest.app.model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EditorServiceImpl implements EditorService{

    private final EditorMapper editorMapper;

    @Override
    public List<Product> getListOfProducts(String searchQuery, Integer minPrice, Integer maxPrice) {
        return editorMapper.getListOfProducts(searchQuery, minPrice, maxPrice);
    }

    @Override
    public Product getProductById(int id) {
        return editorMapper.getProductById(id);
    }

    @Override
    public Product addProduct(Product product) {
        return editorMapper.addProduct(product.getName(), product.getPrice(), product.getProductCount());
    }

    @Override
    public Product updateProduct(int id, Product product) {
        return editorMapper.updateProduct(id, product);
    }

    @Override
    public Product deleteProduct(int id) {
        return editorMapper.deleteProduct(id);
    }
}
