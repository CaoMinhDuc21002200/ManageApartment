package com.cmd.manageapartment.manageapartment.api.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class AuthResponseDto {
    private String tokenType = "Bearer ";
    private String accessToken;

    public AuthResponseDto(String accessToken) {
        this.accessToken = accessToken;
    }
}
