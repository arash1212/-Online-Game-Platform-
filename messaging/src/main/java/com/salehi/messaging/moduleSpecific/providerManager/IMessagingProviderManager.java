package com.salehi.messaging.moduleSpecific.providerManager;

import com.salehi.messaging.moduleSpecific.dto.sms.MessagingSmsInput;

public interface IMessagingProviderManager {
    String sendSms(MessagingSmsInput input);
}
