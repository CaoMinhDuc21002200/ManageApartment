package com.cmd.manageapartment.manageapartment.api.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class RegisterDto {

    private String username;
    private String password;
    private String apartmentId;

}
