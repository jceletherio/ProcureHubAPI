package com.example.procurehub.approval.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class ApprovalResponse {
    private Long id;
    private Long orderId;
    private String approverEmail;
    private String status;
    private String note;
    private LocalDateTime decidedAt;
}
