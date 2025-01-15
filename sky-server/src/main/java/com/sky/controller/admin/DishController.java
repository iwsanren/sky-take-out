package com.sky.controller.admin;

import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.DishService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        return Result.success();
    }

}
