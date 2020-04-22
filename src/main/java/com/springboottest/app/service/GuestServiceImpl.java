package com.springboottest.app.service;

import com.springboottest.app.exceptions.InsufficientFunds;
import com.springboottest.app.mappers.GuestMapper;
import com.springboottest.app.model.Product;
import com.springboottest.app.model.StatusOrder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GuestServiceImpl implements GuestService {

    private final GuestMapper guestMapper;
    private final EditorService editorService;

    @Override
    public void changeDeposit(int guestId, int sumDeposit) {
        guestMapper.changeDeposit(guestId, sumDeposit);
    }

    @Override
    public List<Product> getListOfProduct(String searchQuery, Integer minPrice, Integer maxPrice) {
        return editorService.getListOfProducts(searchQuery, minPrice, maxPrice);
    }

    @Override
    public int takeProduct(int guestId, int productId) {
        guestMapper.substractCountProduct(productId,1);
        return guestMapper.takeProduct(guestId, productId);
    }

    @Override
    public void declineItemFromBasket(int guestId, Integer itemId) {
        int productId = guestMapper.getProductIdByItemId(itemId);
        guestMapper.updateStatus(guestId, StatusOrder.DECLINE, itemId);
        guestMapper.addCountProduct(productId, (long) 1);
    }

    @Override
    public void declineAllBasket(int guestId) {
        List<Integer> productIds = guestMapper.getAllItemFromOrder(guestId);
        Map<Integer, Long> mapProductAndCount = productIds.stream().distinct().collect(Collectors.toMap(s -> guestMapper.getProductIdByItemId(s),
                s -> productIds.stream().filter(p -> p.equals(s)).count()));
        mapProductAndCount.forEach(guestMapper::addCountProduct);
        guestMapper.updateStatus(guestId, StatusOrder.DECLINE, null);
    }

    @Override
    public void payBasket(int guestId) {
        int currentDeposit = guestMapper.getCurrentDeposit(guestId);
        int costOfOrder = guestMapper.getOrderSum(guestId);
        if (currentDeposit < costOfOrder)
            throw new InsufficientFunds();
        guestMapper.changeDeposit(guestId, currentDeposit - costOfOrder);
        guestMapper.updateStatus(guestId, StatusOrder.CLOSED, null);
    }
}
