package com.farm.collector.management.service;

import com.farm.collector.management.domain.entity.Field;
import com.farm.collector.management.domain.request.FieldRequest;
import com.farm.collector.management.repository.FarmRepository;
import com.farm.collector.management.repository.FieldRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@SuppressWarnings("unused")
public class FieldService {

    private final FieldRepository fieldRepository;
    private final FarmRepository farmRepository;

    public Field saveField(FieldRequest fieldRequest) {

        final var farm = farmRepository.findById(fieldRequest.farmId()).orElseThrow();

        final var field = Field.builder()
                .season(fieldRequest.season())
                .actualHarvestedProduct(fieldRequest.actualHarvestedProduct())
                .cropType(fieldRequest.cropType())
                .expectedProduct(fieldRequest.expectedProduct())
                .plantingArea(fieldRequest.plantingArea())
                .farm(farm)
                .build();
        return fieldRepository.save(field);
    }

    public List<Field> getFieldsBySeason(String season) {
        return fieldRepository.findBySeason(season);
    }

    public List<Field> getFieldsByFarmAndSeason(String farmId, String season) {
        return fieldRepository.findByFarmIdAndSeason(farmId, season);
    }
}
