package com.citizen_authentication.web.service;

import com.citizen_authentication.models.dto.request.ActRequest;
import com.citizen_authentication.models.dto.request.DocumentRequest;
import com.citizen_authentication.models.dto.response.ActResponse;
import com.citizen_authentication.models.dto.response.DocumentResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface IDocumentService {
    DocumentRequest saveDocument(DocumentRequest documentRequest);
    DocumentResponse getDocumentById(Integer id);
    List<DocumentResponse> getAllDocuments();
}
