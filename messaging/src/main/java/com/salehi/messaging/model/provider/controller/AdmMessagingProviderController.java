package com.salehi.messaging.model.provider.controller;

import com.salehi.messaging.model.provider.dto.MessagingProviderInput;
import com.salehi.messaging.model.provider.dto.MessagingProviderOutput;
import com.salehi.messaging.model.provider.service.MessagingProviderService;
import com.salehi.utility.constant.PathVariableConstant;
import com.salehi.utility.constant.RestControllerConstant;
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
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Tag(name = "Adm-Messaging-Provider")
@Validated
@RestController
@RequestMapping(path = RestControllerConstant.ADM + "/messaging/provider")
public class AdmMessagingProviderController {

    @Autowired
    private MessagingProviderService providerService;

    @Operation(summary = "Get provider by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Resource Found"),
            @ApiResponse(responseCode = "403", description = "Access Denied", content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "404", description = "Resource not found", content = @Content(schema = @Schema()))})
    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MessagingProviderOutput> provider(@PathVariable(name = PathVariableConstant.ID) Long id) {
        return ResponseEntity.ok(this.providerService.findById(id));
    }

    @Operation(summary = "Get providers list")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully Loaded Resources"),
            @ApiResponse(responseCode = "403", description = "Access Denied", content = @Content(schema = @Schema()))
    })
    @GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<MessagingProviderOutput>> providers() {
        return ResponseEntity.ok(this.providerService.findAll());
    }

    @Operation(summary = "Create new provider")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Resource Created Successfully"),
            @ApiResponse(responseCode = "403", description = "Access Denied", content = @Content(schema = @Schema())),
    })
    @PostMapping(path = "", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Long> create(
            @Valid @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(schema = @Schema(example =
                            "{\n" +
                                    "\"title\": \"سرویس پیامک پارس گرین\",\n" +
                                    "  \"description\": \"سرویس پیامک پارس گرین\",\n" +
                                    "  \"serviceUrl\": \"https://sms.parsgreen.ir/Apiv2/Message/SendSms\",\n" +
                                    "  \"port\": null,\n" +
                                    "  \"tokenHeaderName\": \"Authorization\",\n" +
                                    "  \"serviceToken\": \"token..\",\n" +
                                    "  \"username\": \"null\",\n" +
                                    "  \"password\": \"null\",\n" +
                                    "  \"supportedType\": \"SMS\",\n" +
                                    "  \"provider\": \"PARS_GREEN\"" +
                                    "}"
                    ))
            )
            @RequestBody MessagingProviderInput input, BindingResult result) {
        return ResponseEntity.ok(this.providerService.save(input));
    }

    @Operation(summary = "Update provider by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Resource updated successfully"),
            @ApiResponse(responseCode = "403", description = "Access Denied", content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "404", description = "Resource not found", content = @Content(schema = @Schema()))})
    @PutMapping(path = "{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@PathVariable(name = PathVariableConstant.ID) Long id,
                       @Valid @io.swagger.v3.oas.annotations.parameters.RequestBody(
                               content = @Content(schema = @Schema(example =
                                       "{\n" +
                                               "\"title\": \"سرویس جیمیل\",\n" +
                                               "  \"description\": \"سرویس جیمیل\",\n" +
                                               "  \"serviceUrl\": \"smtp.gmail.com\",\n" +
                                               "  \"port\": 587,\n" +
                                               "  \"tokenHeaderName\": \"null\",\n" +
                                               "  \"serviceToken\": \"null\",\n" +
                                               "  \"username\": \"username..\",\n" +
                                               "  \"password\": \"pass..\",\n" +
                                               "  \"supportedType\": \"EMAIL\",\n" +
                                               "  \"provider\": \"GMAIL\"" +
                                               "}"
                               ))
                       )
                       @RequestBody MessagingProviderInput input, BindingResult result) {
        this.providerService.update(id, input);
    }

    @Operation(summary = "Delete Provider by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Resource deleted successfully"),
            @ApiResponse(responseCode = "403", description = "Access Denied", content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "404", description = "Resource not found", content = @Content(schema = @Schema()))})
    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable(name = PathVariableConstant.ID) Long id) {
        this.providerService.delete(id);
    }

}
