package com.example.procurehub.analytics.service;

import com.example.procurehub.analytics.dto.AnalyticsSummaryResponse;
import com.example.procurehub.analytics.dto.OrderTrendResponse;
import com.example.procurehub.order.entity.OrderStatus;
import com.example.procurehub.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AnalyticsService {

    private final OrderRepository orderRepository;

    public AnalyticsSummaryResponse getSummary() {

        return AnalyticsSummaryResponse.builder()
                .totalOrders(orderRepository.count())
                .approvedOrders(orderRepository.countByStatus(OrderStatus.APPROVED))
                .rejectedOrders(orderRepository.countByStatus(OrderStatus.REJECTED))
                .pendingOrders(orderRepository.countByStatus(OrderStatus.CREATED))
                .totalAmount(orderRepository.sumTotalAmount())
                .approvedAmount(orderRepository.sumTotalByStatus(OrderStatus.APPROVED))
                .build();
    }

    public List<OrderTrendResponse> getTrends() {

        return orderRepository.countOrdersGroupedByMonth()
                .stream()
                .map(row -> OrderTrendResponse.builder()
                        .period(String.valueOf(row[0]))
                        .total((Long) row[1])
                        .build()
                )
                .toList();
    }
}
