package com.citizen_authentication.service;

import com.citizen_authentication.exceptions.ResourceNotFoundException;
import com.citizen_authentication.models.dto.request.ActRequest;
import com.citizen_authentication.models.dto.response.ActResponse;
import com.citizen_authentication.models.entities.ActDemand;
import com.citizen_authentication.models.entities.User;
import com.citizen_authentication.models.repositories.ActDemandRepository;
import com.citizen_authentication.models.repositories.UserRepository;
import com.citizen_authentication.web.service.implementation.ActDemandService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ActDemandServiceTest {

    @Mock
    private ActDemandRepository actDemandRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private ActDemandService actDemandService;

    @Test
    public void testSaveActDemand_Success() {
        // Mocking
        ActRequest actRequest = new ActRequest("test@example.com", "Type", "CarteType", 1);
        User user = new User();
        user.setId(1);
        when(userRepository.findById(1)).thenReturn(Optional.of(user));
        when(actDemandRepository.save(any())).thenAnswer(invocation -> invocation.getArgument(0));

        // Test
        ActRequest savedActRequest = actDemandService.saveActDemand(actRequest);

        // Verify
        assertEquals(actRequest, savedActRequest);
    }

    @Test
    public void testGetActDemandById_Exists() {
        // Mocking
        ActDemand actDemand = new ActDemand();
        actDemand.setId(1);
        when(actDemandRepository.findById(1)).thenReturn(Optional.of(actDemand));

        // Test
        ActResponse actResponse = actDemandService.getActDemandById(1);

        // Verify
        assertEquals(actDemand.getId(), actResponse.getId());
    }

    @Test
    public void testGetActDemandById_NotFound() {
        // Mocking
        when(actDemandRepository.findById(1)).thenReturn(Optional.empty());

        // Test and Verify
        ResourceNotFoundException exception = org.junit.jupiter.api.Assertions.assertThrows(ResourceNotFoundException.class, () -> actDemandService.getActDemandById(1));
        assertEquals("ActDemand 1", exception.getMessage());
    }

    // Add more test cases for other scenarios as needed
}
