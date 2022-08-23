package com.salehi.application.config;

import com.salehi.utility.constant.RestControllerConstant;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigSwagger {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(this.info())
                .components(new Components());

    }

    @Bean
    public GroupedOpenApi apiPublic() {
        return GroupedOpenApi.builder()
                .group("public")
                .pathsToMatch(RestControllerConstant.PUB + "/**")
                .build();
    }

    @Bean
    public GroupedOpenApi apiAdmin() {
        return GroupedOpenApi.builder()
                .group("admin")
                .pathsToMatch(RestControllerConstant.ADM + "/**")
                .build();
    }

    private Contact contact() {
        Contact contact = new Contact();
        contact.setEmail("arashsalehi849@yahoo.com");
        contact.setName("Arash");
        contact.setUrl("arashsalehi849@yahoo.com");
        return contact;
    }

    private Info info() {
        Info info = new Info();
        info.setDescription("Gaming Platform Api");
        info.setContact(this.contact());
        info.setTitle("Store Api Docs");
        return info;
    }

}
