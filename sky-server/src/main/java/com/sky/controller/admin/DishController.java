package com.sky.controller.admin;

import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.entity.Dish;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.DishService;
import com.sky.vo.DishVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

/**
 * Dish administration
 */
@RestController
@RequestMapping("admin/dish")
@Api(tags = "Dish interface")
@Slf4j
public class DishController {

    @Autowired
    private DishService dishService;
    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * add a new dish
     * @param dishDTO
     * @return
     */
    @PostMapping
    @ApiOperation("add a new dish")
    public Result save(@RequestBody DishDTO dishDTO){
        log.info("Add a new dish:{}", dishDTO);
        dishService.saveWithFlavor(dishDTO);

        // Clear cached data
        String key = "dish_" + dishDTO.getCategoryId();
        cleanCache("key");
        return Result.success();
    }

    /**
     * Dish activation and deactivation.
     *
     * @param status
     * @param id
     * @return
     */
    @PostMapping("/status/{status}")
    @ApiOperation("Dish activation and deactivation.")
    public Result<String> startOrStop(@PathVariable Integer status, Long id) {
        dishService.startOrStop(status, id);

        cleanCache("dish_*");

        return Result.success();
    }

    /**
     * Dish page query
     * @param dishPageQueryDTO
     * @return
     */
    @GetMapping("/page")
    @ApiOperation("Dish page query")
    public Result<PageResult> page(DishPageQueryDTO dishPageQueryDTO){
        log.info("Dish page query:{}", dishPageQueryDTO);
        PageResult pageResult = dishService.pageQuery(dishPageQueryDTO);
        return Result.success(pageResult);
    }

    /**
     * Batch deletion of dishes
     * @param ids
     * @return
     */
    @DeleteMapping
    @ApiOperation("Batch deletion of dishes")
    public Result delete(@RequestParam List<Long> ids){
        log.info("Batch deletion of dishes:{}", ids);
        dishService.deleteBatch(ids);

        // Clear all cached dish data, removing all keys that start with "dish_".
        cleanCache("dish_*");

        return Result.success();
    }

    /**
     * Enquiry dish by id
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    @ApiOperation("Enquiry dish by id")
    public Result<DishVO> getById(@PathVariable Long id){
        log.info("Enquiry dish by id:{}", id);
        DishVO dishVO = dishService.getByIdWithFlavor(id);
        return Result.success(dishVO);
    }

    /**
     * Update dish
     * @param dishDTO
     * @return
     */
    @PutMapping
    @ApiOperation("Update dish")
    public Result update(@RequestBody DishDTO dishDTO){
        log.info("Update dish:{}",dishDTO);
        // update the dish basic attribution and relative flavor attribution
        dishService.updateWithFlavor(dishDTO);

        //In complex cases: When modifying a dish, if only general attributes such as name or price are changed,
        // updating a single cached entry is sufficient.
        // However, if the dish category is modified, multiple cache entries need to be handled.
        // To simplify the process, all cached data will be cleared after modifying a dish.
        cleanCache("dish_*");

        return Result.success();
    }

    /**
     * Enquiry the dish list by id
     * @param categoryId
     * @return
     */
    @GetMapping("/list")
    @ApiOperation("Enquiry the dish list by id")
    public Result<List<Dish>> list(Long categoryId){
        List<Dish> list = dishService.list(categoryId);
        return Result.success(list);
    }

    /**
     * Clean cached data
     * @param pattern
     */
    private void cleanCache(String pattern){
        Set keys = redisTemplate.keys(pattern);
        redisTemplate.delete(keys);
    }


}
