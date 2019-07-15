package com.mishas.stuff.generator;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.UUID;

public class Transaction {

    private UUID transactionUID;

    private UUID accountUID;

    private ZonedDateTime createdTimestamp;

    private BigDecimal amount;

    private Direction direction;

    private String currencyCode;

    private Transaction() {

    }

    public static final class TransactionBuilder{

        private UUID transactionUID;

        private UUID accountUUID;

        private ZonedDateTime createdTimestamp;

        private BigDecimal amount;

        private Direction direction;

        private String currencyCode;

        public TransactionBuilder withAccountUID(UUID accountUID) {
            this.accountUUID = accountUID;

            return this;
        }

        public TransactionBuilder withTransactionUID(UUID transactionUID) {
            this.transactionUID = transactionUID;

            return this;
        }

        public TransactionBuilder createdAt(ZonedDateTime createdTimestamp) {
            this.createdTimestamp = createdTimestamp;

            return this;
        }

        public TransactionBuilder withAmount(BigDecimal amount) {
            this.amount = amount;

            return this;
        }

        public TransactionBuilder setDirection(Direction direction) {
            this.direction = direction;

            return this;
        }

        public TransactionBuilder inCurrency(String currencyCode) {
            this.currencyCode = currencyCode;

            return this;
        }

        public Transaction build() {
            Transaction transaction = new Transaction();
            transaction.accountUID = this.accountUUID;
            transaction.transactionUID = this.transactionUID;
            transaction.amount = this.amount;
            transaction.createdTimestamp = this.createdTimestamp;
            transaction.currencyCode = this.currencyCode;
            transaction.direction = this.direction;
            return transaction;
        }
    }

    public UUID getTransactionUID() {
        return transactionUID;
    }

    public UUID getAccountUID() {
        return accountUID;
    }

    public ZonedDateTime getCreatedTimestamp() {
        return createdTimestamp;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public Direction getDirection() {
        return direction;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "transactionUID=" + transactionUID +
                ", accountUID=" + accountUID +
                ", createdTimestamp=" + createdTimestamp +
                ", amount=" + amount +
                ", direction=" + direction +
                ", currencyCode='" + currencyCode + '\'' +
                '}';
    }
}
