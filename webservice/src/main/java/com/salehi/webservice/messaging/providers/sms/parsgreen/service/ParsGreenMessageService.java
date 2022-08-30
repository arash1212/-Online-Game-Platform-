package com.salehi.webservice.messaging.providers.sms.parsgreen.service;

import com.salehi.datasource.relational.enums.messaging.MessageProviderEnum;
import com.salehi.datasource.relational.enums.messaging.MessageTypeEnum;
import com.salehi.webservice.messaging.providers.MessageInput;
import com.salehi.webservice.messaging.providers.interfaces.IMessageService;
import com.salehi.webservice.messaging.providers.sms.parsgreen.dto.ParsGreenSmsInput;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ParsGreenMessageService implements IMessageService<ParsGreenSmsInput> {

    @Override
    //header : Authorization , token prefix : basic apikey:
    public String sendSms(MessageInput input) {
        ParsGreenSmsInput smsInput = this.getInput(input);
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set(smsInput.getTokenHeaderName(), "basic apikey:" + input.getServiceToken());
        HttpEntity<ParsGreenSmsInput> request = new HttpEntity<>(smsInput, headers);
        ResponseEntity<String> response = restTemplate.exchange(smsInput.getServiceUrl(), HttpMethod.POST, request, String.class);
        return response.getBody();
    }

    @Override
    public ParsGreenSmsInput getInput(MessageInput input) {
        return new ParsGreenSmsInput(input.getMessageBody(), input.getTo(), null, input.getServiceURL()
                , input.getServiceToken(), input.getHeaderName());
    }

    @Override
    public boolean supports(MessageTypeEnum messageType) {
        return MessageTypeEnum.SMS.equals(messageType);
    }

    @Override
    public MessageProviderEnum getProviderType() {
        return MessageProviderEnum.PARS_GREEN;
    }

}
