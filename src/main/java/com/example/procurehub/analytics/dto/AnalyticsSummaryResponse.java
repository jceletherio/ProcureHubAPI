package com.example.procurehub.analytics.dto;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@Builder
public class AnalyticsSummaryResponse {

    private Long totalOrders;
    private Long approvedOrders;
    private Long rejectedOrders;
    private Long pendingOrders;

    private BigDecimal totalAmount;
    private BigDecimal approvedAmount;
}
