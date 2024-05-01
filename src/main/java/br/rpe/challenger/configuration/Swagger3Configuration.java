package br.rpe.challenger.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
@Configuration
class Swagger3Configuration {

    @Bean
    public OpenAPI configOpenApi() {
        Info info = new Info()
                .title("Car Management API")
                .version("1.0")
                .description("This API exposes endpoints to manage cars");

        return new OpenAPI().info(info);
    }

    @Bean
    public GroupedOpenApi configGroupedOpenApi() {
        return GroupedOpenApi.builder()
                .displayName("Car Management System")
                .group("carmanagementsystem-public")
                .pathsToMatch("/api/passenger-cars/**", "/api/cargo-cars/**")
                .build();
    }
}