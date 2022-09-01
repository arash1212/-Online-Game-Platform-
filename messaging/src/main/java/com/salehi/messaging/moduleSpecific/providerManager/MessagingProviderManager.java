package com.salehi.messaging.moduleSpecific.providerManager;

import com.salehi.datasource.relational.enums.messaging.MessageTypeEnum;
import com.salehi.messaging.moduleSpecific.dto.email.MessagingEmailInput;
import com.salehi.messaging.moduleSpecific.dto.sms.MessagingSmsInput;
import com.salehi.webservice.messaging.providers.MessageInput;
import com.salehi.webservice.messaging.providers.interfaces.IMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author Arash Salehi
 * @author arashsalehi849@yahoo.com
 * @since 0.0.1
 */
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
            return messageService.sendSms(this.getMessageServiceSmsInput(input));

        return "No Sms MessageService Available";
    }

    public String sendEmail(MessagingEmailInput input) {
        IMessageService<?> messageService = this.chooseMessageService(MessageTypeEnum.EMAIL);
        if (messageService != null)
            return messageService.sendEmail(this.getMessageServiceEmailInput(input));

        return "No Email MessageService Available";
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

    private MessageInput getMessageServiceSmsInput(MessagingSmsInput input) {
        MessageInput messageInput = new MessageInput();
        messageInput.setTo(input.getTo());
        messageInput.setMessageBody(input.getMessageBody());
        return messageInput;
    }

    private MessageInput getMessageServiceEmailInput(MessagingEmailInput input) {
        MessageInput messageInput = new MessageInput();
        messageInput.setSubject(input.getSubject());
        messageInput.setTo(input.getTo());
        messageInput.setMessageBody(input.getMessageBody());
        return messageInput;
    }
}
