package com.salehi.messaging.moduleSpecific.providerManager;

import com.salehi.datasource.relational.enums.messaging.MessageTypeEnum;
import com.salehi.messaging.moduleSpecific.dto.sms.MessagingSmsInput;
import com.salehi.webservice.messaging.providers.MessageInput;
import com.salehi.webservice.messaging.providers.interfaces.IMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class MessagingProviderManager implements IMessagingProviderManager {

    private final ApplicationContext applicationContext;

    @Autowired
    public MessagingProviderManager(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    public String sendSms(MessagingSmsInput input) {
        IMessageService<?> messageService = this.chooseMessageService(MessageTypeEnum.SMS);
        if (messageService != null)
            return messageService.sendSms(this.getMessageServiceInput(input));

        return "No MessageService Available";
    }

    private IMessageService<?> chooseMessageService(MessageTypeEnum type) {
        Map<String, IMessageService> messageServices = this.getMessageServices();
        for (IMessageService messageService : messageServices.values()) {
            if (messageService.supports(type) && messageService.getCredentials() != null) {
                return messageService;
            }
        }
        return null;
    }

    private Map<String, IMessageService> getMessageServices() {
        return applicationContext.getBeansOfType(IMessageService.class);
    }

    private MessageInput getMessageServiceInput(MessagingSmsInput input) {
        MessageInput messageInput = new MessageInput();
        messageInput.setTo(input.getTo());
        messageInput.setMessageBody(input.getMessageBody());
        return messageInput;
    }
}
