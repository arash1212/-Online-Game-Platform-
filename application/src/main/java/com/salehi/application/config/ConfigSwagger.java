package com.salehi.application.config;

import com.salehi.utility.constant.RestControllerConstant;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Arash Salehi
 * @author arashsalehi849@yahoo.com
 * @since 0.0.1
 */
@Configuration
public class ConfigSwagger {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(this.info())
                .components(new Components()
                        .addSecuritySchemes("Bearer", new SecurityScheme()
                                .type(SecurityScheme.Type.APIKEY)
                                .in(SecurityScheme.In.HEADER)
                                .name("Bearer")
                                .bearerFormat("JWT")))
                .addSecurityItem(new SecurityRequirement().addList("Bearer"));
    }

    @Bean
    public GroupedOpenApi apiPublic() {
        return GroupedOpenApi.builder()
                .group("Public")
                .pathsToMatch(RestControllerConstant.PUB + "/**")
                .build();
    }

    @Bean
    public GroupedOpenApi apiAdmin() {
        return GroupedOpenApi.builder()
                .group("Admin")
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
