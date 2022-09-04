package com.salehi.messaging.moduleSpecific.dto.sms;

import io.swagger.v3.oas.annotations.media.Schema;
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
public class MessagingSmsInput {
    @Size(min = 1)
    @Schema(example = "[\"09001234321\",\"09103214321\"]")
    private List<String> to;
    @NotBlank
    @Schema(example = "Test Message Body")
    private String messageBody;
}
