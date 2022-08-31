package com.salehi.webservice.messaging.providers.interfaces;

import com.salehi.datasource.relational.entity.messaging.MessagingProviderEntity;
import com.salehi.datasource.relational.enums.messaging.MessageProviderEnum;
import com.salehi.datasource.relational.enums.messaging.MessageTypeEnum;
import com.salehi.webservice.messaging.providers.MessageInput;

public interface IMessageService<T> {
    String sendSms(MessageInput input);

    T getInput(MessageInput input);

    boolean supports(MessageTypeEnum messageType);

    MessageProviderEnum getProviderType();

    MessagingProviderEntity getCredentials();
}
