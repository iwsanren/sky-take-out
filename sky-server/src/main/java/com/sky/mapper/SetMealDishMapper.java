package com.sky.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SetMealDishMapper {
    /**
     * Enquiry Set Meal id by Dish id
     * @param dishIds
     * @return
     */
    // select set_meal_id from setMeal_dish where dish_id in (1,2,3,4);
    List<Long> getSetMealIdsByDishIds(List<Long> dishIds);
}
