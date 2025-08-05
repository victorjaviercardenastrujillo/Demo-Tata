package com.tata.demo.infrastructure.controllers;

import com.tata.demo.application.utils.BasicResponse;
import com.tata.demo.core.model.TransactionModel;
import com.tata.demo.core.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(value = "api/movimientos",
        produces = MediaType.APPLICATION_JSON_VALUE)
public class MovimientosController {

    public MovimientosController() {}

    @Autowired
    private TransactionService transactionService;
//
    @PostMapping(value = "/create")
    public ResponseEntity<?> create(@RequestBody TransactionModel transaction) {
        var response = transactionService.save(transaction);
        return ResponseEntity.status(((BasicResponse) response).getCode()).body(response);
//        ErrorDto error = transactionService.save(transaction);

//        if (!error.getCode().equals("403")) {
//            return ResponseEntity.status(HttpStatus.CREATED).body(error);
//        } else {
//            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(error);
//        }
    }
}
