package com.salehi.messaging.moduleSpecific.converter;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Getter
@Setter
public class MessagingMapperInput {
    @NotBlank
    private List<String> to;
    @NotBlank
    private String messageBody;
    @NotBlank
    private String serviceToken;
    @NotBlank
    private String serviceURL;
    @NotBlank
    private String headerName;
}
