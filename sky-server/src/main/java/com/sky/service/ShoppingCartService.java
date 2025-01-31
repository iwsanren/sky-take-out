package com.sky.service;

import com.sky.dto.ShoppingCartDTO;
import com.sky.entity.ShoppingCart;

import java.util.List;

public interface ShoppingCartService {

    /**
     * Add a dish into the cart
     * @param shoppingCartDTO
     */
    void addShoppingCart(ShoppingCartDTO shoppingCartDTO);

    /**
     * view the shopping cart
     * @return
     */
    List<ShoppingCart> showShoppingCart();

    /**
     * clean the shopping cart
     */
    void cleanShoppingCart();

    /**
     * Delete an item in the shopping cart
     * @param shoppingCartDTO
     */
    void subShoppingCart(ShoppingCartDTO shoppingCartDTO);


}
