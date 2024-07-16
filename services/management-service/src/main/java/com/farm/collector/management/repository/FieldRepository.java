package com.farm.collector.management.repository;

import com.farm.collector.management.domain.entity.Field;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FieldRepository extends JpaRepository<Field, Long> {

    List<Field> findBySeason(String season);

    List<Field> findByFarmIdAndSeason(Long farmId, String season);
}
