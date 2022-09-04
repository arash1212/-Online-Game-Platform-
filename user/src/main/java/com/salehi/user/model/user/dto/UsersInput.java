package com.salehi.user.model.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * @author Arash Salehi
 * @author arashsalehi849@yahoo.com
 * @since 0.0.1
 */
@Getter
@Setter
public class UsersInput {
    @Email
    @NotBlank
    @Size(max = 150)
    @Schema(example = "arashsalehi867@yahoo.com")
    private String email;
    @NotBlank
    @Size(min = 11, max = 30)
    @Schema(example = "09001112222")
    private String mobile;
    @NotBlank
    @Size(min = 6, max = 100)
    @Schema(example = "123456")
    private String password;
    @NotBlank
    @Size(min = 4, max = 150)
    @Schema(example = "arash111")
    private String accountName;
}
