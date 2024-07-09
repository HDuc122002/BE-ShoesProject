package com.project.ShoesProject.controller;

import com.project.ShoesProject.dto.OrderDetailDTO;
import com.project.ShoesProject.entity.OrderDetail;
import com.project.ShoesProject.response.OrderDetailResponse;
import com.project.ShoesProject.service.Impl.OrderDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("${api.prefix}/order_details")
public class OrderDetailController {
    private final OrderDetailService orderDetailService;
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody OrderDetailDTO orderDetailDTO) throws Exception {
        try {
            OrderDetail newOrderDetail = orderDetailService.create(orderDetailDTO);
            return ResponseEntity.ok(OrderDetailResponse.fromOrderDetail(newOrderDetail));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOrderDetail(@PathVariable Long id){
        try {
            OrderDetail orderDetail = orderDetailService.get(id);
            return ResponseEntity.ok(OrderDetailResponse.fromOrderDetail(orderDetail));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/order/{id}")
    public ResponseEntity<?> getOrderDetails(@PathVariable Long id){
        try {
            List<OrderDetail> orderDetails = orderDetailService.findByOrderId(id);
            List<OrderDetailResponse> orderDetailResponses = orderDetails
                    .stream().map(OrderDetailResponse::fromOrderDetail).toList();
            return ResponseEntity.ok(orderDetailResponses);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable Long id,
                                    @RequestBody OrderDetailDTO orderDetailDTO){
        try {
            OrderDetail orderDetail = orderDetailService.update(id,orderDetailDTO);
            return ResponseEntity.ok(orderDetail);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        try{
            orderDetailService.delete(id);
            return ResponseEntity.ok("Order deleted successfully");
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
