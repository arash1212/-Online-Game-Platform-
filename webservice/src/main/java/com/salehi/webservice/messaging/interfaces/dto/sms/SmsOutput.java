package com.salehi.webservice.messaging.interfaces.dto.sms;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SmsOutput implements ISmsOutput{
    private boolean succeed;
    private int successCount;
}
