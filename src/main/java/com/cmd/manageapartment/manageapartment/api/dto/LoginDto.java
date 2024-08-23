package com.cmd.manageapartment.manageapartment.api.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class LoginDto {
    private String username;
    private String password;
}
