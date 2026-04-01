package com.league.clients.web;

import io.swagger.v3.oas.models.servers.Server;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import java.util.List;

@Configuration
public class OpenAPIConfiguration {

    private final Environment environment;

    public OpenAPIConfiguration(Environment environment) {
        this.environment = environment;
    }

    @Bean
    public OpenAPI defineOpenAPI() {
        Server server = new Server();
        String serverUrl = environment.getProperty("api.server.url");
        server.setUrl(serverUrl);
        server.setDescription("Development");

        Contact contact = new Contact();
        contact.setName("Viktor Grakov");
        contact.setEmail("v1k7or@bk.ru");

        Info info = new Info();
        info.title("Серверная часть для управления клиентами")
                .version("1.0")
                .description("Это API предоставляет эндпоинты для управления клиентами.")
                .contact(contact);
        return new OpenAPI().info(info).servers(List.of(server));
    }
}