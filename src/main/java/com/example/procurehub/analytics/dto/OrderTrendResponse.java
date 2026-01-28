package com.example.procurehub.analytics.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class OrderTrendResponse {
    private String period;
    private Long total;
}
