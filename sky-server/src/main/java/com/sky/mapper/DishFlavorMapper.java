package com.sky.mapper;

import com.sky.entity.DishFlavor;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DishFlavorMapper {
    /**
     * Insert multiple records into the flavor table.
     * @param flavors
     */
    void insertBatch(List<DishFlavor> flavors);

    /**
     * delete flavors by dish id (changed the param name, because id is used on primary key more often)
     * @param dishId
     */
    @Delete("delete from dish_flavor where dish_id = #{dishId}")
    void deleteByDishId(Long dishId);

    /**
     * delete batch relative flavor records by dish id collection
     * @param dishIds
     */
    void deleteByDishIds(List<Long> dishIds);
}
