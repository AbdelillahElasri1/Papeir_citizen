package com.citizen_authentication.models.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponse {
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private String userRole;
}
