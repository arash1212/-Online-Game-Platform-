package com.salehi.messaging.moduleSpecific.dto.email;

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
public class MessagingEmailInput {
    @NotBlank
    private String from;
    @NotBlank
    private List<String> to;
    @NotBlank
    private String messageBody;
    @NotBlank
    private String subject;
}
