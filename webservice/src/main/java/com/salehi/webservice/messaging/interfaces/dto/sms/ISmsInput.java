package com.salehi.webservice.messaging.interfaces.dto.sms;

import java.util.List;

public interface ISmsInput {
    void setTo(List<String> mobiles);

    List<String> getTo();

//    void setFrom(String from);
//
//    String getFrom();

    void setMessageBody(String messageBody);

    String getMessageBody();

    String getServiceToken();

    void setServiceToken(String token);
}
