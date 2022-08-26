package com.salehi.webservice.messaging.parsgreen.service;

import com.salehi.webservice.messaging.interfaces.dto.sms.ISmsInput;
import com.salehi.webservice.messaging.interfaces.service.IMessageService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class ParsGreenMessageService implements IMessageService {

    public String sendSms(ISmsInput input) {
        RestTemplate restTemplate = new RestTemplate();
//        HttpHeaders headers = new HttpHeaders();
//        headers.set("signature", input.getServiceToken());
        Map<String, Object> params = new HashMap<>();
        params.put("from", input.getFrom());
        params.put("th", input.getTo());
        params.put("text", input.getMessageBody());
        params.put("signature", input.getToken());
        HttpEntity<ISmsInput> request = new HttpEntity<>(input);
        ResponseEntity<String> response = restTemplate.exchange(
                input.getServiceUrl() + "?ftom=" + input.getFrom() + "&to=" + input.getTo() +
                        "&text=" + input.getMessageBody() + "&signature=" + input.getToken()
                , HttpMethod.GET, request, String.class, params);
        return response.getBody();
    }
}
