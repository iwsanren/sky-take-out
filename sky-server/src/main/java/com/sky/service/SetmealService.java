package com.sky.service;

import com.sky.dto.SetmealDTO;
import com.sky.dto.SetmealPageQueryDTO;
import com.sky.entity.Setmeal;
import com.sky.result.PageResult;
import com.sky.vo.DishItemVO;
import com.sky.vo.SetmealVO;
import java.util.List;

public interface SetmealService {

    /**
     * 条件查询
     * @param setmeal
     * @return
     */
    List<Setmeal> list(Setmeal setmeal);

    /**
     * 根据id查询菜品选项
     * @param id
     * @return
     */
    List<DishItemVO> getDishItemById(Long id);

    /**
     * Add a new meal set and save the relationship between dish and set at the same time.
     * @param setmealDTO
     */
    void saveWithDish(SetmealDTO setmealDTO);

    /**
     * page query
     * @param setmealPageQueryDTO
     * @return
     */
    PageResult pageQuery(SetmealPageQueryDTO setmealPageQueryDTO);

    /**
     * Batch delete sets
     * @param ids
     */
    void deleteBatch(List<Long> ids);

    /**
     * Get the sets and the associations between set meals and dishes by setmeal id
     * @param id
     * @return
     */
    SetmealVO getByIdWithDish(Long id);

    /**
     * Update set meal
     * @param setmealDTO
     */
    void update(SetmealDTO setmealDTO);

    /**
     * Start or stop selling the set
     * @param status
     * @param id
     */
    void startOrStop(Integer status, Long id);
}
