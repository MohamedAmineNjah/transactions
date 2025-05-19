package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @CrossOrigin(maxAge = 3600)
    @GetMapping("/{id}")
    public List<Transaction> getTransactions(@PathVariable String id) {
        return transactionService.findTransactionsById(id);
    }


}
