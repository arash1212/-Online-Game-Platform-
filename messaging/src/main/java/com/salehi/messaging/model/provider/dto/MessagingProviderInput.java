package com.salehi.messaging.model.provider.dto;

import com.salehi.datasource.relational.enums.messaging.MessageProviderEnum;
import com.salehi.datasource.relational.enums.messaging.MessageTypeEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.ValidationException;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author Arash Salehi
 * @author arashsalehi849@yahoo.com
 * @since 0.0.1
 */
@Getter
@Setter
public class MessagingProviderInput {
    @NotBlank
    @Schema(example = "پارس گرین")
    private String title;
    @NotBlank
    @Schema(example = "سرویس پیامک پارس گرین")
    private String description;
    @NotBlank
    @Schema(example = "https://sms.parsgreen.ir/Apiv2/Message/SendSms")
    private String serviceUrl;
    @Schema(nullable = true)
    private int port;
    @Size(max = 100)
    @Schema(example = "Authorization", nullable = true)
    private String tokenHeaderName;
    @Size(max = 500)
    @Schema(example = "QAf35zCeDsy", nullable = true)
    private String serviceToken;
    @Size(max = 200)
    @Schema(nullable = true)
    private String username;
    @Size(max = 500)
    @Schema(nullable = true)
    private String password;
    @NotNull
    @Schema(example = "SMS")
    private MessageTypeEnum supportedType;
    @NotNull
    @Schema(example = "PARS_GREEN")
    private MessageProviderEnum provider;

    public void validate() {
        if (this.serviceToken != null && this.tokenHeaderName == null || this.tokenHeaderName != null && this.serviceToken == null)
            throw new ValidationException();
        if (this.username != null && this.password == null || this.password != null && this.username == null)
            throw new ValidationException();
        if (this.username == null && this.serviceToken == null)
            throw new ValidationException();
    }
}
