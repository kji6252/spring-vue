package io.github.kji6252.springvue.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.HashMap;
import java.util.Map;

@Data
@ConfigurationProperties("application")
public class ApplicationProperties {

    private Map<String, String> caches = new HashMap<>();
}
