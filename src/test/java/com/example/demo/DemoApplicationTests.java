package com.example.demo;

import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

@SpringBootTest
class DemoApplicationTests {

	@Mock
	private TransactionRepository transactionRepository;

	@InjectMocks
	private TransactionService transactionService;

	@Test
	void testFindTransactionsById() {
		Transaction transaction1 = new Transaction("1", "2", "Reception", LocalDateTime.now());
		Transaction transaction2 = new Transaction("2", "3", "Duplicate", LocalDateTime.now());

		when(transactionRepository.findByPrimaryIdOrSecondaryId("8MHFNAuOGS", "8MHFNAuOGS"))
				.thenReturn(Arrays.asList(transaction1, transaction2));

		List<Transaction> transactions = transactionService.findTransactionsById("8MHFNAuOGS");

		assertEquals(2, transactions.size());
		assertEquals("Reception", transactions.get(0).getEventType());
	}

}
