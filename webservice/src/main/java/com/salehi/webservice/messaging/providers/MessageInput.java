package com.salehi.webservice.messaging.providers;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Getter
@Setter
public class MessageInput {
    private String subject;
    @NotBlank
    private List<String> to;
    private String attachment;
    @NotBlank
    private String messageBody;
}
