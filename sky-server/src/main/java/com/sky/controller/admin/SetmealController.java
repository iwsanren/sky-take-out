package com.sky.controller.admin;

import com.sky.dto.SetmealDTO;
import com.sky.dto.SetmealPageQueryDTO;
import com.sky.entity.Setmeal;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.SetmealService;
import com.sky.vo.SetmealVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * set administration
 */
@RestController
@RequestMapping("/admin/setmeal")
@Api(tags = "set interface")
@Slf4j
public class SetmealController {

    @Autowired
    private SetmealService setmealService;

    /**
     * Add a new meal set
     * @param setmealDTO
     * @return
     */
    @PostMapping
    @ApiOperation("Add a new meal set")
    @CacheEvict(cacheNames = "setmealCache", key = "#setmealDTO.categoryId") // key: setmealCache::categoryId
    public Result save(@RequestBody SetmealDTO setmealDTO){
        log.info("Add a new meal set:{}", setmealDTO);
        setmealService.saveWithDish(setmealDTO);
        return Result.success();
    }

    /**
     * page query
     * @param setmealPageQueryDTO
     * @return
     */
    @GetMapping("/page")
    @ApiOperation("page query")
    public Result<PageResult> page(SetmealPageQueryDTO setmealPageQueryDTO){
        PageResult pageResult = setmealService.pageQuery(setmealPageQueryDTO);
        return Result.success(pageResult);
    }

    /**
     * Batch delete sets
     * @param ids
     * @return
     */
    @DeleteMapping
    @ApiOperation("Batch delete sets")
    @CacheEvict(cacheNames = "setmealCache", allEntries = true)
    public Result delete(@RequestParam List<Long> ids){
        setmealService.deleteBatch(ids);
        return null;
    }

    /**
     * Query set meal by ID for displaying data on the edit page.
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    @ApiOperation("Get set by id")
    public Result<SetmealVO> getById(@PathVariable Long id){
        SetmealVO setmealVO = setmealService.getByIdWithDish(id);
        return Result.success(setmealVO);
    }

    /**
     * Update set meal
     * @param setmealDTO
     * @return
     */
    @PutMapping
    @ApiOperation("Update set meal")
    @CacheEvict(cacheNames = "setmealCache", allEntries = true)
    public Result update(@RequestBody SetmealDTO setmealDTO){
        setmealService.update(setmealDTO);
        return Result.success();
    }

    /**
     * Start or stop selling the set
     * @param status
     * @param id
     * @return
     */
    @PostMapping("status/{status}")
    @ApiOperation("Start or stop selling the set")
    @CacheEvict(cacheNames = "setmealCache", allEntries = true)
    public Result startOrStop(@PathVariable Integer status, Long id){
        setmealService.startOrStop(status ,id);
        return Result.success();
    }

}
