package com.farm.collector.management.controller;

import com.farm.collector.management.AbstractIntegrationTest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

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
        mockMvc.perform(post("/farms")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\": \"MyFarm\"}"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("success"))
                .andExpect(jsonPath("$.data").isNotEmpty());
    }
}
