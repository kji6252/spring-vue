package io.github.kji6252.springvue.service;

import io.github.kji6252.springvue.domain.FavoriteBlog;
import io.github.kji6252.springvue.service.dto.BlogDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FavoriteBlogService {
    void registerFavoriteBlog(BlogDTO blogDTO, String userName);

    void removeFavoriteBlog(String userName, int blogHashCode);

    Page<FavoriteBlog> getFavoriteBlogs(Pageable pageable);
}
