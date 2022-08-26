package com.salehi.webservice.messaging.interfaces.service;


import com.salehi.webservice.messaging.interfaces.dto.sms.ISmsInput;
import com.salehi.webservice.messaging.interfaces.dto.sms.ISmsOutput;

public interface IMessageService {
    String sendSms(ISmsInput input);
}
