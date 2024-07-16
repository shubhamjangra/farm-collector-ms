package com.farm.collector.management.domain.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.util.List;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

@Builder
public record FarmRequest(
        @NotBlank
        @Schema(requiredMode = REQUIRED, example = "MyFarm")
        String name,

        @NotNull
        @Valid
        List<FieldRequest> fields) {
}
