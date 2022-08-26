package com.salehi.webservice.messaging.parsgreen.dto;

import com.salehi.webservice.messaging.interfaces.dto.sms.ISmsInput;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class ParsGreenSmsInput implements ISmsInput {
//    @NotBlank
//    private String SmsBody;
//    @Size(min = 1)
//    private List<String> Mobiles;
//    @NotBlank
//    private String SmsNumber;
//    @NotBlank
//    private String serviceUrl;
//    @NotBlank
//    private String serviceToken;

    @NotBlank
    private String from;
    @NotBlank
    private String to;
    @NotBlank
    private String messageBody;
    @NotBlank
    private String token;
    @NotBlank
    private String serviceUrl;

}
