package com.project.ShoesProject.service.Impl;

import com.project.ShoesProject.dto.OrderDetailDTO;
import com.project.ShoesProject.entity.Order;
import com.project.ShoesProject.entity.OrderDetail;
import com.project.ShoesProject.entity.Product;
import com.project.ShoesProject.exception.DataNotFoundException;
import com.project.ShoesProject.repository.OrderDetailRepository;
import com.project.ShoesProject.repository.OrderRepository;
import com.project.ShoesProject.repository.ProductRepository;
import com.project.ShoesProject.service.IOrderDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderDetailService implements IOrderDetailService {
    private final OrderRepository orderRepository;
    private final OrderDetailRepository orderDetailRepository;
    private final ProductRepository productRepository;

    @Override
    public OrderDetail create(OrderDetailDTO orderDetailDTO) throws Exception {
        Order order = orderRepository.findById(orderDetailDTO.getOrderId())
                .orElseThrow(() -> new DataNotFoundException("Cannot find order with id: "+orderDetailDTO.getOrderId()));
        Product product = productRepository.findById(orderDetailDTO.getProductId())
                .orElseThrow(() -> new DataNotFoundException("Cannot find product with id: "+orderDetailDTO.getProductId()));

        OrderDetail orderDetail = OrderDetail.builder()
                .order(order)
                .product(product)
                .numberOfProducts(orderDetailDTO.getNumberOfProducts())
                .totalMoney(orderDetailDTO.getTotalMoney())
                .price(orderDetailDTO.getPrice())
                .color(orderDetailDTO.getColor())
                .build();

        return orderDetailRepository.save(orderDetail);
    }

    @Override
    public OrderDetail get(Long id) throws Exception {
        return orderDetailRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Cannot find order detail with id: "+id));
    }

    @Override
    public OrderDetail update(Long id, OrderDetailDTO orderDetailDTO) throws Exception{
        OrderDetail existingOrderDetail = orderDetailRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Cannot find order detail with id: "+id));
        Order existingOrder = orderRepository.findById(orderDetailDTO.getOrderId())
                .orElseThrow(() -> new DataNotFoundException("Cannot find order with id: "+orderDetailDTO.getOrderId()));
        Product existingProduct = productRepository.findById(orderDetailDTO.getProductId())
                .orElseThrow(() -> new DataNotFoundException("Cannot find product with id: "+orderDetailDTO.getProductId()));

        existingOrderDetail.setPrice(orderDetailDTO.getPrice());
        existingOrderDetail.setNumberOfProducts(orderDetailDTO.getNumberOfProducts());
        existingOrderDetail.setTotalMoney(orderDetailDTO.getTotalMoney());
        existingOrderDetail.setColor(orderDetailDTO.getColor());
        existingOrderDetail.setOrder(existingOrder);
        existingOrderDetail.setProduct(existingProduct);
        return orderDetailRepository.save(existingOrderDetail);
    }

    @Override
    public void delete(Long id) {
        orderDetailRepository.deleteById(id);
    }

    @Override
    public List<OrderDetail> findByOrderId(Long id){
        return orderDetailRepository.findByOrderId(id);
    }
}
