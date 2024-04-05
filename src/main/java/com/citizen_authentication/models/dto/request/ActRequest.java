package com.citizen_authentication.models.dto.request;

import com.citizen_authentication.models.enums.CartType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ActRequest {
    private String email;
    @Enumerated(EnumType.STRING)
    private CartType type;
    private String carteTypeInput;
    private Integer user_id;

}
