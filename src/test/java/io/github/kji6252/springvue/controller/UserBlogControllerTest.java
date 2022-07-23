package io.github.kji6252.springvue.controller;

import io.github.kji6252.springvue.IntegrationTest;
import io.github.kji6252.springvue.TestUtil;
import io.github.kji6252.springvue.service.dto.BlogDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WithMockUser
@AutoConfigureMockMvc
@IntegrationTest
class UserBlogControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Transactional
    @Test
    void testRegisterFavoriteBlog() throws Exception {
        BlogDTO blogDTO = new BlogDTO("타이틀1", "디스크립션", "url", "블로그이름", LocalDate.now());
        mockMvc
                .perform(post("/api/favorite-blogs")
                                 .contentType(MediaType.APPLICATION_JSON)
                                 .content(TestUtil.OBJECT_MAPPER.writeValueAsBytes(blogDTO)))
                .andExpect(status().isCreated());
    }

    @Transactional
    @Test
    void testExceptionRegisterFavoriteBlog() throws Exception {
        BlogDTO blogDTO = new BlogDTO("타이틀2", "디스크립션", "url", "블로그이름", LocalDate.now());
        mockMvc
                .perform(post("/api/favorite-blogs")
                                 .contentType(MediaType.APPLICATION_JSON)
                                 .content(TestUtil.OBJECT_MAPPER.writeValueAsBytes(blogDTO)))
                .andExpect(status().isCreated());
        mockMvc
                .perform(post("/api/favorite-blogs")
                                 .contentType(MediaType.APPLICATION_JSON)
                                 .content(TestUtil.OBJECT_MAPPER.writeValueAsBytes(blogDTO)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value("400"))
                .andExpect(jsonPath("$.title").value("FavoriteBlogService Error"))
                .andExpect(jsonPath("$.detail").value("Duplicate Blog"));
    }

    @Transactional
    @Test
    void testRemoveFavoriteBlog() throws Exception {
        BlogDTO blogDTO = new BlogDTO("타이틀3", "디스크립션", "url", "블로그이름", LocalDate.now());
        mockMvc
                .perform(post("/api/favorite-blogs")
                                 .contentType(MediaType.APPLICATION_JSON)
                                 .content(TestUtil.OBJECT_MAPPER.writeValueAsBytes(blogDTO)))
                .andExpect(status().isCreated());

        mockMvc
                .perform(delete("/api/favorite-blogs/" + blogDTO.hashCode())
                                 .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    void testExceptionRemoveFavoriteBlog() throws Exception {
        mockMvc
                .perform(delete("/api/favorite-blogs/1")
                                 .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value("400"))
                .andExpect(jsonPath("$.title").value("FavoriteBlogService Error"))
                .andExpect(jsonPath("$.detail").value("Remove Blog Not Found"));
    }

    @Transactional
    @Test
    void testGetFavoriteBlogs() throws Exception {
        BlogDTO blogDTO = new BlogDTO("타이틀4", "디스크립션", "url", "블로그이름", LocalDate.of(2022,7,23));
        mockMvc
                .perform(post("/api/favorite-blogs")
                                 .contentType(MediaType.APPLICATION_JSON)
                                 .content(TestUtil.OBJECT_MAPPER.writeValueAsBytes(blogDTO)))
                .andExpect(status().isCreated());

        mockMvc
                .perform(get("/api/favorite-blogs")
                                 .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].blogHashCode").value(-1934536794))
                .andExpect(jsonPath("$.content[0].title").value("타이틀4"))
                .andExpect(jsonPath("$.content[0].description").value("디스크립션"))
                .andExpect(jsonPath("$.content[0].url").value("url"))
                .andExpect(jsonPath("$.content[0].blogName").value("블로그이름"))
                .andExpect(jsonPath("$.content[0].createdDate").value(LocalDate.of(2022,7,23).toString()));
    }
}