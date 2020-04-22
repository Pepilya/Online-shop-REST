package com.springboottest.app.mappers;

import com.springboottest.app.model.Product;
import com.springboottest.app.model.StatusOrder;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface GuestMapper {

    void changeDeposit(int guestId, int sumDeposit);

    int getCurrentDeposit(int userId);

    int takeProduct(int guestId, int productId);

    Product declineProduct(int guestId, int productId);

    void declineAllBasket(int guestId);

    int getProductIdByItemId(int itemId);

    void addCountProduct(int productId, Long countItem);

    void substractCountProduct(int productId, int countItem);

    void updateStatus(int guestId, StatusOrder status, Integer itemId);

    int getOrderSum(int guestId);

    List<Integer> getAllItemFromOrder(int guestId);

    void payBasket(int guestId);
}
