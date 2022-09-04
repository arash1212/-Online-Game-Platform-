package com.salehi.security.model.otp.dto;

import com.salehi.datasource.relational.enums.messaging.MessageTypeEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author Arash Salehi
 * @author arashsalehi849@yahoo.com
 * @date 2022-09-02 09:05
 * @since 0.0.1
 */
@Getter
@Setter
public class SecurityOtpInput {
    @NotBlank
    @Schema(example = "arashsalehi867@yahoo.com")
    private String username;
    @NotNull
    @Schema(example = "1234")
    private Integer otp;
    @NotNull
    @Schema(example = "SMS")
    private MessageTypeEnum type;
}
