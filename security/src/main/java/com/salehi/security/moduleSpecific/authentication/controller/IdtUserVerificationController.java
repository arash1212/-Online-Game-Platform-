package com.salehi.security.moduleSpecific.authentication.controller;

import com.salehi.security.model.otp.dto.SecurityOtpInput;
import com.salehi.security.moduleSpecific.authentication.dto.userVerification.SecurityUserVerificationInput;
import com.salehi.security.moduleSpecific.authentication.service.SecurityUserVerificationService;
import com.salehi.utility.constant.RestControllerConstant;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author Arash Salehi
 * @author arashsalehi849@yahoo.com
 * @date 2022-09-02 10:47
 * @since 0.0.1
 */
@Tag(name = "Idt-Security-User-Verification")
@Validated
@RestController
@RequestMapping(path = RestControllerConstant.IDT + "/user/verification")
public class IdtUserVerificationController {

    @Autowired
    private SecurityUserVerificationService securityUserVerificationService;

    @Operation(summary = "Request Verification Code")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Resource Created Successfully"),
            @ApiResponse(responseCode = "403", description = "Access Denied", content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "404", description = "Resource not found", content = @Content(schema = @Schema()))
    })
    @PostMapping(path = "/otp/send")
    public void requestOtp(@Valid @RequestBody SecurityOtpInput input) {
        this.securityUserVerificationService.sendOtp(input);
    }

    @Operation(summary = "Verify User Mobile/Email")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Resource Created Successfully"),
            @ApiResponse(responseCode = "403", description = "Access Denied", content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "404", description = "Wrong Otp input", content = @Content(schema = @Schema()))
    })
    @PostMapping(path = "/verify")
    public ResponseEntity<Boolean> verifyMobile(@Valid @RequestBody SecurityUserVerificationInput input) {
        return ResponseEntity.ok(this.securityUserVerificationService.verifyMobile(input));
    }
}

