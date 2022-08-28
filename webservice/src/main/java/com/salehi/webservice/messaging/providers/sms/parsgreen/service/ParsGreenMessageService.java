package com.salehi.webservice.messaging.providers.sms.parsgreen.service;

import com.salehi.webservice.messaging.providers.sms.parsgreen.dto.ParsGreenSmsInput;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ParsGreenMessageService {
    //header : Authorization , token prefix : basic apikey:
    public String sendSms(ParsGreenSmsInput input) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set(input.getTokenHeaderName(), "basic apikey:" + input.getServiceToken());
        HttpEntity<ParsGreenSmsInput> request = new HttpEntity<>(input, headers);
        ResponseEntity<String> response = restTemplate.exchange(input.getServiceUrl(), HttpMethod.POST, request, String.class);
        return response.getBody();
    }
}
