package io.github.kji6252.springvue.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.kji6252.springvue.IntegrationTest;
import io.github.kji6252.springvue.service.dto.UserAndPasswordDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@IntegrationTest
class SearchControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @Test
    void testExceptionRegisterUser() throws Exception {
        mockMvc
                .perform(get("/api/search")
                                 .accept(MediaType.APPLICATION_JSON).param("query", "나이키"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value("400"))
                .andExpect(jsonPath("$.violations[0].field").value("password"))
                .andExpect(jsonPath("$.violations[0].message").value("size must be between 4 and 50"))
                .andExpect(jsonPath("$.violations[1].field").value("username"))
                .andExpect(jsonPath("$.violations[1].message").value("size must be between 1 and 50"));
    }

}