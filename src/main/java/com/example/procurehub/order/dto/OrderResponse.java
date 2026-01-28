package com.example.procurehub.order.dto;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
public class OrderResponse {
    private Long id;
    private LocalDateTime createdAt;
    private BigDecimal total;
    private String status;
    private List<OrderItemResponse> items;
}
