package io.github.kji6252.springvue.service;

import io.github.kji6252.springvue.remote.KakaoClient;
import io.github.kji6252.springvue.service.dto.BlogDTO;
import io.github.kji6252.springvue.remote.dto.KakaoBlogResultDTO;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoSettings;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.time.ZonedDateTime;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

@MockitoSettings
class BlogServiceImplTest {

    @InjectMocks
    private BlogServiceImpl blogService;

    @Mock
    private KakaoClient kakaoClient;

    @Test
    void testBlogResult() {
        // given
        KakaoBlogResultDTO.Document document = new KakaoBlogResultDTO.Document("타이틀", "컨텐츠", "url", "블로그이름", "썸네일", ZonedDateTime.now());
        KakaoBlogResultDTO.Document document2 = new KakaoBlogResultDTO.Document("타이틀2", "컨텐츠", "url", "블로그이름", "썸네일", ZonedDateTime.now());
        KakaoBlogResultDTO.Document document3 = new KakaoBlogResultDTO.Document("타이틀3", "컨텐츠", "url", "블로그이름", "썸네일", ZonedDateTime.now());
        KakaoBlogResultDTO.Document document4 = new KakaoBlogResultDTO.Document("타이틀4", "컨텐츠", "url", "블로그이름", "썸네일", ZonedDateTime.now());
        KakaoBlogResultDTO.Document document5 = new KakaoBlogResultDTO.Document("타이틀5", "컨텐츠", "url", "블로그이름", "썸네일", ZonedDateTime.now());
        KakaoBlogResultDTO value = new KakaoBlogResultDTO(new KakaoBlogResultDTO.Meta(5, false), Arrays.asList(document, document2, document3, document4, document5));
        given(kakaoClient.getBlogResult(anyString(), anyInt(), anyInt())).willReturn(value);
        // when
        Page<BlogDTO> blogs = blogService.getBlogResult("나이키", PageRequest.of(1, 2));
        // then
        assertEquals(1, blogs.getNumber());
        assertEquals(2, blogs.getSize());
        assertEquals(3, blogs.getTotalPages());
        assertEquals(5, blogs.getTotalElements());
        assertEquals("타이틀", blogs.getContent().get(0).getTitle());
        assertEquals("타이틀2", blogs.getContent().get(1).getTitle());
        assertEquals("타이틀3", blogs.getContent().get(2).getTitle());
        assertEquals("타이틀4", blogs.getContent().get(3).getTitle());
        assertEquals("타이틀5", blogs.getContent().get(4).getTitle());
    }
}