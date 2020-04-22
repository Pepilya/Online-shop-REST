package com.springboottest.app.mappers;

import com.springboottest.app.model.Product;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface EditorMapper {

    List<Product> getListOfProducts(String searchQuery, Integer minPrice, Integer maxPrice);

    Product getProductById(int productId);

    Product addProduct(String name, int price, int productCount);

    Product updateProduct(int productId, Product product);

    Product deleteProduct(int productId);
}
