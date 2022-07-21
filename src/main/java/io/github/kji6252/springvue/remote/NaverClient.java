package io.github.kji6252.springvue.remote;

import io.github.kji6252.springvue.service.dto.NaverBlogResultDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "naver", url = "https://openapi.naver.com", fallbackFactory = NaverClient.NaverClientFallbackFactory.class)
public interface NaverClient {

    @GetMapping(path="/v1/search/blog")
    NaverBlogResultDTO getBlogResult(@RequestParam String query);

    @Slf4j
    @Component
    class NaverClientFallbackFactory implements FallbackFactory<NaverClient> {

        @Override
        public NaverClient create(Throwable cause) {
            return new NaverClient() {
                @Override
                public NaverBlogResultDTO getBlogResult(String query) {
                    log.warn("Fallback NaverClient.getBlogResult() called with: query = [" + query + "]", cause);
                    return null;
                }
            };
        }
    }
}
