package io.github.kji6252.springvue.config.cache;

import com.github.benmanes.caffeine.cache.Caffeine;
import io.github.kji6252.springvue.config.ApplicationProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@RequiredArgsConstructor
@EnableCaching
@Configuration
public class CacheConfig {

    private final ApplicationProperties applicationProperties;

    @Bean
    public CaffeineCacheManager caffeineCacheManager() {
        CaffeineCacheManager caffeineCacheManager = new CaffeineCacheManager();
        for (Map.Entry<String, String> entry : applicationProperties.getCaches().entrySet()) {
            caffeineCacheManager.registerCustomCache(entry.getKey(), Caffeine.from(entry.getValue()).build());
        }
        return caffeineCacheManager;
    }
}
