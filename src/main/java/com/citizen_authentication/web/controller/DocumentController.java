package com.citizen_authentication.web.controller;

import com.citizen_authentication.models.dto.request.DocumentRequest;
import com.citizen_authentication.models.dto.request.DocumentRequest;
import com.citizen_authentication.models.dto.response.DocumentResponse;
import com.citizen_authentication.models.dto.response.DocumentResponse;
import com.citizen_authentication.web.service.IDocumentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/documents")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class DocumentController {
    private final IDocumentService iDocumentService;

    @PostMapping("/save")
    public ResponseEntity<DocumentRequest> saveActDemand(@RequestBody DocumentRequest DocumentRequest){
        DocumentRequest savedDocument = iDocumentService.saveDocument(DocumentRequest);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(savedDocument);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<DocumentResponse>> getAll(){
        List<DocumentResponse> documentResponseList = iDocumentService.getAllDocuments();
        return ResponseEntity.ok(documentResponseList);
    }
    @GetMapping("/getOne/{id}")
    public ResponseEntity<DocumentResponse> getOne(@PathVariable("id") Integer id){
        DocumentResponse documentResponse = iDocumentService.getDocumentById(id);
        if (documentResponse != null){
            return ResponseEntity.ok(documentResponse);
        }else {
            return ResponseEntity.notFound().build();
        }
    }
}
