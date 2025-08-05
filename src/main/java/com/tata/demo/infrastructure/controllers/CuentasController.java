package com.tata.demo.infrastructure.controllers;

import com.tata.demo.application.utils.BasicResponse;
import com.tata.demo.application.utils.ObjectsResponse;
import com.tata.demo.core.model.AccountModel;
import com.tata.demo.core.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(value = "api/cuentas",
        produces = MediaType.APPLICATION_JSON_VALUE)
public class CuentasController {

    public CuentasController() {}

    @Autowired
    private AccountService accountService;

    @PostMapping(value = "/create")
    public ResponseEntity<?> create(@RequestBody AccountModel account) {

        var response = accountService.save(account);
        
        if (response instanceof BasicResponse) {
            return ResponseEntity.status(((BasicResponse) response).getCode()).body(response);
        }

        return ResponseEntity.status(((ObjectsResponse) response).getCode()).body(response);
    }

}
