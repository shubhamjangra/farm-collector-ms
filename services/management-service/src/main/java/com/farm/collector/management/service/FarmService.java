package com.farm.collector.management.service;

import com.farm.collector.management.domain.entity.Farm;
import com.farm.collector.management.repository.FarmRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@SuppressWarnings("unused")
public class FarmService {

    private final FarmRepository farmRepository;

    public Farm saveFarm(Farm farm) {
        return farmRepository.save(farm);
    }

    public Optional<Farm> getFarmById(Long id) {
        return farmRepository.findById(id);
    }
}
