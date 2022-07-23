package io.github.kji6252.springvue.controller;

import io.github.kji6252.springvue.domain.HotKeyword;
import io.github.kji6252.springvue.service.BlogService;
import io.github.kji6252.springvue.service.HotKeywordService;
import io.github.kji6252.springvue.service.dto.BlogDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
@Validated
public class SearchController {

    private final BlogService blogService;
    private final HotKeywordService hotKeywordService;

    @GetMapping("/search")
    public Page<BlogDTO> getBlogs(@RequestParam @NotBlank String query,
                                  @PageableDefault(page = 1) Pageable pageable,
                                  @RequestParam(defaultValue = "1") @Min(1) @Max(50) int page) {
        return blogService.getBlogResult(query, pageable);
    }

    @GetMapping("/hot-keywords")
    public List<HotKeyword> getHotKeywords() {
        return hotKeywordService.getTop10HotKeyword();
    }
}
