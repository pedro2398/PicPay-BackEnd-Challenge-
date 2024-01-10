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
    TransactionService serviceTransacion;

    @GetMapping
    public List<Transaction> get() {
        System.out.println("All Transactions");
        return serviceTransacion.getTransactions();
    }

    @GetMapping( "{id}" )
    public ResponseEntity<Transaction> getById(@PathVariable Long id) {
        System.out.println("Transaction with id: " + id);

        return ResponseEntity.ok(serviceTransacion.getByIdTransacion(id));
    }

    @PostMapping
    public ResponseEntity<TransactionDTO> post(@RequestBody TransactionDTO transaction) throws Exception {
        System.out.println("Carrying out transaction");
        serviceTransacion.createTransaction(transaction);

        return ResponseEntity.ok().body(transaction);
    }
}
