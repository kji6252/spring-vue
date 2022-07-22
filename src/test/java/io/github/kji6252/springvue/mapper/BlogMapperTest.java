package io.github.kji6252.springvue.mapper;

import io.github.kji6252.springvue.remote.dto.KakaoBlogResultDTO;
import io.github.kji6252.springvue.remote.dto.NaverBlogResultDTO;
import io.github.kji6252.springvue.service.dto.Blog;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BlogMapperTest {

    @Test
    void testNaverToKakao() {
        /// given
        NaverBlogResultDTO naverBlogResultDTO = new NaverBlogResultDTO(1, 1, 1, Arrays.asList(new NaverBlogResultDTO.Item("타이틀", "링크", "디스크립션", "블로거이름", "블로거링크", "20220101")));
        // when
        KakaoBlogResultDTO kakaoBlogResultDTO = BlogMapper.INSTANCE.naverToKakao(naverBlogResultDTO);
        KakaoBlogResultDTO.Document document = kakaoBlogResultDTO.getDocuments().get(0);
        // then
        assertEquals(1, kakaoBlogResultDTO.getTotalCount());
        assertEquals("타이틀", document.getTitle());
        assertEquals("디스크립션", document.getContents());
        assertEquals("블로거이름", document.getBlogName());
        assertEquals("링크", document.getUrl());
        assertEquals(null, document.getThumbnail());
    }

    @Test
    void testDocumentToBlog() {
        // given
        KakaoBlogResultDTO.Document document = new KakaoBlogResultDTO.Document("타이틀", "컨텐츠", "URL", "블로그이름", "썸네일", ZonedDateTime.now());
        // when
        Blog blog = BlogMapper.INSTANCE.documentToBlog(document);
        // then
        assertEquals("타이틀", blog.getTitle());
        assertEquals("컨텐츠", blog.getDescription());
        assertEquals("URL", blog.getUrl());
        assertEquals("블로그이름", blog.getBlogName());
        assertEquals(LocalDate.now(), blog.getCreatedDate());
    }

    @Test
    void testDocumentsToBlogs() {
        // given
        KakaoBlogResultDTO.Document document = new KakaoBlogResultDTO.Document("타이틀", "컨텐츠", "URL", "블로그이름", "썸네일", ZonedDateTime.now());
        KakaoBlogResultDTO.Document document2 = new KakaoBlogResultDTO.Document("타이틀2", "컨텐츠2", "URL2", "블로그이름2", "썸네일2", ZonedDateTime.now());
        // when
        List<Blog> blogs = BlogMapper.INSTANCE.documentToBlog(Arrays.asList(document, document2));
        // then
        assertEquals(2, blogs.size());
        assertEquals("타이틀", blogs.get(0).getTitle());
        assertEquals("타이틀2", blogs.get(1).getTitle());
    }

}