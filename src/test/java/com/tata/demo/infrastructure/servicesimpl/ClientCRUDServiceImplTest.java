package com.tata.demo.infrastructure.servicesimpl;

import com.tata.demo.infrastructure.entities.ClientEntity;
import com.tata.demo.infrastructure.repositories.ClientRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ClientCRUDServiceImplTest {

    @Mock
    private ClientRepository clRepository;

    @InjectMocks
    private ClientCRUDServiceImpl clientService;

    @Test
    void findByDNI() {

        String dni = "1714952361";

        Optional<ClientEntity> result = clRepository.findByClientDni("1714952361");

        var rst = clientService.findByDNI("1714952361");

        Optional<ClientEntity> clEntTest1 = clRepository.findById(1L);

        System.out.println("Valido");
    }
}