package io.github.kji6252.springvue.controller;

import io.github.kji6252.springvue.remote.KakaoClient;
import io.github.kji6252.springvue.service.dto.KakaoBlogResultDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class SearchController {

    private final KakaoClient kakaoClient;

    @GetMapping("/search")
    public KakaoBlogResultDTO getBlogs(@RequestParam String query) {
        return kakaoClient.getBlogResult(query);
    }
}
