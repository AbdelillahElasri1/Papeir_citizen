package com.citizen_authentication.web.service.implementation;

import com.citizen_authentication.exceptions.ResourceNotFoundException;
import com.citizen_authentication.models.dto.request.ActRequest;
import com.citizen_authentication.models.dto.response.ActResponse;
import com.citizen_authentication.models.entities.ActDemand;
import com.citizen_authentication.models.entities.User;
import com.citizen_authentication.models.repositories.ActDemandRepository;
import com.citizen_authentication.models.repositories.UserRepository;
import com.citizen_authentication.web.service.IActDemandService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ActDemandService implements IActDemandService {
    private final ActDemandRepository actDemandRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    @Override
    public ActRequest saveActDemand(ActRequest actRequest) {
        ActDemand actDemand = modelMapper.map(actRequest, ActDemand.class);
        actDemand.setEmail(actRequest.getEmail());
        actDemand.setType(actRequest.getType());
        actDemand.setCarteTypeInput(actRequest.getCarteTypeInput());
        User user = userRepository.findById(actRequest.getUser_id()).orElseThrow(()-> new ResourceNotFoundException("user id : " + actRequest.getUser_id()));
        actDemand.setUser(user);
        ActDemand savedActDemand = actDemandRepository.save(actDemand);
        return modelMapper.map(savedActDemand, ActRequest.class);
    }

    @Override
    public ActResponse getActDemandById(Integer id) {
        Optional<ActDemand> optionalActDemand = actDemandRepository.findById(id);
        return optionalActDemand.map(actDemand -> modelMapper.map(actDemand, ActResponse.class)).orElseThrow(()-> new ResourceNotFoundException("ActDemand " + id));
    }

    @Override
    public List<ActResponse> getAllActDemands() {
        List<ActDemand> actDemandList = actDemandRepository.findAll();
        return actDemandList.stream().map(actDemand -> modelMapper.map(actDemand, ActResponse.class)).collect(Collectors.toList());
    }
}
