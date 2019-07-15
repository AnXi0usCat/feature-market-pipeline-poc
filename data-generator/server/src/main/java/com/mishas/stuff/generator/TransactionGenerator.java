package com.mishas.stuff.generator;

import com.github.javafaker.Faker;
import com.mishas.stuff.persistence.TransactionRepository;

import java.math.BigDecimal;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.UUID;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;
import static org.apache.commons.lang3.RandomStringUtils.randomNumeric;

public class TransactionGenerator {

    private static final int CORE_POOL_SIZE = 10;

    private final ScheduledExecutorService executor = Executors.newScheduledThreadPool(CORE_POOL_SIZE);

    private final AccountUIDHolder accountUIDHolder = AccountUIDHolder.ACCOUNT_HOLDER;

    private final TransactionRepository transactionRepository = new TransactionRepository();

    public void streamAll() {
        IntStream.range(0, CORE_POOL_SIZE).forEach(res -> stream());
    }

    private void stream() {
        executor.scheduleAtFixedRate(() -> {
//                    System.out.println(Thread.currentThread().toString() +
//                            " Latest transaction data: " + createRandomTransaction().toString());
                    transactionRepository.save(createRandomTransaction());
                },
                1, 1, TimeUnit.MILLISECONDS);
    }

    private Transaction createRandomTransaction() {
        return new Transaction.TransactionBuilder()
                .withAccountUID(accountUIDHolder.getAccountHolder())
                .withTransactionUID(UUID.randomUUID())
                .createdAt(ZonedDateTime.now(ZoneId.systemDefault()))
                .withAmount(new BigDecimal(randomNumeric(4)))
                .inCurrency("GBP")
                .setDirection(new Faker().random().nextBoolean() ? Direction.CREDIT : Direction.DEBIT)
                .build();
    }
}
