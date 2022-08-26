package com.salehi.messaging;

import com.salehi.webservice.messaging.interfaces.dto.sms.ISmsInput;

public interface IMessageProviderManager {
    public String sendSms(ISmsInput input);
}
