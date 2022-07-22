package io.github.kji6252.springvue.remote;

import feign.FeignException;
import io.github.kji6252.springvue.mapper.BlogMapper;
import io.github.kji6252.springvue.remote.dto.KakaoBlogResultDTO;
import io.github.kji6252.springvue.remote.dto.NaverBlogResultDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "kakao", url = "https://dapi.kakao.com", fallbackFactory = KakaoClient.KaKaoClientFallbackFactory.class)
public interface KakaoClient {

    @GetMapping(path="/v2/search/blog")
    KakaoBlogResultDTO getBlogResult(@RequestParam String query,
                                     @RequestParam int page,
                                     @RequestParam int size);

    @RequiredArgsConstructor
    @Slf4j
    @Component
    class KaKaoClientFallbackFactory implements FallbackFactory<KakaoClient> {

        private final NaverClient naverClient;

        @Override
        public KakaoClient create(Throwable cause) {
            return new KakaoClient() {
                @Override
                public KakaoBlogResultDTO getBlogResult(String query, int page, int size) {
                    log.warn("Fallback KakaoClient.getBlogResult() called with: query = [" + query + "] page = [" + page + "] size = [" + size + "]", cause);
                    NaverBlogResultDTO blogResult = naverClient.getBlogResult(query, page, size);
                    return BlogMapper.INSTANCE.naverToKakao(blogResult);
                }

                private FeignException.BadRequest getBadRequest() {
                    return cause instanceof FeignException.BadRequest ? ((FeignException.BadRequest) cause) : null;
                }
            };
        }
    }
}
