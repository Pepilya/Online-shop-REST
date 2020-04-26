package com.springboottest.app.controllers;

import com.springboottest.app.model.Product;
import com.springboottest.app.service.EditorService;
import com.springboottest.app.service.GuestService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import static com.springboottest.app.config.JwtFilter.AUTH_TOKEN;


@RestController
@Validated
@RequestMapping("/guest")
@RequiredArgsConstructor
public class GuestController {

    private final GuestService guestService;
    private final EditorService editorService;

    @PutMapping("/deposit")
    public void changeDeposit(@RequestHeader(AUTH_TOKEN) String token,
                              @RequestParam int guestId, @RequestParam int sumDeposit) {
        guestService.changeDeposit(guestId, sumDeposit);
    }
    @GetMapping("/product")
    public List<Product> getListOfProducts(@RequestHeader(AUTH_TOKEN) String token,
                                           @RequestParam(required = false) String searchQuery,
                                           @RequestParam (required = false) Integer minPrice,
                                           @RequestParam (required = false) Integer maxPrice) {
        return editorService.getListOfProducts(searchQuery, minPrice, maxPrice);
    }
    @PostMapping("/product")
    public int takeProduct(@RequestHeader(AUTH_TOKEN) String token,
                           @RequestParam int guestId, @RequestParam int productId) {
        return guestService.takeProduct(guestId, productId);
    }

    @PutMapping("/product/{itemId}")
    public void declineItemFromBasket(@RequestHeader(AUTH_TOKEN) String token,
                                      @RequestParam int guestId, @PathVariable Integer itemId) {
        guestService.declineItemFromBasket(guestId, itemId);
    }

    @PutMapping("/product")
    public void declineAllBasket(@RequestHeader(AUTH_TOKEN) String token,
                                 @RequestParam int guestId) {
        guestService.declineAllBasket(guestId);
    }

    @PutMapping("/basket")
    public void payBasket(@RequestHeader(AUTH_TOKEN) String token,
                          @RequestParam int guestId) {
        guestService.payBasket(guestId);
    }
}
