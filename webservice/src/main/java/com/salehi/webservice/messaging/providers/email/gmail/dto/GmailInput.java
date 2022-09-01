package com.salehi.webservice.messaging.providers.email.gmail.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class GmailInput {
    private List<String> to;
    private String subject;
    private String messageBody;
    private String attachment;
}
