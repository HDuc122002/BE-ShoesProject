package com.project.ShoesProject.controller;

import com.project.ShoesProject.dto.OrderDTO;
import com.project.ShoesProject.entity.Order;
import com.project.ShoesProject.service.Impl.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${api.prefix}/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody OrderDTO orderDTO) throws Exception {
        try {
            Order orderResponse = orderService.create(orderDTO);
            return ResponseEntity.ok(orderResponse);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOrder(@PathVariable Long id){
        try {
            Order order = orderService.get(id);
            return ResponseEntity.ok(order);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getAllOrderByUserId(@PathVariable Long userId){
        try {
            List<Order> orders = orderService.findByUserId(userId);
            return ResponseEntity.ok(orders);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable Long id,
                                    @RequestBody OrderDTO orderDTO){
        try {
            Order order = orderService.update(id,orderDTO);
            return ResponseEntity.ok(order);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        try{
            orderService.delete(id);
            return ResponseEntity.ok("Order deleted successfully");
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
