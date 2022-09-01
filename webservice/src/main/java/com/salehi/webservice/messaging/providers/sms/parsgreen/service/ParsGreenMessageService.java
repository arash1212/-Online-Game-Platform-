package com.salehi.webservice.messaging.providers.sms.parsgreen.service;

import com.salehi.datasource.relational.entity.messaging.MessagingProviderEntity;
import com.salehi.datasource.relational.enums.messaging.MessageProviderEnum;
import com.salehi.datasource.relational.enums.messaging.MessageTypeEnum;
import com.salehi.webservice.messaging.providers.MessageInput;
import com.salehi.webservice.messaging.providers.MessagingRepository;
import com.salehi.webservice.messaging.providers.interfaces.IMessageService;
import com.salehi.webservice.messaging.providers.sms.parsgreen.dto.ParsGreenSmsInput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @author Arash Salehi
 * @author arashsalehi849@yahoo.com
 * @since 0.0.1
 */
@Service
public class ParsGreenMessageService implements IMessageService<ParsGreenSmsInput> {

    private final MessagingRepository messagingRepository;

    @Autowired
    public ParsGreenMessageService(MessagingRepository messagingRepository) {
        this.messagingRepository = messagingRepository;
    }

    @Override
    public String sendEmail(MessageInput input) {
        throw new UnsupportedOperationException();
    }

    //header => Authorization , token prefix => basic apikey:
    @Override
    public String sendSms(MessageInput input) {
        ParsGreenSmsInput smsInput = this.getInput(input);
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set(smsInput.getTokenHeaderName(), "basic apikey:" + smsInput.getServiceToken());
        HttpEntity<ParsGreenSmsInput> request = new HttpEntity<>(smsInput, headers);
        ResponseEntity<String> response = restTemplate.exchange(smsInput.getServiceUrl(), HttpMethod.POST, request, String.class);
        return response.getBody();
    }

    @Override
    public ParsGreenSmsInput getInput(MessageInput input) {
        MessagingProviderEntity providerEntity = this.getCredentials();
        return new ParsGreenSmsInput(input.getMessageBody(), input.getTo(), null, providerEntity.getServiceUrl()
                , providerEntity.getServiceToken(), providerEntity.getTokenHeaderName());
    }

    @Override
    public boolean supports(MessageTypeEnum messageType) {
        return MessageTypeEnum.SMS.equals(messageType);
    }

    @Override
    public MessageProviderEnum getProviderType() {
        return MessageProviderEnum.PARS_GREEN;
    }

    @Override
    public MessagingProviderEntity getCredentials() {
        return this.messagingRepository.getCredentialsByProvider(MessageProviderEnum.PARS_GREEN);
    }

}
