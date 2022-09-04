package com.salehi.security.model.authority.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * @author Arash Salehi
 * @author arashsalehi849@yahoo.com
 * @since 0.0.1
 */
@Getter
@Setter
public class SecurityAuthorityInput {
    @NotBlank
    @Size(max = 100)
    @Schema(example = "Admin")
    private String authority;
}
