package com.example.procurehub.approval.controller;

import com.example.procurehub.approval.dto.ApprovalDecisionRequest;
import com.example.procurehub.approval.dto.ApprovalResponse;
import com.example.procurehub.approval.service.ApprovalService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/approvals")
@RequiredArgsConstructor
@CrossOrigin
public class ApprovalController {

    private final ApprovalService approvalService;

    @GetMapping("/pending")
    public List<ApprovalResponse> pending() {
        return approvalService.findPending();
    }

    @PostMapping("/{approvalId}/approve/{approverId}")
    public ApprovalResponse approve(
            @PathVariable Long approvalId,
            @PathVariable Long approverId,
            @RequestBody ApprovalDecisionRequest request
    ) {
        return approvalService.approve(approvalId, approverId, request.getNote());
    }

    @PostMapping("/{approvalId}/reject/{approverId}")
    public ApprovalResponse reject(
            @PathVariable Long approvalId,
            @PathVariable Long approverId,
            @RequestBody ApprovalDecisionRequest request
    ) {
        return approvalService.reject(approvalId, approverId, request.getNote());
    }
}
