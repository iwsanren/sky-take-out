package com.sky.mapper;

import com.sky.annotation.AutoFill;
import com.sky.entity.Orders;
import com.sky.enumeration.OperationType;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface OrderMapper {
    /**
     * Insert one record into order sheet
     * @param orders
     */
    public void insert(Orders orders);

    /**
     * Enquiry orders by status and order time.
     * @param status
     * @param orderTime
     * @return
     */
    @Select("select * from orders where status = #{status} and order_time < #{orderTime}")
    List<Orders> getByStatusAndOrderTimeLT(Integer status, LocalDateTime orderTime);

    /**
     * update the order
     * @param orders
     */
    void update(Orders orders);



}
