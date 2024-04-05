package com.citizen_authentication.web.controller;

import com.citizen_authentication.models.dto.request.ActRequest;
import com.citizen_authentication.models.dto.response.ActResponse;
import com.citizen_authentication.web.service.IActDemandService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/actDemand")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ActDemandController {
    private final IActDemandService iActDemandService;

    @PostMapping("/save")
    public ResponseEntity<ActRequest> saveActDemand(@RequestBody ActRequest actRequest){
        ActRequest savedActDemand = iActDemandService.saveActDemand(actRequest);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(savedActDemand);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<ActResponse>> getAll(){
        List<ActResponse> actResponseList = iActDemandService.getAllActDemands();
        return ResponseEntity.ok(actResponseList);
    }
    @GetMapping("/getOne/{id}")
    public ResponseEntity<ActResponse> getOne(@PathVariable("id") Integer id){
        ActResponse actResponse = iActDemandService.getActDemandById(id);
        if (actResponse != null){
            return ResponseEntity.ok(actResponse);
        }else {
            return ResponseEntity.notFound().build();
        }
    }
}
