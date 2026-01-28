package com.example.procurehub.approval.service;

import com.example.procurehub.approval.dto.ApprovalDecisionRequest;
import com.example.procurehub.approval.dto.ApprovalResponse;
import com.example.procurehub.approval.entity.*;
import com.example.procurehub.approval.repository.ApprovalRepository;
import com.example.procurehub.order.entity.Order;
import com.example.procurehub.order.entity.OrderStatus;
import com.example.procurehub.order.repository.OrderRepository;
import com.example.procurehub.user.entity.User;
import com.example.procurehub.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ApprovalService {

    private final ApprovalRepository approvalRepository;
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;

    public ApprovalResponse approve(Long approvalId, Long approverId, String note) {

        Approval approval = getApproval(approvalId, approverId);

        approval.setStatus(ApprovalStatus.APPROVED);
        approval.setNote(note);
        approval.setDecidedAt(LocalDateTime.now());

        Order order = approval.getOrder();
        order.setStatus(OrderStatus.APPROVED);

        orderRepository.save(order);
        approvalRepository.save(approval);

        return toResponse(approval);
    }

    public ApprovalResponse reject(Long approvalId, Long approverId, String note) {

        Approval approval = getApproval(approvalId, approverId);

        approval.setStatus(ApprovalStatus.REJECTED);
        approval.setNote(note);
        approval.setDecidedAt(LocalDateTime.now());

        Order order = approval.getOrder();
        order.setStatus(OrderStatus.REJECTED);

        orderRepository.save(order);
        approvalRepository.save(approval);

        return toResponse(approval);
    }

    public List<ApprovalResponse> findPending() {
        return approvalRepository.findByStatus(ApprovalStatus.PENDING)
                .stream()
                .map(this::toResponse)
                .toList();
    }

    private Approval getApproval(Long approvalId, Long approverId) {
        Approval approval = approvalRepository.findById(approvalId)
                .orElseThrow(() -> new RuntimeException("Aprovação não encontrada"));

        User approver = userRepository.findById(approverId)
                .orElseThrow(() -> new RuntimeException("Aprovador não encontrado"));

        approval.setApprover(approver);

        return approval;
    }

    private ApprovalResponse toResponse(Approval approval) {
        return ApprovalResponse.builder()
                .id(approval.getId())
                .orderId(approval.getOrder().getId())
                .approverEmail(
                        approval.getApprover() != null
                                ? approval.getApprover().getEmail()
                                : null
                )
                .status(approval.getStatus().name())
                .note(approval.getNote())
                .decidedAt(approval.getDecidedAt())
                .build();
    }
}
