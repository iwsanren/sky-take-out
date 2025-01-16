package com.sky.controller.admin;

import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.DishService;
import com.sky.vo.DishVO;
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

        return Result.success();
    }


}
