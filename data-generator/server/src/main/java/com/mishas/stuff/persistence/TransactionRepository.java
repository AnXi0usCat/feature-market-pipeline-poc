package com.mishas.stuff.persistence;

import com.mishas.stuff.generator.Transaction;

import java.sql.*;

public class TransactionRepository {

    public void save(Transaction transaction) {

        String sql = "INSERT INTO public.transaction(transaction_uid, account_uid, " +
                "created_timestamp, amount, direction, currency_code) VALUES (?, ?, ?, ?, ?, ?)";

        Connection connection = null;
        PreparedStatement pst = null;

        try {
            connection = DataSource.getConnection();
            pst = connection.prepareStatement(sql);
            pst.setObject(1, transaction.getTransactionUID());
            pst.setObject(2, transaction.getAccountUID());
            pst.setTimestamp(3, new Timestamp(transaction.getCreatedTimestamp().toInstant().getEpochSecond() * 1000L));
            pst.setBigDecimal(4, transaction.getAmount());
            pst.setString(5, transaction.getDirection().toString());
            pst.setString(6, transaction.getCurrencyCode());

            pst.executeUpdate();
            connection.commit();

        } catch (SQLException se) {
            try {
                connection.rollback();
            } catch (SQLException | NullPointerException sq1) {
                // empty
            }
        } finally {
            try {if (pst != null) { pst.close(); } } catch (SQLException sq2) { }
            try {if (connection != null) { connection.rollback(); connection.close(); } } catch (SQLException sq3) { }
        }
    }
}

