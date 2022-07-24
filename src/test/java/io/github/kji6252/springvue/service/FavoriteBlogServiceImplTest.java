package io.github.kji6252.springvue.service;

import io.github.kji6252.springvue.domain.Blog;
import io.github.kji6252.springvue.domain.FavoriteBlog;
import io.github.kji6252.springvue.domain.FavoriteBlogID;
import io.github.kji6252.springvue.repository.FavoriteBlogRepository;
import io.github.kji6252.springvue.service.dto.BlogDTO;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoSettings;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@MockitoSettings
class FavoriteBlogServiceImplTest {

    @InjectMocks
    private FavoriteBlogServiceImpl favoriteBlogService;

    @Mock
    private FavoriteBlogRepository favoriteBlogRepository;

    @Test
    void testRegisterFavoriteBlog() {
        // given
        given(favoriteBlogRepository.existsById(any())).willReturn(false);
        // when
        favoriteBlogService.registerFavoriteBlog(new BlogDTO("타이틀", "디스크립션", "url", "블로그이름", LocalDate.now()), "user");
        // then
        then(favoriteBlogRepository).should().save(any());
    }

    @Test
    void testExceptionRegisterFavoriteBlog() {
        // given
        given(favoriteBlogRepository.existsById(any())).willReturn(true);
        // when
        // then
        assertThrows(FavoriteBlogServiceImpl.FavoriteBlogServiceException.class,
                     () -> favoriteBlogService.registerFavoriteBlog(new BlogDTO("타이틀", "디스크립션", "url", "블로그이름", LocalDate.now()), "user"),
                     "a");
    }

    @Test
    void testRemoveFavoriteBlog() {
        // given
        given(favoriteBlogRepository.existsById(any())).willReturn(true);
        // when
        favoriteBlogService.removeFavoriteBlog("user", 1);
        // then
        then(favoriteBlogRepository).should().deleteById(any());
    }

    @Test
    void testExceptionRemoveFavoriteBlog() {
        // given
        given(favoriteBlogRepository.existsById(any())).willReturn(false);
        // when
        // then
        assertThrows(FavoriteBlogServiceImpl.FavoriteBlogServiceException.class,
                     () -> favoriteBlogService.removeFavoriteBlog("user", 1),
                     "Remove Blog Not Found");
    }

    @Test
    void getFavoriteBlogs() {
        // given
        FavoriteBlog favoriteBlog = FavoriteBlog.of(FavoriteBlogID.of("user", 1), new Blog(), LocalDateTime.now());
        given(favoriteBlogRepository.findAllByUserName(any(), anyString())).willReturn(new PageImpl<>(Arrays.asList(favoriteBlog)));
        // when
        Page<FavoriteBlog> favoriteBlogPage = favoriteBlogService.getFavoriteBlogs(PageRequest.of(0, 10), "user");
        // then
        assertEquals(1, favoriteBlogPage.getTotalElements());
        assertEquals(0, favoriteBlogPage.getNumber());
        assertEquals(1, favoriteBlogPage.getTotalPages());
        assertIterableEquals(Arrays.asList(favoriteBlog), favoriteBlogPage.getContent());
    }
}