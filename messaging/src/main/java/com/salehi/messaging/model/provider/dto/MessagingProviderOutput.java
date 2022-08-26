package com.salehi.messaging.model.provider.dto;

import com.salehi.datasource.relational.enums.messaging.MessageProviderType;
import com.salehi.datasource.relational.enums.messaging.MessageType;
import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;

@Getter
@Setter
public class MessagingProviderOutput {
    private Long id;
    private String title;
    private Boolean active;
    private String description;
    private ZonedDateTime creationTime;
    private String serviceUrl;
    private String tokenHeaderName;
    private String serviceToken;
    private MessageType supportedType;
    private MessageProviderType providerType;
}
