package com.salehi.webservice.messaging.providers;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * @author Arash Salehi
 * @author arashsalehi849@yahoo.com
 * @since 0.0.1
 */
@Getter
@Setter
public class MessageInput {
    @Schema(description = "Subject Of The Message", example = "تایید حساب کاربری")
    private String subject;
    @NotBlank
    @Schema(description = "Intended User Mobile/Email",example = "09001234321")
    private List<String> to;
    @Schema(description = "Message File/attachments")
    private String attachment;
    @NotBlank
    @Schema(description = "Message Body",example = "Test Message")
    private String messageBody;
}
