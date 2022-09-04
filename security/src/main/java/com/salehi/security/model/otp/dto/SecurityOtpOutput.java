package com.salehi.security.model.otp.dto;

import com.salehi.datasource.relational.enums.messaging.MessageTypeEnum;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Arash Salehi
 * @author arashsalehi849@yahoo.com
 * @date 2022-09-02 09:05
 * @since 0.0.1
 */
@Getter
@Setter
public class SecurityOtpOutput {
    private String id;
    private String username;
    private Integer otp;
    private MessageTypeEnum type;
}
