package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;


@Service
public class TransactionService {
    private final TransactionRepository transactionRepository;

    TransactionService(TransactionRepository transactionRepository){
        this.transactionRepository=transactionRepository;
    }

    // Retrieve and reconcile transactions by primary or secondary ID
    public List<Transaction> findTransactionsById(String id) {
        List<Transaction> transactions = transactionRepository.findByPrimaryIdOrSecondaryId(id, id);
        transactions.forEach(this::fixDateFormat);  // Handle malformed dates
        transactions.sort(this::compareTransactions); // sort transactions
        return transactions;
    }

    // Corrects malformed dates and returns a LocalDateTime
    private void fixDateFormat(Transaction transaction) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
            transaction.setEventDate(LocalDateTime.parse(transaction.getEventDate().toString(), formatter));
        } catch (DateTimeParseException e) {
            // Handle the exception or set a default date
            transaction.setEventDate(LocalDateTime.now()); // or any date value
        }
    }

    // Compares two transactions based on eventRank
    private int compareTransactions(Transaction t1, Transaction t2) {
        int rank1 = getEventRank(t1.getEventType());
        int rank2 = getEventRank(t2.getEventType());
        return Integer.compare(rank1, rank2);
    }

    // Mock method to get event rank based on event type (use real logic as required)
    private int getEventRank(String eventType) {
        switch (eventType) {
            case "Reception": return 1;
            case "Duplicate": return 2;
            case "DuplicateVerification": return 3;
            // Add the rest mapping from referentiel.md context
            default: return Integer.MAX_VALUE;
        }
    }
}
