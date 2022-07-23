package io.github.kji6252.springvue.controller;

import io.github.kji6252.springvue.controller.vm.FavoriteBlogVM;
import io.github.kji6252.springvue.domain.FavoriteBlog;
import io.github.kji6252.springvue.mapper.BlogMapper;
import io.github.kji6252.springvue.service.FavoriteBlogService;
import io.github.kji6252.springvue.service.dto.BlogDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class UserBlogController {

    private final FavoriteBlogService favoriteBlogService;

    @PostMapping("/favorite-blogs")
    @ResponseStatus(HttpStatus.CREATED)
    public void registerFavoriteBlog(@RequestBody BlogDTO blogDTO, Authentication authentication) {
        favoriteBlogService.registerFavoriteBlog(blogDTO, authentication.getName());
    }

    @DeleteMapping("/favorite-blogs/{blogHashCode}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeFavoriteBlog(@PathVariable int blogHashCode, Authentication authentication) {
        favoriteBlogService.removeFavoriteBlog(authentication.getName(), blogHashCode);
    }

    @GetMapping("/favorite-blogs")
    public Page<FavoriteBlogVM> getFavoriteBlogs(@PageableDefault Pageable pageable) {
        Page<FavoriteBlog> favoriteBlogs = favoriteBlogService.getFavoriteBlogs(pageable);

        return new PageImpl<>(BlogMapper.INSTANCE.entityToVM(favoriteBlogs.getContent()),
                              favoriteBlogs.getPageable(),
                              favoriteBlogs.getTotalElements());
    }
}
