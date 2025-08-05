package com.tata.demo.infrastructure.controllers;

import com.tata.demo.application.dto.PersonClientDto;
import com.tata.demo.application.utils.BasicResponse;
import com.tata.demo.application.utils.ObjectsResponse;
import com.tata.demo.core.model.PersonClientModel;
import com.tata.demo.core.services.ClientCRUDService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(value = "api/clientes",
        produces = MediaType.APPLICATION_JSON_VALUE)
public class ClientesController {

    @Autowired
    private ClientCRUDService clientCRUDService;

    @GetMapping(value = "/buscarDNI/{dni}")
    public ResponseEntity<?> buscarDNI(@PathVariable String dni) {

        var response = clientCRUDService.findByDNI(dni);

        if(response instanceof BasicResponse) {
            return ResponseEntity.status(((BasicResponse) response).getCode()).body(response);
        }
        return ResponseEntity.status(((ObjectsResponse) response).getCode()).body(response);

    }

    @PostMapping(value = "/create")
    public ResponseEntity<?> create(@RequestBody PersonClientDto client) {

        var response = clientCRUDService.save(client);

        if(response instanceof BasicResponse) {
            return ResponseEntity.status(((BasicResponse) response).getCode()).body(response);
        }
        return ResponseEntity.status(((ObjectsResponse) response).getCode()).body(response);

    }

    @PutMapping(value = "/update")
    public ResponseEntity<?> update(@RequestBody PersonClientModel client) {

        var response = clientCRUDService.update(client);

        if(response instanceof BasicResponse) {
            return ResponseEntity.status(((BasicResponse) response).getCode()).body(response);
        }
        return ResponseEntity.status(((ObjectsResponse) response).getCode()).body(response);


    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {

        var response = clientCRUDService.deleteById(id);

        if(response instanceof BasicResponse) {
            return ResponseEntity.status(((BasicResponse) response).getCode()).body(response);
        }
        return ResponseEntity.status(((ObjectsResponse) response).getCode()).body(response);


    }
}
