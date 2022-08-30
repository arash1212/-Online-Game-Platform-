package com.salehi.messaging.model.provider.dto;

import com.salehi.datasource.relational.enums.messaging.MessageProviderEnum;
import com.salehi.datasource.relational.enums.messaging.MessageTypeEnum;
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
    private MessageTypeEnum supportedType;
    private MessageProviderEnum provider;
}
