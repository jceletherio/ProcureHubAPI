package com.example.procurehub.order.repository;

import com.example.procurehub.order.entity.Order;
import com.example.procurehub.order.entity.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    long countByStatus(OrderStatus status);

    @Query("SELECT COALESCE(SUM(o.total), 0) FROM Order o")
    BigDecimal sumTotalAmount();

    @Query("SELECT COALESCE(SUM(o.total), 0) FROM Order o WHERE o.status = :status")
    BigDecimal sumTotalByStatus(OrderStatus status);

    @Query("""
                SELECT TO_CHAR(o.createdAt, 'YYYY-MM') as period, COUNT(o)
                FROM Order o
                GROUP BY TO_CHAR(o.createdAt, 'YYYY-MM')
            """)
    List<Object[]> countOrdersGroupedByMonth();

}
