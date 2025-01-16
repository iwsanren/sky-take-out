package com.sky.controller.user;

import com.sky.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

@RestController("userShopController")
@RequestMapping("/user/shop")
@Api(tags = "Shop-related APIs")
@Slf4j
public class ShopController {

    public static final String KEY = "SHOP_STATUS";

    @Autowired
    private RedisTemplate redisTemplate;

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
