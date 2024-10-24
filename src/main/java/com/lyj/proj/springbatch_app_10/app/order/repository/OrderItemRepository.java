package com.lyj.proj.springbatch_app_10.app.order.repository;

import com.lyj.proj.springbatch_app_10.app.order.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
