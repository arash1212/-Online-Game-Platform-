package com.salehi.webservice.messaging.interfaces.dto.sms;

public interface ISmsInput {

//    void setServiceUrl(String url);
//
//    String getServiceUrl();
//
//    void setMobiles(List<String> mobiles);
//
//    List<String> getMobiles();
//
//    void setSendNumber(String sendNumber);
//
//    String getSendNumber();
//
//    void setMessageBody(String messageBody);
//
//    String getMessageBody();

    void setServiceUrl(String url);

    String getServiceUrl();

    void setFrom(String from);

    String getFrom();

    void setTo(String mobile);

    String getTo();

    String getToken();

    void setToken(String token);

    void setMessageBody(String messageBody);

    String getMessageBody();
}
