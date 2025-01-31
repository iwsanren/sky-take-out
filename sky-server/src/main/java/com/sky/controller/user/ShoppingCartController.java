package com.sky.controller.user;

import com.sky.dto.ShoppingCartDTO;
import com.sky.entity.ShoppingCart;
import com.sky.result.Result;
import com.sky.service.ShoppingCartService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user/shoppingCart")
@Slf4j
@Api(tags = "Consumer-side shopping cart API")
public class ShoppingCartController {

    @Autowired
    private ShoppingCartService shoppingCartService;
    /**
     * Add into shopping cart
     * @param shoppingCartDTO
     * @return
     */
    @PostMapping("/add")
    @ApiOperation("Add into shopping cart")
    public Result add(@RequestBody ShoppingCartDTO shoppingCartDTO){
        log.info("Add a dish into the cart:{}", shoppingCartDTO);
        shoppingCartService.addShoppingCart(shoppingCartDTO);
        return Result.success();
    }

    @GetMapping("/list")
    @ApiOperation("View shopping cart")
    public Result<List<ShoppingCart>> list(){
        List<ShoppingCart> list = shoppingCartService.showShoppingCart();
        return Result.success(list);
    }

    @DeleteMapping("/clean")
    @ApiOperation("clean the shopping cart")
    public Result clean(){
        shoppingCartService.cleanShoppingCart();
        return Result.success();
    }
}
