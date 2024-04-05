package com.citizen_authentication.models.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class fileUpload {
    @Id
    private Integer id;

    private String name;
    private String type;
    @Lob
    @Column(name = "fileData",length = 1000)
    private byte[] fileData;
}
