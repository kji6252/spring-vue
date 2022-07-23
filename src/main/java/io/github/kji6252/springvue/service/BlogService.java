package io.github.kji6252.springvue.service;

import io.github.kji6252.springvue.service.dto.BlogDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BlogService {
    Page<BlogDTO> getBlogResult(String query, Pageable pageable);
}
