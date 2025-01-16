package com.sky.service;

import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.result.PageResult;
import com.sky.vo.DishVO;

import java.util.List;

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

    /**
     * Batch deletion of dishes
     * @param ids
     */
    void deleteBatch(List<Long> ids);

    /**
     * Enquiry dish by id
     * @param id
     * @return
     */
    DishVO getByIdWithFlavor(Long id);

    /**
     * update the dish basic attributions' value and relative flavor attributions' value
     * @param dishDTO
     */
    void updateWithFlavor(DishDTO dishDTO);
}
