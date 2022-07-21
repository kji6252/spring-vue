package io.github.kji6252.springvue.service;

import io.github.kji6252.springvue.remote.KakaoClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class BlogServiceImpl {

    private final KakaoClient kakaoClient;
}
