package com.lyj.proj.springbatch_app_10.app.cash.repository;

import com.lyj.proj.springbatch_app_10.app.cash.entity.CashLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CashRepository extends JpaRepository<CashLog, Long> {
}
