package com.salehi.messaging.moduleSpecific.providerManager;

import com.salehi.datasource.relational.entity.messaging.MessagingProviderEntity;
import com.salehi.datasource.relational.enums.messaging.MessageProviderType;
import com.salehi.messaging.model.provider.repository.MessagingProviderRepository;
import com.salehi.messaging.moduleSpecific.converter.MessagingMapperService;
import com.salehi.messaging.moduleSpecific.converter.MessagingMapperInput;
import com.salehi.messaging.moduleSpecific.dto.sms.MessagingSmsInput;
import com.salehi.webservice.messaging.providers.sms.parsgreen.service.ParsGreenMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MessagingProviderManager implements IMessagingProviderManager {

    private final MessagingProviderRepository providerRepository;
    private final ParsGreenMessageService parsGreenMessageService;
    private final MessagingMapperService messagingMapperService;

    @Autowired
    public MessagingProviderManager(MessagingProviderRepository providerRepository, ParsGreenMessageService parsGreenMessageService, MessagingMapperService messagingMapperService) {
        this.providerRepository = providerRepository;
        this.parsGreenMessageService = parsGreenMessageService;
        this.messagingMapperService = messagingMapperService;
    }

    public String sendSms(MessagingSmsInput input) {
        List<MessagingProviderEntity> providerEntities = this.providerRepository.getActiveSmsProviders();
        MessagingProviderEntity providerEntity = providerEntities.stream().filter(MessagingProviderEntity::isActive).findFirst().orElse(null);
        if (providerEntity == null)
            return "No Active Sms Provider Found";

        if (providerEntity.getProviderType() == MessageProviderType.PARS_GREEN) {
            return parsGreenMessageService.sendSms(this.messagingMapperService.mapToParsGreenSmsInput(this.getConverterInput(input, providerEntity)));
        }
        return "No Active Sms Provider Found";
    }

    private MessagingMapperInput getConverterInput(MessagingSmsInput input, MessagingProviderEntity providerEntity) {
        MessagingMapperInput converterInput = new MessagingMapperInput();
        converterInput.setTo(input.getTo());
        converterInput.setMessageBody(input.getMessageBody());
        converterInput.setServiceURL(providerEntity.getServiceUrl());
        converterInput.setHeaderName(providerEntity.getTokenHeaderName());
        converterInput.setServiceToken(providerEntity.getServiceToken());
        return converterInput;
    }
}
