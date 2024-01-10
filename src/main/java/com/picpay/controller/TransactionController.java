package com.picpay.controller;

import com.picpay.domain.transaction.Transaction;
import com.picpay.dto.TransactionDTO;
import com.picpay.service.NotificationService;
import com.picpay.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("transaction")
public class TransactionController {

    @Autowired
    TransactionService service;

    @GetMapping
    public List<Transaction> getUser() {
        System.out.println("All Transactions");
        return service.get();
    }

    @GetMapping( "{id}" )
    public ResponseEntity<Transaction> getById(@PathVariable Long id) {
        System.out.println("Transaction with id: " + id);

        return ResponseEntity.ok(service.getById(id));
    }

    @PostMapping
    public ResponseEntity<TransactionDTO> post(@RequestBody TransactionDTO transaction) throws Exception {
        System.out.println("Carrying out transaction");
        service.createTransaction(transaction);

        return ResponseEntity.ok().body(transaction);
    }
}
