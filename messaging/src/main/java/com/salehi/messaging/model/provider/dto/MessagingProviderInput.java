package com.salehi.messaging.model.provider.dto;

import com.salehi.datasource.relational.enums.messaging.MessageProviderEnum;
import com.salehi.datasource.relational.enums.messaging.MessageTypeEnum;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class MessagingProviderInput {
    @NotBlank
    private String title;
    @NotBlank
    private String description;
    @NotBlank
    private String serviceUrl;
    @NotBlank
    private String tokenHeaderName;
    @NotBlank
    private String serviceToken;
    @NotNull
    private MessageTypeEnum supportedType;
    @NotNull
    private MessageProviderEnum provider;
}
