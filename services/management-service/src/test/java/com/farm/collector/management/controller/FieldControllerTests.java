package com.farm.collector.management.controller;

import com.farm.collector.management.AbstractIntegrationTest;
import com.farm.collector.management.domain.entity.Farm;
import com.farm.collector.management.domain.request.FieldRequest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class FieldControllerTests extends AbstractIntegrationTest {

    @AfterEach
    void tearDown() {
        fieldRepository.deleteAll();
    }

    @Test
    public void test_add_field() throws Exception {
        final var farm = farmRepository.save(Farm.builder().name("MyFarm").build());

        String content = objectMapper.writeValueAsString(FieldRequest.builder()
                .season("winter")
                .farmId(farm.getId())
                .build());

        mockMvc.perform(post("/api/management-service/fields/add")
                        .accept(MediaType.APPLICATION_JSON)
                        .content(content))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("success"))
                .andExpect(jsonPath("$.data").isNotEmpty());
    }
}
