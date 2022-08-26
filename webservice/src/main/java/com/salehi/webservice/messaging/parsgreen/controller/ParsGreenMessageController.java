package com.salehi.webservice.messaging.parsgreen.controller;

import com.salehi.utility.constant.RestControllerConstant;
import com.salehi.webservice.messaging.interfaces.dto.sms.SmsInput;
import com.salehi.webservice.messaging.interfaces.service.IMessageService;
import com.salehi.webservice.messaging.parsgreen.dto.ParsGreenSmsInput;
import com.salehi.webservice.messaging.parsgreen.service.ParsGreenMessageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Adm-Pars-Green-Messaging")
@Validated
@RestController
@RequestMapping(path = RestControllerConstant.ADM + "/messaging/pars-green")
public class ParsGreenMessageController {

    @Autowired
    private IMessageService messageService;

    @Operation(summary = "Send Sms")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Message Sent Successfully")
    })
    @PostMapping(path = "/send-sms", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> sendSms(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(
                            schema = @Schema(
                                    example = "{\n" +
                                            "  \"to\": [\n" +
                                            "    \"09101234321\"\n" +
                                            "  ],\n" +
                                            "  \"messageBody\": \"Test Message Body\",\n" +
                                            "  \"serviceToken\": \"token..\"\n" +
                                            "}"
                            )
                    )
            )
            @RequestBody SmsInput input, BindingResult bindingResult) {
        return ResponseEntity.ok(this.messageService.sendSms(input));
    }

}
