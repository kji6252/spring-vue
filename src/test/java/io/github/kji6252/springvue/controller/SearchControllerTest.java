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
    void testSearch() throws Exception {
        mockMvc
                .perform(get("/api/search")
                                 .param("query", "나이키")
                                 .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content.size()").value("10"))
                .andExpect(jsonPath("$.number").value("1"))
                .andExpect(jsonPath("$.size").value("10"));
    }

    @Test
    void testExceptionValidationNotBlankSearch() throws Exception {
        mockMvc
                .perform(get("/api/search")
                                 .param("query", "")
                                 .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.violations[0].field").value("getBlogs.query"))
                .andExpect(jsonPath("$.violations[0].message").value("must not be blank"));
    }

    @Test
    void testExceptionValidationMinPageSearch() throws Exception {
        mockMvc
                .perform(get("/api/search")
                                 .param("query", "나이키")
                                 .param("page", "0")
                                 .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.violations[0].field").value("getBlogs.page"))
                .andExpect(jsonPath("$.violations[0].message").value("must be greater than or equal to 1"));
    }

    @Test
    void testExceptionValidationMaxPageSearch() throws Exception {
        mockMvc
                .perform(get("/api/search")
                                 .param("query", "나이키")
                                 .param("page", "51")
                                 .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.violations[0].field").value("getBlogs.page"))
                .andExpect(jsonPath("$.violations[0].message").value("must be less than or equal to 50"));
    }

    @Test
    void testHotKeywords() throws Exception {
        mockMvc
                .perform(get("/api/search")
                                 .param("query", "나이키")
                                 .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content.size()").value("10"))
                .andExpect(jsonPath("$.number").value("1"))
                .andExpect(jsonPath("$.size").value("10"));

        mockMvc
                .perform(get("/api/hot-keywords")
                                 .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].keyword").value("나이키"))
                .andExpect(jsonPath("$[0].searchCount").value("1"));
    }

}