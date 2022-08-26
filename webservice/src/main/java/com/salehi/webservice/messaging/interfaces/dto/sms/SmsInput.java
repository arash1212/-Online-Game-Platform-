package com.salehi.webservice.messaging.interfaces.dto.sms;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Getter
@Setter
public class SmsInput implements ISmsInput {
//    @NotBlank
//    private String from;
    @NotBlank
    private List<String> to;
    @NotBlank
    private String messageBody;

    //TODO hard code beshe ya az database begire
    @NotBlank
    private String serviceToken;
}
