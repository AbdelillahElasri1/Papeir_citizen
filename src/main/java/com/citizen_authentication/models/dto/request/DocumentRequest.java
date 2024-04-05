package com.citizen_authentication.models.dto.request;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class DocumentRequest {
    private String title;
    private String email;
    private String message;
    private String content;
    private Integer user_id;
}
