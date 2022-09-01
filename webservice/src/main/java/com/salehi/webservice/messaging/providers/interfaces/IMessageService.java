package com.salehi.webservice.messaging.providers.interfaces;

import com.salehi.datasource.relational.entity.messaging.MessagingProviderEntity;
import com.salehi.datasource.relational.enums.messaging.MessageProviderEnum;
import com.salehi.datasource.relational.enums.messaging.MessageTypeEnum;
import com.salehi.webservice.messaging.providers.MessageInput;

/**
 * @author Arash Salehi
 * @author arashsalehi849@yahoo.com
 * @since 0.0.1
 */
public interface IMessageService<T> {
    String sendEmail(MessageInput input);

    String sendSms(MessageInput input);

    T getInput(MessageInput input);

    boolean supports(MessageTypeEnum messageType);

    MessageProviderEnum getProviderType();

    MessagingProviderEntity getCredentials();
}
