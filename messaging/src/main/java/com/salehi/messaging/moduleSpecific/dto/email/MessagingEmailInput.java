package com.salehi.messaging.moduleSpecific.dto.email;

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
public class MessagingEmailInput {
    //    @NotBlank
//    @Schema(example = "test849@yahoo.com")
//    private String from;
    @Size(min = 1)
    @Schema(example = " [\"test849@yahoo.com\",\"test321@yahoo.com\"]")
    private List<String> to;
    @NotBlank
    @Schema(example = "Example Message Body")
    private String messageBody;
    @NotBlank
    @Schema(example = "Test Subject")
    private String subject;
}
