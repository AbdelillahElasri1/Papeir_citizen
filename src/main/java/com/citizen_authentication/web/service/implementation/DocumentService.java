package com.citizen_authentication.web.service.implementation;

import com.citizen_authentication.exceptions.ResourceNotFoundException;
import com.citizen_authentication.models.dto.request.DocumentRequest;
import com.citizen_authentication.models.dto.response.ActResponse;
import com.citizen_authentication.models.dto.response.DocumentResponse;
import com.citizen_authentication.models.dto.response.UserResponse;
import com.citizen_authentication.models.entities.ActDemand;
import com.citizen_authentication.models.entities.Documents;
import com.citizen_authentication.models.entities.User;
import com.citizen_authentication.models.repositories.DocumentRepository;
import com.citizen_authentication.models.repositories.UserRepository;
import com.citizen_authentication.web.service.IDocumentService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DocumentService implements IDocumentService {
    private final DocumentRepository documentRepository;
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;


    @Override
    public DocumentRequest saveDocument(DocumentRequest documentRequest) {
        Documents documents = modelMapper.map(documentRequest, Documents.class);
        documents.setTitle(documentRequest.getTitle());
        documents.setEmail(documentRequest.getEmail());
        documents.setMessage(documentRequest.getMessage());
        documents.setContent(documentRequest.getContent());
        User user = userRepository.findById(documentRequest.getUser_id()).orElseThrow(()-> new ResourceNotFoundException("user id" + documentRequest.getUser_id()));
        documents.setUser(user);
        Documents savedDocument = documentRepository.save(documents);
        return modelMapper.map(savedDocument, DocumentRequest.class);
    }

    @Override
    public DocumentResponse getDocumentById(Integer id) {
        Optional<Documents> optionalDocuments = documentRepository.findById(id);
        return optionalDocuments.map(documents -> modelMapper.map(documents, DocumentResponse.class)).orElseThrow(()-> new ResourceNotFoundException("Document " + id));
    }

    @Override
    public List<DocumentResponse> getAllDocuments() {
        List<Documents> documentsList = documentRepository.findAll();
        return documentsList.stream().map(documents -> modelMapper.map(documents, DocumentResponse.class)).collect(Collectors.toList());
    }
}
