package com.salehi.webservice.messaging.parsgreen.controller;

import com.salehi.utility.constant.RestControllerConstant;
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
    private ParsGreenMessageService messageService;

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
                                            "  \"from\": \"1000001000002\",\n" +
                                            "  \"to\": \"09011233214\",\n" +
                                            "  \"messageBody\": \"Test Message\",\n" +
                                            "  \"token\": \"token...\",\n" +
                                            "  \"serviceUrl\": \"https://sms.parsgreen.ir/UrlService/sendSMS.ashx\"\n" +
                                            "}"
                            )
                    )
            )
            @RequestBody ParsGreenSmsInput input, BindingResult bindingResult) {
        return ResponseEntity.ok(this.messageService.sendSms(input));
    }

}
