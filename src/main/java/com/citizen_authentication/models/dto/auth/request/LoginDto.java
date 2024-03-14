package com.citizen_authentication.models.dto.auth.request;

import lombok.Data;

@Data
public class LoginDto {
    private String email;
    private String password;
}
