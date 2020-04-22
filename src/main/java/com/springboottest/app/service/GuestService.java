package com.springboottest.app.service;

import com.springboottest.app.model.Product;

import java.util.List;

public interface GuestService {

    void changeDeposit(int guestId, int sumDeposit);

    List<Product> getListOfProduct(String searchQuery, Integer minPrice, Integer maxPrice);

    int takeProduct(int guest, int productId);

    void declineItemFromBasket(int guest, Integer itemId);

    void declineAllBasket(int guest);

    void payBasket(int guest);
}
