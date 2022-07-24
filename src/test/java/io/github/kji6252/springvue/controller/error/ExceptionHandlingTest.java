package io.github.kji6252.springvue.controller.error;

import io.github.kji6252.springvue.IntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@IntegrationTest
class ExceptionHandlingTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testUnauthorized() throws Exception {
        mockMvc
                .perform(get("/api/exception-handling-test/unauthorized"))
                .andExpect(status().isUnauthorized())
                .andExpect(content().contentType(MediaType.APPLICATION_PROBLEM_JSON))
                .andExpect(jsonPath("$.status").value("401"))
                .andExpect(jsonPath("$.title").value("Unauthorized"))
                .andExpect(jsonPath("$.detail").value("Full authentication is required to access this resource"));
    }

    @WithMockUser
    @Test
    void testExceptionWithResponseStatus() throws Exception {
        mockMvc
                .perform(get("/api/exception-handling-test/response-status"))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_PROBLEM_JSON))
                .andExpect(jsonPath("$.status").value("400"))
                .andExpect(jsonPath("$.title").value("test response status"));
    }

    @WithMockUser
    @Test
    void testInternalServerError() throws Exception {
        mockMvc
                .perform(get("/api/exception-handling-test/internal-server-error"))
                .andExpect(status().isInternalServerError())
                .andExpect(content().contentType(MediaType.APPLICATION_PROBLEM_JSON))
                .andExpect(jsonPath("$.status").value("500"))
                .andExpect(jsonPath("$.title").value("Internal Server Error"));
    }
}