package com.project.ShoesProject.service.Impl;

import com.project.ShoesProject.dto.OrderDTO;
import com.project.ShoesProject.entity.Order;
import com.project.ShoesProject.entity.OrderStatus;
import com.project.ShoesProject.entity.User;
import com.project.ShoesProject.exception.DataNotFoundException;
import com.project.ShoesProject.repository.OrderRepository;
import com.project.ShoesProject.repository.UserRepository;
import com.project.ShoesProject.response.OrderResponse;
import com.project.ShoesProject.service.IOrderService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService implements IOrderService {
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    @Override
    public Order create(OrderDTO orderDTO) throws Exception {
        User user = userRepository.findById(orderDTO.getUserId())
                .orElseThrow(() -> new DataNotFoundException("Cannot find user with ID: "+orderDTO.getUserId()));
        modelMapper.typeMap(OrderDTO.class, Order.class)
                .addMappings(mapper -> mapper.skip(Order::setId));
        Order order = new Order();
        modelMapper.map(orderDTO,order);
        order.setUser(user);
        order.setOrderDate(new Date());
        order.setStatus(OrderStatus.PENDING);
        LocalDate shippingDate = orderDTO.getShippingDate() == null ?  LocalDate.now() :orderDTO.getShippingDate();
        if (shippingDate.isBefore(LocalDate.now())){
            throw new DataNotFoundException("Date must be at least today");
        }
        order.setShippingDate(shippingDate);
        order.setActive(true);
        orderRepository.save(order);
        return order;
    }

    @Override
    public Order get(Long id) {
        return orderRepository.findById(id)
                .orElse(null);
    }

    @Override
    public Order update(Long id, OrderDTO orderDTO) throws DataNotFoundException {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Cannot find order with id: "+id));
        User existingUser = userRepository.findById(orderDTO.getUserId())
                .orElseThrow(() -> new DataNotFoundException("Cannot find user with id: "+orderDTO.getUserId()));
        modelMapper.typeMap(OrderDTO.class,Order.class)
                .addMappings(mapper -> mapper.skip(Order::setId));
        modelMapper.map(orderDTO,order);
        order.setUser(existingUser);
        return orderRepository.save(order);
    }

    @Override
    public void delete(Long id) {
        Order order = orderRepository.findById(id).orElse(null);
        if (order != null){
            order.setActive(false);
            orderRepository.save(order);
        }
    }

    @Override
    public List<Order> findByUserId(Long userId) {
        return orderRepository.findByUserId(userId);
    }
}
