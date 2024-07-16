package com.farm.collector.management.controller;

import com.farm.collector.commons.ApiResponse;
import com.farm.collector.management.domain.entity.Field;
import com.farm.collector.management.domain.request.FieldRequest;
import com.farm.collector.management.service.FieldService;
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

import java.util.List;

@RestController
@RequestMapping(path = "/fields")
@Slf4j
@RequiredArgsConstructor
@SuppressWarnings("unused")
public class FieldController {

    private final FieldService fieldService;

    @PostMapping("/add")
    @Timed("addField")
    @Operation(description = "This API is used to add the field.",
            summary = "This API adds the field properties.")
    public ApiResponse<Field> addField(@RequestBody FieldRequest fieldRequest) {
        return ApiResponse.success(fieldService.saveField(fieldRequest));
    }

    @GetMapping("/season/{season}")
    @Timed("getFields")
    @Operation(description = "This API is used to retrieve the fields.",
            summary = "This API retrieves the fields on the basis of season.")
    public ApiResponse<List<Field>> getFieldsBySeason(@PathVariable String season) {
        return ApiResponse.success(fieldService.getFieldsBySeason(season));
    }

    @GetMapping("/farm/{farmId}/season/{season}")
    @Timed("getFieldsByFarm")
    @Operation(description = "This API is used to retrieve the fields.",
            summary = "This API retrieves the fields on the basis of farm and the season.")
    public ApiResponse<List<Field>> getFieldsByFarmAndSeason(@PathVariable String farmId, @PathVariable String season) {
        return ApiResponse.success(fieldService.getFieldsByFarmAndSeason(farmId, season));
    }
}