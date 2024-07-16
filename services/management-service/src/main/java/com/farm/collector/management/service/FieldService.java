package com.farm.collector.management.service;

import com.farm.collector.management.domain.entity.Field;
import com.farm.collector.management.repository.FieldRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@SuppressWarnings("unused")
public class FieldService {

    private final FieldRepository fieldRepository;

    public Field saveField(Field field) {
        return fieldRepository.save(field);
    }

    public List<Field> getFieldsBySeason(String season) {
        return fieldRepository.findBySeason(season);
    }

    public List<Field> getFieldsByFarmAndSeason(Long farmId, String season) {
        return fieldRepository.findByFarmIdAndSeason(farmId, season);
    }
}
