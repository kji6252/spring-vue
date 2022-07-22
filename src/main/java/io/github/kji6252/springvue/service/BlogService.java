package io.github.kji6252.springvue.service;

import io.github.kji6252.springvue.service.dto.Blog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BlogService {
    Page<Blog> getBlogResult(String query, Pageable pageable);
}
