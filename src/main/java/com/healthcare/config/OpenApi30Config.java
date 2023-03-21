package com.healthcare.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApi30Config {

    private final String moduleName;
    private final String apiVersion;

    public OpenApi30Config(
            @Value("${module-name}") String moduleName,
            @Value("${api-version}") String apiVersion) {
        this.moduleName = moduleName;
        this.apiVersion = apiVersion;
    }

    @Bean
    public OpenAPI customOpenAPI() {
        final String securitySchemeName = "bearerAuth";
        final String apiTitle = String.format("%s API", moduleName);
        return new OpenAPI()
//                .addSecurityItem(new SecurityRequirement().addList(securitySchemeName))
                .info(new Info().title(apiTitle).version(apiVersion));
    }
}
