package com.salehi.messaging.moduleSpecific.providerManager;

import com.salehi.messaging.moduleSpecific.dto.email.MessagingEmailInput;
import com.salehi.messaging.moduleSpecific.dto.sms.MessagingSmsInput;

/**
 * @author Arash Salehi
 * @author arashsalehi849@yahoo.com
 * @since 0.0.1
 */
public interface IMessagingProviderManager {
    String sendSms(MessagingSmsInput input);

    String sendEmail(MessagingEmailInput input);
}
