package com.sky.service;

import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.result.PageResult;

public interface DishService {

    /**
     * add a new dish and relative flavor data
     * @param dishDTO
     */
    public void saveWithFlavor(DishDTO dishDTO); //Not only need to save dish, but also need to save relative flavor.

    /**
     * Dish page query
     * @param dishPageQueryDTO
     * @return
     */
    PageResult pageQuery(DishPageQueryDTO dishPageQueryDTO);
}
