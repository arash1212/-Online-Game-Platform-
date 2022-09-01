package com.salehi.webservice.messaging.providers.sms.parsgreen.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * @author Arash Salehi
 * @author arashsalehi849@yahoo.com
 * @since 0.0.1
 */
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
    @NotBlank
    private String tokenHeaderName;
}
