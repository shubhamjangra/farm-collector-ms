package com.farm.collector.management.controller;

import com.farm.collector.management.AbstractIntegrationTest;
import com.farm.collector.management.domain.entity.Farm;
import com.farm.collector.management.domain.request.FarmRequest;
import com.farm.collector.management.domain.request.FieldRequest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import java.util.Collections;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class FarmControllerTests extends AbstractIntegrationTest {

    @AfterEach
    void tearDown() {
        farmRepository.deleteAll();
    }

    @Test
    public void test_add_farm() throws Exception {
        final var farm = farmRepository.save(Farm.builder().name("MyFarm").build());

        String content = objectMapper.writeValueAsString(FarmRequest.builder()
                .name("MyFarm")
                .fields(Collections.singletonList(FieldRequest.builder()
                        .season("winters")
                        .farmId(farm.getId())
                        .build()))
                .build());

        mockMvc.perform(post("/api/management-service/farms/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(content))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("success"))
                .andExpect(jsonPath("$.data").isNotEmpty());
    }
}
