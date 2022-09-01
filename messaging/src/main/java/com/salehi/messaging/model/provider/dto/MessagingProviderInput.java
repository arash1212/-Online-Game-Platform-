package com.salehi.messaging.model.provider.dto;

import com.salehi.datasource.relational.enums.messaging.MessageProviderEnum;
import com.salehi.datasource.relational.enums.messaging.MessageTypeEnum;
import lombok.Getter;
import lombok.Setter;

import javax.validation.ValidationException;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class MessagingProviderInput {
    @NotBlank
    private String title;
    @NotBlank
    private String description;
    @NotBlank
    private String serviceUrl;
    private int port;
    @Size(max = 100)
    private String tokenHeaderName;
    @Size(max = 500)
    private String serviceToken;
    @Size(max = 200)
    private String username;
    @Size(max = 500)
    private String password;
    @NotNull
    private MessageTypeEnum supportedType;
    @NotNull
    private MessageProviderEnum provider;

    public void validate() {
        if (this.serviceToken != null && this.tokenHeaderName == null || this.tokenHeaderName != null && this.serviceToken == null)
            throw new ValidationException();
        if (this.username != null && this.password == null || this.password != null && this.username == null)
            throw new ValidationException();
        if (this.username == null && this.serviceToken == null)
            throw new ValidationException();
    }
}
