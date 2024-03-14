package com.citizen_authentication.models.dto.auth.request;

import lombok.Data;

import java.io.Serializable;

@Data
public class RegisterDto implements Serializable {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String userRole;
}
