package com.lyj.proj.springbatch_app_10.app.calculate.repository;

import com.lyj.proj.springbatch_app_10.app.calculate.entity.CalculateOrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CalculateOrderItemRepository extends JpaRepository<CalculateOrderItem, Long> {
    Optional<CalculateOrderItem> findByOrderItemId(long orderItemId);
}
