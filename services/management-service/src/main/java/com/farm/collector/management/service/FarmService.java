package com.farm.collector.management.service;

import com.farm.collector.management.domain.entity.Farm;
import com.farm.collector.management.domain.entity.Field;
import com.farm.collector.management.domain.request.FarmRequest;
import com.farm.collector.management.repository.FarmRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@SuppressWarnings("unused")
public class FarmService {

    private final FarmRepository farmRepository;

    public Farm saveFarm(FarmRequest farmRequest) {

        final var farmBuilder = Farm.builder()
                .name(farmRequest.name());

        farmBuilder.fields(farmRequest.fields().stream().map(mapper ->
                        Field.builder()
                                .season(mapper.season())
                                .actualHarvestedProduct(mapper.actualHarvestedProduct())
                                .cropType(mapper.cropType())
                                .expectedProduct(mapper.expectedProduct())
                                .plantingArea(mapper.plantingArea())
                                .farm(Objects.nonNull(mapper.farmId())
                                        ? farmRepository.findById(mapper.farmId()).orElseThrow()
                                        : farmBuilder.build())
                                .build()).toList())
                .build();

        return farmRepository.save(farmBuilder.build());
    }

    public Optional<Farm> getFarmById(String id) {
        return farmRepository.findById(id);
    }
}
