package com.sky.task;


import com.sky.entity.Orders;
import com.sky.mapper.OrderMapper;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Scheduled task class: periodically processing order status
 */
@Component
@Slf4j
public class OrderTask {

    @Autowired
    private OrderMapper orderMapper;
    /**
     * process time out order
     */
    @Scheduled(cron = "0 * * * * ? ") // Triggers every minute.
    public void processTimeoutOrder(){
        log.info("Scheduled Processing of Timeout Orders: {}", LocalDateTime.now());

        // 1. Enquiry whether this program has time out order:
        // select * from orders where status = ? and order_time < (current time - 15min)
        LocalDateTime time = LocalDateTime.now().plusMinutes(-15);
        List<Orders> orderList = orderMapper.getByStatusAndOrderTimeLT(Orders.PENDING_PAYMENT, time);

        if(orderList != null && orderList.size() > 0){
            for(Orders orders : orderList){
                orders.setStatus(Orders.CANCELLED);
                orders.setCancelReason("Order timeout: Auto-Cancellation ");
                orders.setCancelTime(LocalDateTime.now());
                orderMapper.update(orders);
            }
        }

    }

    /**
     * Scheduled Processing of Orders Stuck in Delivery Status
     */
    @Scheduled(cron = "0 0 1 * * ? ") // Triggers 1 am everyday
    public void processDeliveryOrder(){
        log.info("Scheduled Processing of Orders Stuck in Delivery Status: {}", LocalDateTime.now());

        // select * from orders where status = 4 and order_time < 当前时间-1小时
        LocalDateTime time = LocalDateTime.now().plusMinutes(-60);
        List<Orders> orderList = orderMapper.getByStatusAndOrderTimeLT(Orders.DELIVERY_IN_PROGRESS, time);
        if(orderList != null && orderList.size() > 0){
            for(Orders orders : orderList){
                orders.setStatus(Orders.COMPLETED);
                orders.setCancelReason("Order timeout: Auto-Cancellation ");
                orders.setCancelTime(LocalDateTime.now());
                orderMapper.update(orders);
            }
        }



    }

}
