package com.farm.collector.management.controller;

import com.farm.collector.commons.ApiResponse;
import com.farm.collector.management.domain.entity.Farm;
import com.farm.collector.management.domain.request.FarmRequest;
import com.farm.collector.management.service.FarmService;
import io.micrometer.core.annotation.Timed;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/farms")
@Slf4j
@RequiredArgsConstructor
@SuppressWarnings("unused")
public class FarmController {

    private final FarmService farmService;

    @PostMapping("/add")
    @Timed("addFarm")
    @Operation(description = "This API is used to add the farm.",
            summary = "This API adds the farm along with it's field properties.")
    public ApiResponse<Farm> addFarm(@RequestBody FarmRequest farmRequest) {
        return ApiResponse.success(farmService.saveFarm(farmRequest));
    }

    @GetMapping("/{id}")
    @Timed("getFarm")
    @Operation(description = "This API is used to retrieve the farm.",
            summary = "This API retrieve's the farm details along with it's field properties.")
    public ApiResponse<Farm> getFarmById(@PathVariable String id) {
        return ApiResponse.success(farmService.getFarmById(id).orElse(null));
    }
}