package com.salehi.messaging.moduleSpecific.providerManager;

import com.salehi.datasource.relational.entity.messaging.MessagingProviderEntity;
import com.salehi.datasource.relational.enums.messaging.MessageProviderEnum;
import com.salehi.datasource.relational.enums.messaging.MessageTypeEnum;
import com.salehi.messaging.model.provider.repository.MessagingProviderRepository;
import com.salehi.messaging.moduleSpecific.dto.sms.MessagingSmsInput;
import com.salehi.webservice.messaging.providers.MessageInput;
import com.salehi.webservice.messaging.providers.interfaces.IMessageService;
import com.salehi.webservice.messaging.providers.sms.parsgreen.service.ParsGreenMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class MessagingProviderManager implements IMessagingProviderManager {

    private final ApplicationContext applicationContext;
    private final MessagingProviderRepository providerRepository;

    @Autowired
    public MessagingProviderManager(ApplicationContext applicationContext, MessagingProviderRepository providerRepository, ParsGreenMessageService parsGreenMessageService) {
        this.applicationContext = applicationContext;
        this.providerRepository = providerRepository;
    }

    public String sendSms(MessagingSmsInput input) {
        List<MessagingProviderEntity> providerEntities = this.providerRepository.getActiveSmsProviders();
        MessagingProviderEntity providerEntity = providerEntities.stream().filter(MessagingProviderEntity::isActive).findFirst().orElse(null);
        if (providerEntity == null)
            return "No Active Sms Providers Found";

        IMessageService<?> messageService = this.chooseMessageService(providerEntity.getProvider(), MessageTypeEnum.SMS);
        if (messageService != null)
            return messageService.sendSms(this.getMessageServiceInput(input, providerEntity));

        return "No MessageService Found For Selected Provider";
    }

    private IMessageService<?> chooseMessageService(MessageProviderEnum providerType, MessageTypeEnum type) {
        Map<String, IMessageService> messageServices = this.getMessageServices();
        for (IMessageService messageService : messageServices.values()) {
            if (messageService.getProviderType().equals(providerType) && messageService.supports(type)) {
                return messageService;
            }
        }
        return null;
    }

    private Map<String, IMessageService> getMessageServices() {
        return applicationContext.getBeansOfType(IMessageService.class);
    }

    private MessageInput getMessageServiceInput(MessagingSmsInput input, MessagingProviderEntity providerEntity) {
        MessageInput messageInput = new MessageInput();
        messageInput.setTo(input.getTo());
        messageInput.setMessageBody(input.getMessageBody());
        messageInput.setServiceURL(providerEntity.getServiceUrl());
        messageInput.setHeaderName(providerEntity.getTokenHeaderName());
        messageInput.setServiceToken(providerEntity.getServiceToken());
        return messageInput;
    }
}
