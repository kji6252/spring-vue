package io.github.kji6252.springvue.controller;

import io.github.kji6252.springvue.IntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@IntegrationTest
class AccountControllerTest {

    static final String TEST_USER_LOGIN = "test";

    @Autowired
    private MockMvc mockMvc;

    @WithMockUser(value = TEST_USER_LOGIN)
    @Test
    void testAuthenticatedUser() throws Exception {
        mockMvc
                .perform(get("/api/account")
                                 .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(TEST_USER_LOGIN));
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

}