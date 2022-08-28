package com.salehi.messaging.moduleSpecific.converter;

import com.salehi.webservice.messaging.providers.sms.parsgreen.dto.ParsGreenSmsInput;
import org.springframework.stereotype.Component;

@Component
public class MessagingMapperService {
    public ParsGreenSmsInput mapToParsGreenSmsInput(MessagingMapperInput input) {
        return new ParsGreenSmsInput(input.getMessageBody(), input.getTo(), null, input.getServiceURL()
                , input.getServiceToken(), input.getHeaderName());
    }
}
