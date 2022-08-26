package com.salehi.webservice.messaging.parsgreen.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class ParsGreenSmsInput {
    @NotBlank
    private String SmsBody;
    @Size(min = 1)
    private List<String> Mobiles;
    @NotBlank
    private String SmsNumber;
    @NotBlank
    private String serviceUrl;
    @NotBlank
    private String serviceToken;
}
