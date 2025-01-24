package com.sky.mapper;

import com.sky.entity.SetmealDish;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface Setmealdishmapper {
    /**
     * Enquiry Set Meal id by Dish id
     *
     * @param dishIds
     * @return
     */
    // select set_meal_id from setMeal_dish where dish_id in (1,2,3,4);
    default List<Long> getSetmealIdsByDishIds(List<Long> dishIds) {
        return null;
    }

    /**
     * Batch insert the mappings between set meals and dishes into the database.
     * @param setmealDishes
     */
    void insertBatch(List<SetmealDish> setmealDishes);

    /**
     * Batch delete the sets and the associations between set meals and dishes.
     * @param setmealId
     */
    @Delete("delete from setmeal_dish where setmeal_id = #{setmealId}")
    void deleteBySetmealId(Long setmealId);

    /**
     * Get the sets and the associations between set meals and dishes by setmeal id
     * @param setmealId
     * @return
     */
    @Select("select * from setmeal_dish where setmeal_id = #{setmealId}")
    List<SetmealDish> getBySetmealId(Long setmealId);
}
