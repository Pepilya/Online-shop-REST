package com.springboottest.app.controllers;

import com.springboottest.app.model.Product;
import com.springboottest.app.service.EditorService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Validated
@RequestMapping("/editor")
@RequiredArgsConstructor
public class EditorController {

    private final EditorService editorService;

    @GetMapping("/product")
    public List<Product> getListOfProducts(@RequestParam(required = false) String searchQuery,
                                           @RequestParam (required = false) Integer minPrice,
                                           @RequestParam (required = false) Integer maxPrice) {
        return editorService.getListOfProducts(searchQuery, minPrice, maxPrice);
    }

    @GetMapping("/product/{id}")
    public Product getProductById(@PathVariable int id) {
        return editorService.getProductById(id);
    }

    @PostMapping("/product")
    public Product addProduct(@RequestBody Product product) {
        return editorService.addProduct(product);
    }

    @PutMapping("/product/{id}")
    public Product updateProduct(@PathVariable int id, Product product) {
        return editorService.updateProduct(id, product);
    }

    @DeleteMapping("/product/{id}")
    public Product deleteProduct(@PathVariable int id) {
        return editorService.deleteProduct(id);
    }
}
