package com.citizen_authentication.models.dto.response;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class DocumentResponse {
    private Integer id;
    private String title;
    private String email;
    private String message;
    private String content;
    private UserResponse user;
}
