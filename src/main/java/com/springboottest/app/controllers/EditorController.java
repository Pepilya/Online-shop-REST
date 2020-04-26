package com.springboottest.app.controllers;

import com.springboottest.app.model.Product;
import com.springboottest.app.service.EditorService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import static com.springboottest.app.config.JwtFilter.AUTH_TOKEN;

@RestController
@Validated
@RequestMapping("/editor")
@RequiredArgsConstructor
public class EditorController {

    private final EditorService editorService;

    @GetMapping("/product")
    public List<Product> getListOfProducts(@RequestHeader(AUTH_TOKEN) String token,
                                           @RequestParam(required = false) String searchQuery,
                                           @RequestParam (required = false) Integer minPrice,
                                           @RequestParam (required = false) Integer maxPrice) {
        return editorService.getListOfProducts(searchQuery, minPrice, maxPrice);
    }

    @GetMapping("/product/{id}")
    public Product getProductById(@RequestHeader(AUTH_TOKEN) String token,
                                  @PathVariable int id) {
        return editorService.getProductById(id);
    }

    @PostMapping("/product")
    public Product addProduct(@RequestHeader(AUTH_TOKEN) String token,
                              @RequestBody Product product) {
        return editorService.addProduct(product);
    }

    @PutMapping("/product/{id}")
    public Product updateProduct(@RequestHeader(AUTH_TOKEN) String token,
                                 @PathVariable int id, Product product) {
        return editorService.updateProduct(id, product);
    }

    @DeleteMapping("/product/{id}")
    public Product deleteProduct(@RequestHeader(AUTH_TOKEN) String token,
                                 @PathVariable int id) {
        return editorService.deleteProduct(id);
    }
}
