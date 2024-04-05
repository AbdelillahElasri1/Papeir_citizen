package com.citizen_authentication.models.entities;

import com.citizen_authentication.models.enums.CartType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ActDemand {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String email;
    @Enumerated(EnumType.STRING)
    private CartType type;
    private String carteTypeInput;

    //relationship between actDemand and User
    @ManyToOne
    private User user;

}
