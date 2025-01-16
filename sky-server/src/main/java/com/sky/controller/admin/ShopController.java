package com.sky.controller.admin;

import com.sky.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

@RestController("adminShopController")
@RequestMapping("/admin/shop")
@Api(tags = "Shop-related APIs")
@Slf4j
public class ShopController {

    public static final String KEY = "SHOP_STATUS";

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * Enable or disable the store's business status
     * @param status
     * @return
     */
    @PutMapping("/{status}")
    @ApiOperation("Enable or disable the store's business status")
    public Result setStatus(@PathVariable Integer status){
        log.info("Enable or disable the store's business status as:{}", status == 1 ? "It's open." : "It's close.");
        redisTemplate.opsForValue().set(KEY, status);
        return Result.success();
    }

    /**
     * Get the store's business status
     * @return
     */
    @GetMapping("/status")
    @ApiOperation("Get the store's business status")
    public Result<Integer> getStatus(){
        // get status from redis
        Integer status = (Integer)redisTemplate.opsForValue().get(KEY);
        log.info("Get the store's business status:{}",status == 1 ? "it's open." : "it's close");
        return Result.success(status);
    }
}
