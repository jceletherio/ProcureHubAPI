package com.example.procurehub.analytics.controller;

import com.example.procurehub.analytics.dto.AnalyticsSummaryResponse;
import com.example.procurehub.analytics.dto.OrderTrendResponse;
import com.example.procurehub.analytics.service.AnalyticsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/analytics")
@RequiredArgsConstructor
@CrossOrigin
public class AnalyticsController {

    private final AnalyticsService analyticsService;

    @GetMapping
    public AnalyticsSummaryResponse summary() {
        return analyticsService.getSummary();
    }

    @GetMapping("/trends")
    public List<OrderTrendResponse> trends() {
        return analyticsService.getTrends();
    }
}
