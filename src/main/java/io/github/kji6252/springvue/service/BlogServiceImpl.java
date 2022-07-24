package io.github.kji6252.springvue.service;

import io.github.kji6252.springvue.mapper.BlogMapper;
import io.github.kji6252.springvue.remote.KakaoClient;
import io.github.kji6252.springvue.remote.dto.KakaoBlogResultDTO;
import io.github.kji6252.springvue.service.dto.BlogDTO;
import io.github.kji6252.springvue.service.dto.QueryCountEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class BlogServiceImpl implements BlogService {

    private final KakaoClient kakaoClient;

    @Cacheable(value = "queryBlogsFirstPage", key = "#query", condition = "#pageable.pageNumber == 1", sync = true)
    @Override
    public Page<BlogDTO> getBlogResult(String query, Pageable pageable) {
        KakaoBlogResultDTO blogResult = kakaoClient.getBlogResult(query,
                                                                  pageable.getPageNumber(),
                                                                  pageable.getPageSize());
        return new PageImpl<>(BlogMapper.INSTANCE.documentToBlog(blogResult.getDocuments()),
                              pageable,
                              blogResult.getTotalCount());
    }

}
