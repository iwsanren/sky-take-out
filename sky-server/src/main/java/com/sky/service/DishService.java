package com.sky.service;

import com.sky.dto.DishDTO;

public interface DishService {

    /**
     * add a new dish and relative flavor data
     * @param dishDTO
     */
    public void saveWithFlavor(DishDTO dishDTO); //Not only need to save dish, but also need to save relative flavor.
}
