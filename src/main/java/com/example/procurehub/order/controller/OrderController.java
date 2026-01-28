package com.example.procurehub.order.controller;

import com.example.procurehub.order.dto.OrderRequest;
import com.example.procurehub.order.dto.OrderResponse;
import com.example.procurehub.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
@CrossOrigin
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/{userId}")
    public OrderResponse create(
            @PathVariable Long userId,
            @RequestBody OrderRequest request
    ) {
        return orderService.create(userId, request);
    }

    @GetMapping
    public List<OrderResponse> findAll() {
        return orderService.findAll();
    }

    @GetMapping("/{id}")
    public OrderResponse findById(@PathVariable Long id) {
        return orderService.findById(id);
    }
}
