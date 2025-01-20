package com.sky.controller.admin;

import com.sky.dto.SetmealDTO;
import com.sky.dto.SetmealPageQueryDTO;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.SetmealService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public Result save(@RequestBody SetmealDTO setmealDTO){
        log.info("Add a new meal set:{}", setmealDTO);
        setmealService.saveWithDish(setmealDTO);
        return Result.success();
    }



}
