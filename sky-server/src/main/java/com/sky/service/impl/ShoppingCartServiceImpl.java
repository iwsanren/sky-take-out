package com.sky.service.impl;

import com.sky.context.BaseContext;
import com.sky.dto.ShoppingCartDTO;
import com.sky.entity.Dish;
import com.sky.entity.Setmeal;
import com.sky.entity.ShoppingCart;
import com.sky.mapper.DishMapper;
import com.sky.mapper.SetmealMapper;
import com.sky.mapper.ShoppingCartMapper;
import com.sky.service.ShoppingCartService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Service
@Slf4j
public class ShoppingCartServiceImpl implements ShoppingCartService {

    @Autowired
    private ShoppingCartMapper shoppingCartMapper;
    @Autowired
    private DishMapper dishMapper;
    @Autowired
    private SetmealMapper setmealMapper;

    /**
     * Add a dish into the cart
     * @param shoppingCartDTO
     */
    public void addShoppingCart(ShoppingCartDTO shoppingCartDTO) {

        ShoppingCart shoppingCart = new ShoppingCart();
        BeanUtils.copyProperties(shoppingCartDTO,shoppingCart);

        // get userId:
        Long userId = BaseContext.getCurrentId();
        shoppingCart.setUserId(userId);

        // 1. Check if the current item being added to the shopping cart exists in the shopping cart:
        List<ShoppingCart> list = shoppingCartMapper.list(shoppingCart);

        // If it has already been added, increase the quantity
        if(list != null && list.size() > 0){
            ShoppingCart cart = list.get(0); // 购物车只可能有两种情况：空/有一条数据
            cart.setNumber(cart.getNumber() + 1); // update shopping_cart set number = ? where id = ?
            shoppingCartMapper.updateNumberById(cart);
        }else{
            // else insert a new shopping cart record

            //2. Check if the current item being added to the shopping cart is a dish or a set meal:
            Long dishId = shoppingCartDTO.getDishId();
            if(dishId != null){
                // Adding a dish to the shopping cart.
                Dish dish = dishMapper.getById(dishId);
                shoppingCart.setName(dish.getName());
                shoppingCart.setImage(dish.getImage());
                shoppingCart.setAmount(dish.getPrice()); // inconsistent naming
            }else{
                // Adding a set meal to the shopping cart.
                Long setmealId = shoppingCartDTO.getSetmealId();
                Setmeal setmeal = setmealMapper.getById(setmealId);

                shoppingCart.setName(setmeal.getName());
                shoppingCart.setImage(setmeal.getImage());
                shoppingCart.setAmount(setmeal.getPrice()); // inconsistent naming
            }
            shoppingCart.setNumber(1);
            shoppingCart.setCreateTime(LocalDateTime.now());

            shoppingCartMapper.insert(shoppingCart);

        }

    }

    /**
     * view the shopping cart
     * @return
     */
    public List<ShoppingCart> showShoppingCart() {
        // get current user id
        Long userId = BaseContext.getCurrentId();
        ShoppingCart shoppingCart =ShoppingCart.builder()
                .userId(userId)
                .build();
        List<ShoppingCart> list = shoppingCartMapper.list(shoppingCart);
        return list;
    }

    /**
     * clean the shopping cart
     */
    public void cleanShoppingCart() {
        Long userId = BaseContext.getCurrentId();
        shoppingCartMapper.deleteByUserId(userId);
    }
}
