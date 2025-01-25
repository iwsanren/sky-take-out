package com.sky.service;

import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.entity.Dish;
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

    /**
     * 条件查询菜品和口味
     * @param dish
     * @return
     */
    List<DishVO> listWithFlavor(Dish dish);

    /**
     * Enquiry the dish list by id
     * @param categoryId
     * @return
     */
    List<Dish> list(Long categoryId);

    /**
     * Dish activation and deactivation.
     * @param status
     * @param id
     */
    void startOrStop(Integer status, Long id);
}
