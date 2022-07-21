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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@IntegrationTest
class AccountControllerTest {

    private static final ObjectMapper OBJECT_MAPPER = Jackson2ObjectMapperBuilder.json().build();

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testAuthenticatedUser() throws Exception {
        mockMvc
                .perform(get("/api/authentication")
                                 .accept(MediaType.APPLICATION_FORM_URLENCODED)
                                 .content("username=\"user\"&password=\"password\""))
                .andExpect(status().isOk());
    }

    @Test
    void testNonAuthenticatedUser() throws Exception {
        mockMvc
                .perform(get("/api/account")
                                 .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized())
                .andExpect(content().contentType(MediaType.APPLICATION_PROBLEM_JSON))
                .andExpect(jsonPath("$.status").value("401"))
                .andExpect(jsonPath("$.title").value("Unauthorized"))
                .andExpect(jsonPath("$.detail").value("Full authentication is required to access this resource"));
    }

    @Test
    void testRegisterUser() throws Exception {
        mockMvc
                .perform(post("/api/register")
                                 .contentType(MediaType.APPLICATION_JSON)
                                 .content(OBJECT_MAPPER.writeValueAsBytes(new UserAndPasswordDTO("1", "1234"))))
                .andExpect(status().isCreated());
    }

    @Test
    void testExceptionRegisterUser() throws Exception {
        mockMvc
                .perform(post("/api/register")
                                 .contentType(MediaType.APPLICATION_JSON)
                                 .content(OBJECT_MAPPER.writeValueAsBytes(new UserAndPasswordDTO("", ""))))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value("400"))
                .andExpect(jsonPath("$.violations[0].field").value("password"))
                .andExpect(jsonPath("$.violations[0].message").value("size must be between 4 and 50"))
                .andExpect(jsonPath("$.violations[1].field").value("username"))
                .andExpect(jsonPath("$.violations[1].message").value("size must be between 1 and 50"));
    }


}