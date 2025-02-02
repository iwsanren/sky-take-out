package com.sky.mapper;

import com.sky.entity.OrderDetail;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrderDetailMapper {

    /**
     * Batch insert records into order_detailed sheet
     * @param orderDetailList
     */
    void insertBatch(List<OrderDetail> orderDetailList);



}
