package com.example.procurehub.approval.repository;

import com.example.procurehub.approval.entity.Approval;
import com.example.procurehub.approval.entity.ApprovalStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ApprovalRepository extends JpaRepository<Approval, Long> {

    List<Approval> findByStatus(ApprovalStatus status);
}
