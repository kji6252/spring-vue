package io.github.kji6252.springvue.service;

import io.github.kji6252.springvue.mapper.BlogMapper;
import io.github.kji6252.springvue.remote.KakaoClient;
import io.github.kji6252.springvue.remote.dto.KakaoBlogResultDTO;
import io.github.kji6252.springvue.service.dto.Blog;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class BlogServiceImpl implements BlogService {

    private final KakaoClient kakaoClient;
    private final HotKeywordService hotKeywordService;

    @Cacheable(value = "queryBlogsFirstPage", key = "#query", condition = "#pageable.pageNumber == 1", sync = true)
    @Override
    public Page<Blog> getBlogResult(String query, Pageable pageable) {
        if (pageable.getPageNumber() == 1) {
            hotKeywordService.queryCount(query);
        }
        KakaoBlogResultDTO blogResult = kakaoClient.getBlogResult(query,
                                                                  pageable.getPageNumber(),
                                                                  pageable.getPageSize());
        return new PageImpl<>(BlogMapper.INSTANCE.documentToBlog(blogResult.getDocuments()),
                              pageable,
                              blogResult.getTotalCount());
    }

}
