package com.salehi.messaging.moduleSpecific.controller;

import com.salehi.messaging.moduleSpecific.dto.email.MessagingEmailInput;
import com.salehi.messaging.moduleSpecific.dto.sms.MessagingSmsInput;
import com.salehi.messaging.moduleSpecific.providerManager.IMessagingProviderManager;
import com.salehi.utility.constant.RestControllerConstant;
import io.swagger.v3.oas.annotations.Operation;
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

import javax.validation.Valid;

/**
 * @author Arash Salehi
 * @author arashsalehi849@yahoo.com
 * @since 0.0.1
 */
@Tag(name = "Adm-Messaging")
@Validated
@RestController
@RequestMapping(path = RestControllerConstant.ADM + "/messaging")
public class AdmMessagingController {

    @Autowired
    private IMessagingProviderManager providerManager;

    @Operation(summary = "Send Sms")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Message Sent Successfully")
    })
    @PostMapping(path = "/send-sms", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> sendSms(@Valid @RequestBody MessagingSmsInput input, BindingResult bindingResult) {
        return ResponseEntity.ok(this.providerManager.sendSms(input));
    }

    @Operation(summary = "Send Email")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Message Sent Successfully")
    })
    @PostMapping(path = "/send-email", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> sendEmail(@Valid @RequestBody MessagingEmailInput input, BindingResult bindingResult) {
        return ResponseEntity.ok(this.providerManager.sendEmail(input));
    }
}
