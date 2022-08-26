package com.salehi.webservice.messaging.parsgreen.service;

import com.salehi.webservice.messaging.interfaces.dto.sms.ISmsInput;
import com.salehi.webservice.messaging.interfaces.service.IMessageService;
import com.salehi.webservice.messaging.parsgreen.dto.ParsGreenSmsInput;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ParsGreenMessageService implements IMessageService {
    public String sendSms(ISmsInput input) {
        String serviceUrl = "https://sms.parsgreen.ir/Apiv2/Message/SendSms";
        String tokenHeaderName = "Authorization";
        String serviceToken = "basic apikey:" + input.getServiceToken();
        //
        ParsGreenSmsInput parsGreenSmsInput =
                new ParsGreenSmsInput(input.getMessageBody(), input.getTo(), null, serviceUrl, serviceToken);
        //
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set(tokenHeaderName, serviceToken);
        HttpEntity<ParsGreenSmsInput> request = new HttpEntity<>(parsGreenSmsInput, headers);
        ResponseEntity<String> response = restTemplate.exchange(serviceUrl, HttpMethod.POST, request, String.class);
        return response.getBody();
    }
}
