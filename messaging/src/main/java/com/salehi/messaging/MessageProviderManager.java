package com.salehi.messaging;

import com.salehi.datasource.relational.entity.messaging.MessagingProviderEntity;
import com.salehi.datasource.relational.enums.messaging.MessageProviderType;
import com.salehi.messaging.model.provider.repository.MessagingProviderRepository;
import com.salehi.webservice.messaging.interfaces.dto.sms.ISmsInput;
import com.salehi.webservice.messaging.parsgreen.service.ParsGreenMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MessageProviderManager implements IMessageProviderManager {

    private final MessagingProviderRepository providerRepository;
    private final ParsGreenMessageService parsGreenMessageService;

    @Autowired
    public MessageProviderManager(MessagingProviderRepository providerRepository, ParsGreenMessageService parsGreenMessageService) {
        this.providerRepository = providerRepository;
        this.parsGreenMessageService = parsGreenMessageService;
    }

    public String sendSms(ISmsInput input) {
        List<MessagingProviderEntity> providerEntities = this.providerRepository.getActiveSmsProviders();
        MessagingProviderEntity providerEntity = providerEntities.stream().filter(MessagingProviderEntity::isActive).findFirst().orElse(null);
        if (providerEntity == null)
            return "No Active Sms Provider Found";

        if (providerEntity.getProviderType() == MessageProviderType.PARS_GREEN) {
            input.setServiceToken(providerEntity.getServiceToken());
            return parsGreenMessageService.sendSms(input);
        }
        return "No Active Sms Provider Found";
    }
}
