package com.project.ShoesProject.service;

import com.project.ShoesProject.dto.OrderDTO;
import com.project.ShoesProject.entity.Order;
import com.project.ShoesProject.exception.DataNotFoundException;

import java.util.List;

public interface IOrderService {
    Order create(OrderDTO orderDTO) throws Exception;
    Order get(Long id);
    Order update(Long id,OrderDTO orderDTO) throws DataNotFoundException;
    void delete(Long id);
    List<Order> findByUserId(Long userId);
}
