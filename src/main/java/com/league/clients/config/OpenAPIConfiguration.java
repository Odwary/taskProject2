package com.league.clients.config;

import io.swagger.v3.oas.models.servers.Server;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenAPIConfiguration {

    @Bean
    public OpenAPI openAPI(@Value("${api.server.url:}") String serverUrl) {
        OpenAPI openAPI = new OpenAPI()
                .info(new Info()
                        .title("Серверная часть для управления клиентами")
                        .version("1.0")
                        .description("API предоставляет эндпоинты для управления клиентами.")
                        .contact(new Contact()
                                .name("Viktor Grakov")
                                .email("v1k7or@bk.ru")));

        if (!serverUrl.isBlank()) {
            openAPI.setServers(List.of(
                    new Server()
                            .url(serverUrl)
                            .description("API server")
            ));
        }

        return openAPI;
    }
}