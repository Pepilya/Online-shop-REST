package com.springboottest.app.controllers;

import com.springboottest.app.model.Product;
import com.springboottest.app.service.EditorService;
import com.springboottest.app.service.GuestService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Validated
@RequestMapping("/guest")
@RequiredArgsConstructor
public class GuestController {

    private final GuestService guestService;
    private final EditorService editorService;

    @PutMapping("/deposit")
    public void changeDeposit(@RequestParam int guestId, @RequestParam int sumDeposit) {
        guestService.changeDeposit(guestId, sumDeposit);
    }
    @GetMapping("/product")
    public List<Product> getListOfProducts(@RequestParam(required = false) String searchQuery,
                                           @RequestParam (required = false) Integer minPrice,
                                           @RequestParam (required = false) Integer maxPrice) {
        return editorService.getListOfProducts(searchQuery, minPrice, maxPrice);
    }
    @PostMapping("/product")
    public int takeProduct(@RequestParam int guestId, @RequestParam int productId) {
        return guestService.takeProduct(guestId, productId);
    }

    @PutMapping("/product/{itemId}")
    public void declineItemFromBasket(@RequestParam int guestId, @PathVariable Integer itemId) {
        guestService.declineItemFromBasket(guestId, itemId);
    }

    @PutMapping("/product")
    public void declineAllBasket(@RequestParam int guestId) {
        guestService.declineAllBasket(guestId);
    }

    @PutMapping("/basket")
    public void payBasket(@RequestParam int guestId) {
        guestService.payBasket(guestId);
    }
}
