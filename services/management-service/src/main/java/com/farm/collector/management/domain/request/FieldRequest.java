package com.farm.collector.management.domain.request;

import lombok.Builder;

@Builder
public record FieldRequest(

        String farmId,
        String cropType,
        double plantingArea,
        double expectedProduct,
        double actualHarvestedProduct,
        String season) {
}
