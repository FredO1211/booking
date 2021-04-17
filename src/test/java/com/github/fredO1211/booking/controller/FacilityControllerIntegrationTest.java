package com.github.fredO1211.booking.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fredO1211.booking.domain.Facility;
import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class FacilityControllerIntegrationTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    Flyway flyway;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    public void shouldReturnStatusCreatedWithValidData() throws Exception {
        // given
        Facility facility = new Facility("Name", 300);
        // when + then
        var result = mockMvc.perform(MockMvcRequestBuilders.post("/facilities")
                .content(objectMapper.writeValueAsString(facility))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated()).andReturn();

    }

    @Test
    public void shouldReturnStatusOkWhen() throws Exception {
        // given
        Facility facility = new Facility("Name", 100);
        // when + then
        var result = mockMvc.perform(MockMvcRequestBuilders.post("/facilities")
                .content(objectMapper.writeValueAsString(facility))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isConflict()).andReturn();

    }

    @Test
    public void shouldReturnStatusConflictForReservedFacilityName() throws Exception {
        // given
        Facility facility = new Facility("Name", 100);
        // when + then
        var result = mockMvc.perform(MockMvcRequestBuilders.post("/facilities")
                .content(objectMapper.writeValueAsString(facility))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isConflict()).andReturn();

    }
}