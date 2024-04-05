package com.citizen_authentication.web.service;

import com.citizen_authentication.models.dto.request.ActRequest;
import com.citizen_authentication.models.dto.response.ActResponse;

import java.util.List;

public interface IActDemandService {
    ActRequest saveActDemand(ActRequest actRequest);
    ActResponse getActDemandById(Integer id);
    List<ActResponse> getAllActDemands();
}
