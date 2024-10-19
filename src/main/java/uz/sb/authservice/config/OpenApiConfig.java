package uz.sb.authservice.config;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    @Bean
    public GroupedOpenApi authApi() {
        return GroupedOpenApi.builder()
                .group("AuthController")
                .pathsToMatch("/api/auth/**")
                .build();
    }

    @Bean
    public GroupedOpenApi blockingApi() {
        return GroupedOpenApi.builder()
                .group("BlockingController")
                .pathsToMatch("/api/block/**")
                .build();
    }
}
