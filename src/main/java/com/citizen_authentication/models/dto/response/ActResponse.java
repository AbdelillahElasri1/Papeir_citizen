package com.citizen_authentication.models.dto.response;

import com.citizen_authentication.models.entities.User;
import com.citizen_authentication.models.enums.CartType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ActResponse {
    private Integer id;
    private String email;
    @Enumerated(EnumType.STRING)
    private CartType type;
    private String carteTypeInput;
    private UserResponse user;
}
