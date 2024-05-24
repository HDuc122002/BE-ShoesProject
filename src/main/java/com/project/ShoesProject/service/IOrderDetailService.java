package com.project.ShoesProject.service;

import com.project.ShoesProject.dto.OrderDetailDTO;
import com.project.ShoesProject.entity.OrderDetail;
import com.project.ShoesProject.exception.DataNotFoundException;

import java.util.List;

public interface IOrderDetailService {
    OrderDetail create(OrderDetailDTO orderDetailDTO) throws Exception;
    OrderDetail get(Long id) throws Exception;
    OrderDetail update(Long id,OrderDetailDTO orderDetailDTO) throws Exception;
    void delete(Long id);

    List<OrderDetail> findByOrderId(Long id);
}
