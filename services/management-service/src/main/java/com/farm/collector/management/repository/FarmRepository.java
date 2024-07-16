package com.farm.collector.management.repository;

import com.farm.collector.management.domain.entity.Farm;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FarmRepository extends JpaRepository<Farm, Long> {
}
